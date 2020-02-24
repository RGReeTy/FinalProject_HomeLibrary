package by.javatr.library.service.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.DAOFactory;
import by.javatr.library.service.LibraryService;
import by.javatr.library.service.ServiceException;

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
        try {
            bookDAO.saveLibraryToTXT();
        } catch (DAOException e) {
            throw new ServiceException("Error during saving new book!", e);
        }
    }

    public List<Book> returnCollectionOfBooks() {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();

        return  bookDAO.getAllBooks();
    }


    public List<Book> findTheBook(String textToFind) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        BookDAO bookDAO = daoFactory.getBookDAO();

        List<Book> findingBooks = bookDAO.getAllBooks();

        Pattern pattern = Pattern.compile(textToFind.toLowerCase());

        for (Book book : findingBooks) {
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
