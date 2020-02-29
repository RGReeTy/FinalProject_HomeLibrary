package by.javatr.library.service;

public interface ClientService {

    boolean singIn(String login, String password) throws ServiceException;

    boolean registration(String login, String password) throws ServiceException;

}
