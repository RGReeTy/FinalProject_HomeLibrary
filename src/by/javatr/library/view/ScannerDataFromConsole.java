package by.javatr.library.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        return number;
    }

    public static String enterStringFromConsole() {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a line please:");
        try {
            stringBuilder.append(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }

}

