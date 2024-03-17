package org.example.dao.impl;

import org.example.dao.ContactDao;
import org.example.model.Contact;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ContactDaoImpl implements ContactDao {
    private List<Contact> contacts = new ArrayList<>();
    @Override
    public void addContact(Contact contact) {
        contact.setContactId(contacts.size() + 1); // плохая реализация
        contacts.add(contact);
    }

    @Override
    public void deleteContact(int id) {
        contacts.removeIf(contact -> contact.getContactId() == id);
        resortContacts(contacts, id);
    }

    @Override
    public Contact getContactByName(String name) {
        ListIterator<Contact> listIterator = contacts.listIterator();
        Contact contact = null;
        while (listIterator.hasNext()) {
            contact = listIterator.next();
            if (contact.getFirstName().equals(name)) return contact;
        }
        return contact;
    }

    @Override
    public Contact getContactById(int id) {
        ListIterator<Contact> listIterator = contacts.listIterator();
        Contact contact = null;
        while (listIterator.hasNext()) {
            contact = listIterator.next();
            if (contact.getContactId() == id) return contact;
        }
        return contact;
    }

    @Override
    public List<Contact> getContacts() {
        return contacts;
    }

    @Override
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> resortContacts(List<Contact> contacts, int id) {
        for (Contact contact: contacts) {
            if (contact.getContactId() > id) {
                contact.setContactId(contact.getContactId() - 1);
            }
        }
        return contacts;
    }
}
