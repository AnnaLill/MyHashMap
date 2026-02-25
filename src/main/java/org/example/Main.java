package org.example;

public class Main {
    public static void main(String[] args) {

        MyHashMap<Integer, String> passportsAndNames = new MyHashMap<>();

        passportsAndNames.put(12345, "Вакуленко Василий Николаевич");
        passportsAndNames.put(45678, "Котов Николай Иванович");
        passportsAndNames.put(56789, "Сорока Елена Михайловна");

        String vasiliyName = passportsAndNames.get(12345);
        System.out.println(vasiliyName);

        System.out.println(passportsAndNames);

        passportsAndNames.remove(56789);
        System.out.println(passportsAndNames);
    }
}