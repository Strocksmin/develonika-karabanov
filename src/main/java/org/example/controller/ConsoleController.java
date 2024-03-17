package org.example.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.example.model.Contact;
import org.example.service.impl.ContactServiceImpl;
import org.example.utils.FileSerialization;

import java.util.Scanner;

public class ConsoleController {
    ContactServiceImpl contactService;
    Scanner scanner;

    public ConsoleController(Scanner scanner) {
        this.contactService = new ContactServiceImpl();
        this.scanner = scanner;
        contactService.setContacts((FileSerialization.read(
                System.getProperty("user.dir") + "/src/main/resources/contacts.obj")));
    }

    public void addContactScanner() {
        System.out.print("Введите имя контакта: ");
        String name = scanner.next();
        while (!name.matches("[a-zA-Z]+")) {
            System.out.println("Имя некорректное");
            System.out.print("Введите имя контакта: ");
            name = scanner.next();
        }
        System.out.print("Введите фамилию контакта: ");
        String lastName = scanner.next();
        while (!lastName.matches("[a-zA-Z]+")) {
            System.out.println("Фамилия некорректная");
            System.out.print("Введите фамилию контакта: ");
            lastName = scanner.next();
        }
        System.out.print("Введите номер телефона: ");
        String phone = scanner.next();
        while (!phone.matches("\\d{3}-\\d{3}-\\d{3}")) {
            System.out.println("Номер телефона некорректный");
            System.out.print("Введите номер телефона: ");
            phone = scanner.next();
        }
        System.out.print("Введите адрес электронной почты: ");
        String email = scanner.next();
        while (!EmailValidator.getInstance().isValid(email)) { // RFC
            System.out.println("Адрес электронной почты некорректный");
            System.out.print("Введите адрес электронной почты: ");
            email = scanner.next();
        }
        System.out.println("Контакт успешно добавлен.");
        contactService.addContact(new Contact(name, lastName, phone, email));
    }

    public void getAllContactsScanner() {
        System.out.print("Список контактов: ");
        if (!contactService.getContacts().isEmpty()) {
            System.out.println();
            for (Contact contact: contactService.getContacts()){
                System.out.println(contact);
            }
        }
        else {
            System.out.println(" (пусто) ");
        }
    }

    public void getContactByNameScanner() {
        System.out.print("Введите имя для поиска: ");
        String name = scanner.next();
        System.out.print("Результаты поиска: ");
        if (contactService.getContactByName(name) != null) {
            System.out.println("\n" + contactService.getContactByName(name));
        }
        else {
            System.out.println(" (пусто) ");
        }
    }

    public void deleteContactScanner() {
        System.out.print("Введите номер контакта для удаления: ");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.next());
        }
        catch (Exception ignored) { // simplified
        }
        Contact contact = contactService.getContactById(id);
        if (contact != null) {
            contactService.deleteContact(id);
            System.out.println("Контакт " + contact.getContactInfo() + " успешно удален.");
        }
        else {
            System.out.print("Такого контакта не существует\n");
        }
    }

    public void closeApp() {
        System.out.print("До свидания!");
        FileSerialization.write(System.getProperty("user.dir") + "/src/main/resources/contacts.obj",
                contactService.getContacts());
    }
}