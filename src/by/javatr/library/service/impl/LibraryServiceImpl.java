package by.javatr.library.service.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookBuilder;
import by.javatr.library.dao.DAOException;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.ServiceException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public void addNewBook(Book book) {

    }

    public ArrayList<Book> returnCollectionOfBooks() {
        return books = bookDAO.getAllBooks();
    }

    public void addNewBook() throws IOException, ServiceException {
        // такое ощущение, что я читаю код студента 1-го курса
        // которому layered architecture вообще не зашло
        // какое чтение из консоли в сервисах. Карл????
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter book's name..");
        String bookName = reader.readLine();
        System.out.println("Enter author's name..");
        String author = reader.readLine();
        System.out.println("Enter type of book..");
        String typeOfBook = reader.readLine();
        System.out.println("What about this book..");
        String info = reader.readLine();

        bookDAO.addBook(new BookBuilder()
                .withTitle(bookName)
                .withAuthors(author)
                .withTypeOfBook(typeOfBook)
                .withInfo(info)
                .build());

        try {
            bookDAO.saveLibraryToTXT();
        } catch (DAOException e) {
            throw new ServiceException("Error during saving procedure", e);
        }
    }

    public ArrayList<Book> findTheBook() throws ServiceException {
        String textToFind;
        ArrayList<Book> findingBooks = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Enter what you want to find..");
        try {
            textToFind = reader.readLine();
        } catch (IOException e) {
            throw new ServiceException("Error during finding procedure", e);
        }

        Pattern pattern = Pattern.compile(textToFind.toLowerCase());

        for (Book book : books) {
            Matcher matcher = pattern.matcher(book.toString().toLowerCase());
            if (matcher.find()) {
                findingBooks.add(book);
            }
        }
        if (findingBooks.size() == 0) {
            return null;
        } else {
            return findingBooks;
        }
    }

    public void deleteBook() throws ServiceException {
        if (userDAO.getCurrentUser().isAdmin()) {
            try {
                bookDAO.deleteBook();
            } catch (DAOException e) {
                throw new ServiceException("Error during deleting procedure", e);
            }
        } else {
            System.out.println("\nOnly administrator can delete books!");
        }
    }
}
