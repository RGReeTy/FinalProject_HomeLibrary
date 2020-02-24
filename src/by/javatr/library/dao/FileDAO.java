package by.javatr.library.dao;

import java.io.FileNotFoundException;
import java.util.List;

public interface FileDAO {

    List<String> loadDataFromFile(String address) throws DAOException, FileNotFoundException;

}
