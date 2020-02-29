package by.javatr.library.service;

import by.javatr.library.bean.Book;

import java.util.List;

public interface LibraryService {
    void addNewBook(Book book) throws ServiceException;

    List<Book> returnCollectionOfBooks() throws ServiceException;

    List<Book> findTheBook(String textToFind) throws ServiceException;

    void deleteBook(int id) throws ServiceException;
}
