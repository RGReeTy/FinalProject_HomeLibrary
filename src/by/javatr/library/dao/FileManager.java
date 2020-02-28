package by.javatr.library.dao;

import by.javatr.library.bean.Book;
import by.javatr.library.bean.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager implements FileDAO {

    @Override
    public List<String> loadDataFromFile(File file) throws DAOException {
        List<String> list = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String s = "";
            while ((s = bufferedReader.readLine()) != null) {
                list.add(s);
            }
        } catch (FileNotFoundException ex) {
            throw new DAOException("File not found. ", ex);
        } catch (IOException e) {
            throw new DAOException("ReaFileException in read method.", e);
        }
        return list;
    }

    @Override
    public void writeUserToFile(User user, File file, boolean append) throws DAOException {
        try (FileWriter writer = new FileWriter(file, append)) {
            writer.append(System.lineSeparator());
            writer.write(user.getUserName() + " "
                    + user.getUserPassword()
                    + " " + user.isAdmin());
            writer.flush();
        } catch (IOException ex) {
            throw new DAOException("Error at saving new user", ex);
        }
    }

    @Override
    public void writeBookToFile(Book book, File file, boolean append) throws DAOException {
        try (FileWriter writer = new FileWriter(file, append)) {
            writer.append(System.lineSeparator());
            writer.write(book.getBookName() + " " +
                    book.getAuthor() + " " +
                    book.getTypeOfBook() + " " +
                    book.getAboutBook() + "\n");
            writer.flush();
        } catch (IOException ex) {
            throw new DAOException("Error at saving Library", ex);
        }
    }
}
