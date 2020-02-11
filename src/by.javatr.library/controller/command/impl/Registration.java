package by.javatr.library.controller.command.impl;

import by.javatr.library.controller.command.Command;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;

public class Registration implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            if (clientService != null) {
                if (clientService.registerNewUser()) {
                    response = "New user added!";
                } else {
                    response = "Wrong login or password!";
                }
            }
        } catch (ServiceException e) {
            response = "Error during added new user procedure";
        }
        return response;
    }
}
