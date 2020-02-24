package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.impl.ClientServiceImpl;
import by.javatr.library.service.factory.ServiceFactory;

public class Show implements Command {
    @Override
    public String execute(String request) {
        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientServiceImpl clientServiceImpl = null;
        clientServiceImpl = serviceFactory.getClientService();

        if (clientServiceImpl != null) {
            for (Book book : clientServiceImpl.returnCollectionOfBooks()) {

                response += book.toString() + "\n";
            }
        } else response = "Error during load book's library procedure";

        return response;
    }
}