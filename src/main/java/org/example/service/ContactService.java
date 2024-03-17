package org.example.service;

import org.example.model.Contact;

import java.util.List;

public interface ContactService {
    void addContact(Contact contact);
    void deleteContact(int id);
    Contact getContactByName(String name);
    Contact getContactById(int id);
    List<Contact> getContacts();
    void setContacts(List<Contact> contacts);
}
