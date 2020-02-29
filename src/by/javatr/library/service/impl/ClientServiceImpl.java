package by.javatr.library.service.impl;

import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.DAOFactory;
import by.javatr.library.dao.UserDAO;
import by.javatr.library.service.ClientService;
import by.javatr.library.service.ServiceException;

import java.io.FileNotFoundException;

import static by.javatr.library.service.validation.Validation.checkAllSymbolsOnLetterOrDigit;
import static by.javatr.library.service.validation.Validation.cryptThePassword;

public class ClientServiceImpl implements ClientService {

    public ClientServiceImpl() {
    }

    @Override
    public boolean singIn(String login, String password) throws ServiceException {
        if (checkAllSymbolsOnLetterOrDigit(login, password)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try {
                //Crypt the password
                password = cryptThePassword(password);
                return (userDAO.signIn(login, password));
            } catch (DAOException e) {
                throw new ServiceException("Error during sign in procedure", e);
            } catch (FileNotFoundException ex) {
                throw new ServiceException("File not found!", ex);
            }
        }
        return false;
    }

    @Override
    public boolean registration(String login, String password) throws ServiceException {
        if (checkAllSymbolsOnLetterOrDigit(login, password)) {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try {
                //Crypt the password
                password = cryptThePassword(password);
                return (userDAO.registration(login, password));
            } catch (DAOException e) {
                throw new ServiceException("Error during registration procedure");
            } catch (FileNotFoundException ex) {
                throw new ServiceException("File not found!");
            }
        }
        return false;
    }
}

