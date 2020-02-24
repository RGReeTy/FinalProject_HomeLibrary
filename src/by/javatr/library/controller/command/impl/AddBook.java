package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;


import static by.javatr.library.view.ScannerDataFromConsole.enterStringFromConsole;

public class AddBook implements Command {
    @Override
    public String execute(String request) {
        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        Book newBook = new Book(enterStringFromConsole(), enterStringFromConsole(), enterStringFromConsole(), enterStringFromConsole());

        try {
            libraryService.addNewBook(newBook);
        } catch (ServiceException e) {
            request = "Error during adding new book";
        }
        for (Book book : libraryService.returnCollectionOfBooks()) {
            System.out.println(book);
            response += book.toString() + "\n";
        }

        return response;
    }
}
