package by.javatr.library.dao;

import by.javatr.library.bean.Book;

import java.util.List;

public interface BookDAO {

    List<Book> getAllBooks();

    void addBook(Book book) throws DAOException;

    void deleteBook() throws DAOException;

}
