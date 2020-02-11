package by.javatr.library.dao;

import java.io.FileNotFoundException;

public interface FileDAO {

    void loadDataFromFile(String address) throws DAOException, FileNotFoundException;

}
