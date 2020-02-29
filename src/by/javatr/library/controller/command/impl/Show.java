package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;

import java.util.List;

public class Show implements Command {
    @Override
    public String execute(String request) {
        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

        List<Book> books = null;
        try {
            books = libraryService.returnCollectionOfBooks();
        } catch (ServiceException e) {
            System.out.println("Error during loading library!");
        }
        if (books != null) {
            for (Book book : books) {
                if (book != null) {
                    response += book.toString() + "\n";
                }
            }
        }
        return response;
    }
}