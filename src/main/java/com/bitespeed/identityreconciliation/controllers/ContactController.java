package com.bitespeed.identityreconciliation.controllers;

import com.bitespeed.identityreconciliation.dtos.ContactDto;
import com.bitespeed.identityreconciliation.dtos.IdentifyContactRequestDto;
import com.bitespeed.identityreconciliation.dtos.IdentifyContactResponseDto;
import com.bitespeed.identityreconciliation.models.Contact;
import com.bitespeed.identityreconciliation.services.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/identify")
    public IdentifyContactResponseDto getConsolidatedContactInformation(@RequestBody IdentifyContactRequestDto request) {
        String phoneNumber = request.getPhoneNumber() == null ? null : request.getPhoneNumber().toString();
        ContactDto contact = contactService.createConsolidatedContact(request.getEmail(), phoneNumber);
        return new IdentifyContactResponseDto(contact);
    }

    @PostMapping
    public Contact createContact(@RequestBody IdentifyContactRequestDto request) {
        return contactService.createContact(request.getEmail(), request.getPhoneNumber().toString());
    }

    @GetMapping
    public List<Contact> getContacts() {
        return contactService.getAllContacts();
    }
}
