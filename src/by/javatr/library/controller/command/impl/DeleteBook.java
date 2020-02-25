package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;

import static by.javatr.library.view.ScannerDataFromConsole.enterIntFromConsole;

public class DeleteBook implements Command {
    @Override
    public String execute(String request) {
        String response = "";
        System.out.println("Choose book's number for deleting:");
        int idBookForDeleting = enterIntFromConsole();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();

            try {
                libraryService.deleteBook(idBookForDeleting);
            } catch (ServiceException e) {
                System.out.println("Sorry, we caught an error, try again later..");
            }
            for (Book book : libraryService.returnCollectionOfBooks()) {
                System.out.println(book);
                response += book.toString() + "\n";
        }

        return response;
    }
}
