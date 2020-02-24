package by.javatr.library.dao;

import by.javatr.library.dao.impl.BookDAOImpl;
import by.javatr.library.dao.impl.UserDAOImpl;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO fileUserImpl = new UserDAOImpl();
    private final BookDAO fileBookImpl = new BookDAOImpl();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public UserDAO getUserDAO() {
        return fileUserImpl;
    }

    public BookDAO getBookDAO() {
        return fileBookImpl;
    }
}
