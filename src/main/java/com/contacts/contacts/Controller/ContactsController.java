package com.contacts.contacts.Controller;

import com.contacts.contacts.Models.Contacts;
import com.contacts.contacts.Repo.ContactsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ContactsController {
    @Autowired
    ContactsRepo contactsRepo;

    @GetMapping ("/")
    public  String getPage(){
        return "Hello World";
    }
    @GetMapping (value ="/contacts")
    public List<Contacts>getContacts(){
        return contactsRepo.findAll();
    }
    @PostMapping(value="/save")
    public String saveContacts(@RequestBody Contacts contacts){
        contactsRepo.save(contacts);
        return "contacts saved";
    }
    @PutMapping(value="update/{id}")
    public  String updatedContacts(@PathVariable long id,@RequestBody Contacts contacts){
        Contacts updatedContacts = contactsRepo.findById(id).get();
        updatedContacts.setName(contacts.getName());
        updatedContacts.setEmail(contacts.getEmail());
        updatedContacts.setPhoneNumber(contacts.getPhoneNumber());
        contactsRepo.save(updatedContacts);
        return "Contacts Updated";
    }
    @DeleteMapping (value= "delete/{id}")
    public  String deleteContact(@PathVariable long id){
        Contacts deleteContact = contactsRepo.findById(id).get();
        contactsRepo.delete(deleteContact);
        return "contact deleted";

    }
    @GetMapping("/{id}")
    public ResponseEntity<Contacts> getContactById(@PathVariable Long id) {
        Optional<Contacts> contact = contactsRepo.findById(id);
        return contact.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
