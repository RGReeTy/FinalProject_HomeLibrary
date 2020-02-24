package by.javatr.library.service;

import by.javatr.library.bean.Book;

import java.util.List;

public interface LibraryService {
    void addNewBook(Book book) throws ServiceException;

    List<Book> returnCollectionOfBooks();

    List<Book> findTheBook(String textToFind);

    void deleteBook(int id) throws ServiceException;
}
