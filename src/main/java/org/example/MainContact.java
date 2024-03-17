package org.example;

import org.apache.commons.validator.routines.EmailValidator;
import org.example.dao.impl.ContactDaoImpl;
import org.example.model.Contact;
import org.example.utils.FileSerialization;

import java.util.Scanner;

public class MainContact {
    ContactDaoImpl contactDao = new ContactDaoImpl();
    public void work() {
        boolean isBreak = false;
        contactDao.setContacts((FileSerialization.read(
                System.getProperty("user.dir") + "/src/main/resources/contacts.obj")));
        System.out.println("Добро пожаловать в приложение \"Список контактов\"!");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Посмотреть список контактов");
        System.out.println("3. Найти контакт по имени");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Выход");
        Scanner scanner = new Scanner(System.in);
        int answer;
        while (!isBreak) {
            System.out.print("Выберите действие (введите номер): ");
            try {
                answer = Integer.parseInt(scanner.next());
            }
            catch (Exception e) {
                answer = 0;
            }
            switch (answer) {
                case 1:
                    addContactScanner(scanner);
                    break;
                case 2:
                    getAllContactsScanner();
                    break;
                case 3:
                    getContactByNameScanner(scanner);
                    break;
                case 4:
                    deleteContactScanner(scanner);
                    break;
                case 5:
                    System.out.print("До свидания!");
                    isBreak = true;
                    FileSerialization.write(System.getProperty("user.dir") + "/src/main/resources/contacts.obj",
                            contactDao.getContacts());
                    break;
                default:
                    System.out.println("Неправильный номер!");
            }
        }
        scanner.close();
    }
    private void addContactScanner(Scanner scanner) {
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
        contactDao.addContact(new Contact(name, lastName, phone, email));
    }

    private void getAllContactsScanner() {
        System.out.print("Список контактов: ");
        if (!contactDao.getContacts().isEmpty()) {
            System.out.println();
            for (Contact contact: contactDao.getContacts()){
                System.out.println(contact);
            }
        }
        else {
            System.out.println(" (пусто) ");
        }
    }
    private void getContactByNameScanner(Scanner scanner) {
        System.out.print("Введите имя для поиска: ");
        String name = scanner.next();
        System.out.print("Результаты поиска: ");
        if (contactDao.getContactByName(name) != null) {
            System.out.println("\n" + contactDao.getContactByName(name));
        }
        else {
            System.out.println(" (пусто) ");
        }
    }

    private void deleteContactScanner(Scanner scanner) {
        System.out.print("Введите номер контакта для удаления: ");
        int id = -1;
        try {
            id = Integer.parseInt(scanner.next());
        }
        catch (Exception ignored) { // simplified
        }
        Contact contact = contactDao.getContactById(id);
        if (contact != null) {
            contactDao.deleteContact(id);
            System.out.println("Контакт " + contact.getContactInfo() + " успешно удален.");
        }
        else {
            System.out.print("Такого контакта не существует\n");
        }
    }
}