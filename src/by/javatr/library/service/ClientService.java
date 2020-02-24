package by.javatr.library.service;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.impl.BookDAOImpl;
import by.javatr.library.dao.impl.UserDAOImpl;
import by.javatr.library.dao.BookBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.javatr.library.service.validation.Validation.checkAllSymbolsOnLetterOrWhitespaceRegEx;

public class ClientService {
    private UserDAOImpl userDAO = new UserDAOImpl();// ну вот что, у тебя не хватало времени, чтобы переписать этот бред????
    // мы не обсуждали, почему реализацию слоя дао раскрывать не стоит?
    private BookDAOImpl bookDAO;

    {
        bookDAO = new BookDAOImpl();
    }

    private ArrayList<Book> books;// опять 25

    public ClientService() throws ServiceException {
    }

    public boolean signIn(String login, String password) throws ServiceException {
        try {
            return userDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException("Error during sign in procedure!", e);
        }
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

    public boolean registerNewUser() throws ServiceException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter login:");
        String login = scanner.nextLine();
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        if (checkAllSymbolsOnLetterOrWhitespaceRegEx(login, password)) {
            try {
                userDAO.registration(login, password);
            } catch (DAOException e) {
                throw new ServiceException("Error at saving new user");
            }
            return true;
        } else {
            System.out.println("Wrong symbols at login/password! Needed A-Z, 0-9");
            return false;
        }
    }
}

