package by.javatr.library.service.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.DAOFactory;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.ServiceException;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public void addNewBook(Book book) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.addBook(book);
        } catch (DAOException e) {
            throw new ServiceException("Error during adding new book!", e);
        }
    }

    public List<Book> returnCollectionOfBooks() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        List<Book> books = null;

        try {
            books = bookDAO.getAllBooks();
        } catch (DAOException e) {
            System.out.println("Error during loading library!");
        }
        return books;
    }


    public List<Book> findTheBook(String textToFind) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        List<Book> temp = new LinkedList<>();
        List<Book> findingBooks = new LinkedList<>();
        try {
            findingBooks.addAll(bookDAO.getAllBooks());
        } catch (DAOException e) {
            System.out.println("Error during loading library!");
        }
        Pattern pattern = Pattern.compile(textToFind.toLowerCase());

        for (Book book : findingBooks) {
            Matcher matcher = pattern.matcher(book.toString().toLowerCase());
            if (matcher.find()) {
                temp.add(book);
            }
        }
        if (temp.size() == 0) {
            return null;
        } else {
            return temp;
        }
    }

    @Override
    public void deleteBook(int id) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();
        try {
            bookDAO.deleteBook(id);
        } catch (DAOException e) {
            throw new ServiceException("Error during deleting procedure", e);
        }

    }
}
