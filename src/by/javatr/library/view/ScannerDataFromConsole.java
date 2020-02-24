package by.javatr.library.view;

import java.util.Scanner;

public class ScannerDataFromConsole {

    public static int enterIntFromConsole() {
        Scanner sc = new Scanner(System.in);
        int number = 0;
        System.out.println("Print a number plz:");
        while (!sc.hasNextInt()) {
            sc.next();
        }
        number = sc.nextInt();
        sc.close();
        return number;
    }

    public static String enterStringFromConsole() {
        Scanner sc = new Scanner(System.in);
        String s = null;
        System.out.println("Print a line plz:");
        while (!sc.hasNextLine()) {
            sc.next();
        }
        s = sc.nextLine();
        sc.close();
        return s;
    }
}
