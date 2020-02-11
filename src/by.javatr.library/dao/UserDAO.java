package by.javatr.library.dao;

import by.javatr.library.dao.exception.DAOException;

public interface UserDAO {

    boolean signIn(String login, String password) throws DAOException;

    void registration(String login, String password) throws DAOException;


}
