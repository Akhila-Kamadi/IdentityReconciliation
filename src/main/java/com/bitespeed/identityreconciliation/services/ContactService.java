package com.bitespeed.identityreconciliation.services;

import com.bitespeed.identityreconciliation.dtos.ContactDto;
import com.bitespeed.identityreconciliation.models.Contact;

import java.util.List;

public interface ContactService {
    Contact createContact(String email, String phoneNumber);

    ContactDto createConsolidatedContact(String email, String phoneNumber);

    List<Contact> getAllContacts();
}
