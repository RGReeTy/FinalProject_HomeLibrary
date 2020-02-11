package by.javatr.library.dao;

public interface UserDAO {

    boolean signIn(String login, String password) throws DAOException;

    void registration(String login, String password) throws DAOException;


}
