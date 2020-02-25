package by.javatr.library.service.validation;

import by.javatr.library.bean.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    //Next number is constant to Cesar's shift
    final static int plusStepForChar = 13;

    public static boolean checkTheUserOnAuth(String login, String password, User user) {
        if (user == null) {
            return false;
        }
        return user.getUserName().equalsIgnoreCase(login)
                & user.getUserPassword().equalsIgnoreCase(cryptThePassword(password));
    }

    public static String cryptThePassword(String password) {
        String cryptedWord = "";
        char[] symbols = password.toCharArray();
        for (char ch : symbols) {
            cryptedWord += (ch + plusStepForChar);
        }
        return cryptedWord;
    }

    public static boolean checkAllSymbolsOnLetterOrWhitespaceRegEx(String login, String password) {
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{1,20}$");
        Matcher matcherLogin = pattern.matcher(login);
        Matcher matcherPassword = pattern.matcher(password);

        return matcherLogin.find() & matcherPassword.find();
    }
}

