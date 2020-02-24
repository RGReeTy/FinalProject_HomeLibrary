package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.impl.ClientServiceImpl;
import by.javatr.library.service.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;

import static by.javatr.library.view.ScannerDataFromConsole.enterStringFromConsole;

public class Registration implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        String login = enterStringFromConsole();
        String password = enterStringFromConsole();

        if (clientService.registration(login, password)) {
            response = "New user added!";
        } else {
            response = "Wrong login or password!";
        }
        return response;
    }
}
