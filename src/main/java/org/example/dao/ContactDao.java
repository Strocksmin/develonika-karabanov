package org.example.dao;

import org.example.model.Contact;

import java.util.List;

public interface ContactDao {
    public void addContact(Contact contact);
    public void deleteContact(int id);
    public Contact getContactByName(String name);
    public Contact getContactById(int id);
    public List<Contact> getContacts();
    public void setContacts(List<Contact> contacts);
}
