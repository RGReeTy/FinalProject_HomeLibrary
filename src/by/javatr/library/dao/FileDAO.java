package by.javatr.library.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public interface FileDAO {

    List<String> loadDataFromFile(File file) throws DAOException, FileNotFoundException;

}
