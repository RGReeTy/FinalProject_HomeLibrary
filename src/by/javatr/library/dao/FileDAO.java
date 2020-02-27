package by.javatr.library.dao;

import by.javatr.library.bean.Book;
import by.javatr.library.bean.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileDAO {

    List<String> loadDataFromFile(File file) throws DAOException, FileNotFoundException;

    void writeUserToFile(User user, File file, boolean append) throws DAOException;

    void writeBookToFile(Book book, File file, boolean append) throws DAOException;


}
