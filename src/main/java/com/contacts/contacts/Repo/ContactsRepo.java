package com.contacts.contacts.Repo;

import com.contacts.contacts.Models.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactsRepo extends JpaRepository<Contacts,Long> {
}
