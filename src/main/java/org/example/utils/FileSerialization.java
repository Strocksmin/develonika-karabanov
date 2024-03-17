package org.example.utils;

import org.example.model.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSerialization {

    public static void write(String filename, List<Contact> contacts) {
        try (FileOutputStream fos = new FileOutputStream(filename);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(contacts);
        }
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Contact> read(String filename) {
        List<Contact> list = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(filename);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            list = (ArrayList) ois.readObject();
        }
        catch (IOException ignored) {
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
}