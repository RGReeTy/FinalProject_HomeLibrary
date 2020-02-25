package by.javatr.library.dao.fileUtil;

import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.FileDAO;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileParser implements FileDAO {

    @Override
    public List<String> loadDataFromFile(String address) throws DAOException {
        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(address))) {
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                list.add(s.trim());
            }
        } catch (FileNotFoundException ex) {
            throw new DAOException("File not found. ", ex);
        } catch (IOException e) {
            throw new DAOException("Error at read method.", e);
        }
        list.remove(null);

        return list;
    }

}
