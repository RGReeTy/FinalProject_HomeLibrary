package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.dao.exception.DAOException;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.exception.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;

import java.io.FileNotFoundException;

public class DeleteBook implements Command {
    @Override
    public String execute(String request) {
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = null;
        clientService = serviceFactory.getClientService();

        if (clientService != null) {
            try {
                clientService.deleteBook();
            } catch (ServiceException e) {
                System.out.println("Sorry, we caught an error, try again later..");
            }
            for (Book book : clientService.returnCollectionOfBooks()) {
                System.out.println(book);
                response += book.toString() + "\n";
            }
        } else response = "Error during load book's library procedure";

        return response;
    }
}
