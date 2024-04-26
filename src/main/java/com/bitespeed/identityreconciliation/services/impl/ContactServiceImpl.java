package com.bitespeed.identityreconciliation.services.impl;

import com.bitespeed.identityreconciliation.dtos.ContactDto;
import com.bitespeed.identityreconciliation.models.Contact;
import com.bitespeed.identityreconciliation.models.enums.LinkPrecedence;
import com.bitespeed.identityreconciliation.repositories.ContactRepository;
import com.bitespeed.identityreconciliation.services.ContactService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    //Create Contact
    public Contact createContact(String email, String phoneNumber) {

        Optional<Contact> existingByEmail = contactRepository.findFirstByEmailOrderByCreatedAtAsc(email);
        Optional<Contact> existingByPhoneNumber = contactRepository.findFirstByPhoneNumberOrderByCreatedAtAsc(phoneNumber);

        if (existingByEmail.isEmpty() && existingByPhoneNumber.isEmpty()) {
            //if both doesn't exist -> create new contact
            Contact primaryContact = createNewContact(email, phoneNumber, LinkPrecedence.PRIMARY);
            return contactRepository.save(primaryContact);
        } else if (existingByEmail.isPresent() && existingByPhoneNumber.isPresent()) {
            //If both exists check if both are of same id or different, and perform update.
            Contact contactByEmail = existingByEmail.get();
            Contact contactByPhoneNumber = existingByPhoneNumber.get();
            if (contactByEmail.getId().equals(contactByPhoneNumber.getId())) {
                throw new RuntimeException("Contact already exists with email: " + email + " and phoneNumber: " + phoneNumber);
            } else {
                Contact olderContact = contactByEmail.getCreatedAt().isBefore(contactByPhoneNumber.getCreatedAt()) ? contactByEmail : contactByPhoneNumber;
                Contact newerContact = olderContact.equals(contactByEmail) ? contactByPhoneNumber : contactByEmail;
                newerContact.setLinkPrecedence(LinkPrecedence.SECONDARY);
                newerContact.setLinkedId(olderContact.getId());
                return contactRepository.save(newerContact);
            }
        } else {
            //If either of one present -> create secondary contact
            Contact primaryContact = existingByEmail.isPresent() ? existingByEmail.get() : existingByPhoneNumber.get();
            Contact secondaryContact = createNewContact(email, phoneNumber, LinkPrecedence.SECONDARY);
            secondaryContact.setLinkedId(primaryContact.getId());
            return contactRepository.save(secondaryContact);
        }
    }

    private Contact createNewContact(String email, String phoneNumber, LinkPrecedence linkPrecedence) {
        Contact contact;
        contact = new Contact();
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        contact.setLinkPrecedence(linkPrecedence);
        contact.setCreatedAt(LocalDateTime.now());
        contact.setUpdatedAt(LocalDateTime.now());
        return contact;
    }


    //Create Consolidate contact
    public ContactDto createConsolidatedContact(String email, String phoneNumber) {

        List<String> emails = new ArrayList<>();
        List<String> phoneNumbers = new ArrayList<>();
        List<Integer> secondaryContactIds = new ArrayList<>();

        List<Contact> existingContactList = contactRepository.findByEmailOrPhoneNumberOrderByCreatedAtAsc(email, phoneNumber);
        Contact primaryContact = null;

        for (Contact contact : existingContactList) {
            //Check if primary contact exists in list
            if (contact.getLinkPrecedence().equals(LinkPrecedence.PRIMARY)) {
                primaryContact = contact;
            }
        }
        //if primary not exist based on given input -> get primary
        if (primaryContact == null) {
            primaryContact = contactRepository.findById(existingContactList.get(0).getLinkedId()).get();
        }

        emails.add(primaryContact.getEmail());
        phoneNumbers.add(primaryContact.getPhoneNumber());

        //Get linked secondary contacts
        List<Contact> secondaryContactList = contactRepository.findByLinkedId(primaryContact.getId());

        for (Contact secondoryContact : secondaryContactList) {
            if (!emails.contains(secondoryContact.getEmail())) {
                emails.add(secondoryContact.getEmail());
            }
            if (!phoneNumbers.contains(secondoryContact.getPhoneNumber())) {
                phoneNumbers.add(secondoryContact.getPhoneNumber());
            }
            secondaryContactIds.add(secondoryContact.getId());
        }

        ContactDto consolidatedContact = new ContactDto();

        consolidatedContact.setPrimaryContactId(primaryContact.getId());
        consolidatedContact.setEmails(emails);
        consolidatedContact.setPhoneNumbers(phoneNumbers);
        consolidatedContact.setSecondaryContactIds(secondaryContactIds);

        return consolidatedContact;
    }

    //Get All the Contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }


}
