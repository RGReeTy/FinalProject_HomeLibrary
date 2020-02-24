package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.factory.ServiceFactory;

import static by.javatr.library.view.ScannerDataFromConsole.enterStringFromConsole;

public class Find implements Command {
    @Override
    public String execute(String request) {
        String response = "";
        String textToFind = enterStringFromConsole();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();


        for (Book book : libraryService.findTheBook(textToFind)) {
            response += book.toString() + "\n";
        }
        if (response.equals(null)) {
            response = "Error during load book's library procedure";
        }

        return response;
    }
}
