package org.example;

import org.example.controller.ConsoleController;

import java.util.Scanner;

public class ConsoleApp {
    public void work() {
        boolean isBreak = false;
        System.out.println("Добро пожаловать в приложение \"Список контактов\"!");
        System.out.println("1. Добавить контакт");
        System.out.println("2. Посмотреть список контактов");
        System.out.println("3. Найти контакт по имени");
        System.out.println("4. Удалить контакт");
        System.out.println("5. Выход");
        Scanner scanner = new Scanner(System.in);
        ConsoleController consoleController = new ConsoleController(scanner);
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
                    consoleController.addContactScanner();
                    break;
                case 2:
                    consoleController.getAllContactsScanner();
                    break;
                case 3:
                    consoleController.getContactByNameScanner();
                    break;
                case 4:
                    consoleController.deleteContactScanner();
                    break;
                case 5:
                    consoleController.closeApp();
                    isBreak = true;
                    break;
                default:
                    System.out.println("Неправильный номер!");
            }
        }
        scanner.close();
    }
}