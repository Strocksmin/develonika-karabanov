package org.example;

import org.apache.commons.validator.routines.EmailValidator;
import org.example.dao.impl.ContactDaoImpl;

import java.util.Scanner;

public class Main {
/*
    ContactDaoImpl contactDao = new ContactDaoImpl();
*/

    public static void main(String[] args) {
        MainContact mainContact = new MainContact();
        mainContact.work();
    }
}