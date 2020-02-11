package by.javatr.library.dao;

import by.javatr.library.dao.exception.DAOException;

import java.io.FileNotFoundException;

public interface FileDAO {

    void loadDataFromFile(String address) throws DAOException, FileNotFoundException;

}
