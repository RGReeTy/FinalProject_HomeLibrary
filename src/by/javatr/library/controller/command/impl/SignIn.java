package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.factory.ServiceFactory;

import static by.javatr.library.view.ScannerDataFromConsole.enterStringFromConsole;

public class SignIn implements Command {
    @Override
    public String execute(String request) {

        String response = null;

        String login = enterStringFromConsole();
        String password = enterStringFromConsole();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = null;
        clientService = serviceFactory.getClientService();

        if (clientService.singIn(login, password)) {
            response = "Welcome";
        } else {
            response = "Wrong login or password!";
        }
        return response;
    }
}
