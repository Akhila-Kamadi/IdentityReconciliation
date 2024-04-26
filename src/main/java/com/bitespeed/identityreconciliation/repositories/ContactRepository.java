package com.bitespeed.identityreconciliation.repositories;

import com.bitespeed.identityreconciliation.models.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

    List<Contact> findByLinkedId(Integer linkedId);

    List<Contact> findByEmailOrPhoneNumberOrderByCreatedAtAsc(
            String email,
            String phoneNumber);

    Optional<Contact> findFirstByEmailOrderByCreatedAtAsc(String email);

    Optional<Contact> findFirstByPhoneNumberOrderByCreatedAtAsc(String phoneNumber);
}
