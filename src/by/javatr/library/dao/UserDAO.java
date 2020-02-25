package by.javatr.library.dao;

public interface UserDAO {

    boolean signIn(String login, String password) throws DAOException;

    boolean registration(String login, String password) throws DAOException;



}
