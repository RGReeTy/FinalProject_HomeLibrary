package by.javatr.library.controller.command.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.controller.command.Command;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.ServiceException;
import by.javatr.library.service.factory.ServiceFactory;

import static by.javatr.library.view.ScannerDataFromConsole.enterStringFromConsole;

public class Find implements Command {
    @Override
    public String execute(String request) {
        String response = "";
        String textToFind = enterStringFromConsole();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        LibraryService libraryService = serviceFactory.getLibraryService();


        try {
            for (Book book : libraryService.findTheBook(textToFind)) {
                response += book.toString() + "\n";
            }
        } catch (ServiceException e) {
            System.out.println("Error during finding procedure!");
        }

        return response;
    }
}
