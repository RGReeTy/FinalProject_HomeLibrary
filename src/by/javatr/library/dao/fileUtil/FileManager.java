package by.javatr.library.dao.fileUtil;

import by.javatr.library.dao.DAOException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    public List<String> readFile(File file) throws DAOException {

        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String s = null;
            while ((s = bufferedReader.readLine()) != null) {
                list.add(s);
            }
        } catch (FileNotFoundException ex) {
            throw new DAOException("File not found. ",ex);
        } catch (IOException e) {
            throw new DAOException("ReaFileException in read method.",e);
        }

        return list;
    }



}
