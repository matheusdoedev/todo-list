package com.matheusdoedev.todo.list.utils;

import java.util.Scanner;

public class Utils {

    public static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static void println(String message) {
        System.out.println(message);
    }

    public static double askForADouble(String message) {
        println(message);
        return getScanner().nextDouble();
    }

    public static int askForAInt(String message) {
        println(message);
        return getScanner().nextInt();
    }

    public static String askForAString(String message) {
        println(message);
        return getScanner().nextLine();
    }
}
