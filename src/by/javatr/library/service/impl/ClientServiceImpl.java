package by.javatr.library.service.impl;

import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.DAOFactory;
import by.javatr.library.dao.UserDAO;
import by.javatr.library.service.ClientService;

import static by.javatr.library.service.validation.Validation.checkAllSymbolsOnLetterOrWhitespaceRegEx;

public class ClientServiceImpl implements ClientService {

    public ClientServiceImpl() {
    }

    @Override
    public boolean singIn(String login, String password) {
        if (checkAllSymbolsOnLetterOrWhitespaceRegEx(login, password)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try {
                return (userDAO.signIn(login, password));
            } catch (DAOException e) {
                System.out.println("Error during sign in procedure");
            }
        }
        return false;
    }

    @Override
    public boolean registration(String login, String password) {
        if (checkAllSymbolsOnLetterOrWhitespaceRegEx(login, password)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try {
                return (userDAO.registration(login, password));
            } catch (DAOException e) {
                System.out.println("Error during login procedure");
            }
        }
        return false;
    }
}

