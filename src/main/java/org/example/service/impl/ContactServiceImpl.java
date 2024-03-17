package org.example.service.impl;

import org.example.service.ContactService;
import org.example.model.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ContactServiceImpl implements ContactService {
    private List<Contact> contacts = new ArrayList<>();
    @Override
    public void addContact(Contact contact) {
        contact.setContactId(contacts.size() + 1);
        contacts.add(contact);
    }

    @Override
    public void deleteContact(int id) {
        contacts.removeIf(contact -> contact.getContactId() == id);
        resortContacts(contacts, id);
    }

    @Override
    public Contact getContactByName(String name) { // чувствителен к регистру
        ListIterator<Contact> listIterator = contacts.listIterator();
        Contact contact;
        while (listIterator.hasNext()) {
            contact = listIterator.next();
            if (contact.getFirstName().equals(name)) return contact;
        }
        return null;
    }

    @Override
    public Contact getContactById(int id) {
        ListIterator<Contact> listIterator = contacts.listIterator();
        Contact contact;
        while (listIterator.hasNext()) {
            contact = listIterator.next();
            if (contact.getContactId() == id) return contact;
        }
        return null;
    }

    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void resortContacts(List<Contact> contacts, int id) {
        for (Contact contact: contacts) {
            if (contact.getContactId() > id) {
                contact.setContactId(contact.getContactId() - 1);
            }
        }
    }
}
