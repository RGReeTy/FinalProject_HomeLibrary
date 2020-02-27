package by.javatr.library.dao;

import java.io.FileNotFoundException;

public interface UserDAO {

    boolean signIn(String login, String password) throws DAOException, FileNotFoundException;

    boolean registration(String login, String password) throws DAOException, FileNotFoundException;



}
