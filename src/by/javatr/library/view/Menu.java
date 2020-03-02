package by.javatr.library.view;

import by.javatr.library.controller.Controller;

import static by.javatr.library.view.ScannerDataFromConsole.enterStringFromConsole;

public class Menu {
    private Controller controller = new Controller();
    private final String WELCOME_TEXT = "Welcome to the library!";
    private final String OPTIONS_USER = "You can choose next commands:\n[SHOW]\t[FIND]\t[ADD_BOOK]\t[DELETE_BOOK]\t[QUIT]";
    private final String BUE_TEXT = "Have a nice day!";

    public void run() {
        String access;
        System.out.println(WELCOME_TEXT);
        access = controller.executeTask("SIGN_IN ");
        if (access.equals("Welcome")) {
            workWithLibrary();
        } else {
            registerNewUser();
        }
    }


    private void workWithLibrary() {
        System.out.println(OPTIONS_USER);

        String request = enterStringFromConsole();

        if (request.equalsIgnoreCase("QUIT")) {
            System.out.println(BUE_TEXT);
        } else {
            String response = controller.executeTask(request);
            System.out.println(response);
            workWithLibrary();
        }
    }


    private void registerNewUser() {
        System.out.println("Do you want to register as a new user? (Y/N)");
        if (enterStringFromConsole().equalsIgnoreCase("Y")) {
            controller.executeTask("REGISTRATION");
        }
        run();
    }
}
