package by.javatr.library.dao.impl;

import by.javatr.library.bean.User;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.FileParser;
import by.javatr.library.dao.UserDAO;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.javatr.library.service.validation.Validation.checkTheUserOnAuth;
import static by.javatr.library.service.validation.Validation.cryptThePassword;

public class UserDAOImpl implements UserDAO {

    private final String FIELD = "([а-яА-яA-Za-z0-9]+)";
    private final String IS_ADMIN = "(true|false)";

    private String address = "src\\by\\javatr\\library\\resource\\user\\users.txt";

    private static final Map<Integer, User> clientList = new HashMap<Integer, User>();
    private static int id = 0;
    private User currentUser = new User();// кому я что рассказывала??????? опять поля объекта полезли

    {
        try {
            loadDataFromFile(address);
        } catch (DAOException e) {
            System.out.println(e.getMessage());// действительно, файл не загрузился, ну и фиг с ним, выведем тихонько строчку на консоль и все
        }
    }

    @Override
    public boolean signIn(String login, String password) throws DAOException {
        for (Map.Entry<Integer, User> entry : clientList.entrySet()) {
            if (checkTheUserOnAuth(login, password, entry.getValue())) {
                currentUser.setUserName(entry.getValue().getUserName());
                currentUser.setUserPassword(entry.getValue().getUserPassword());
                currentUser.setAdmin(entry.getValue().isAdmin());
                return true;
            }
        }
        return false;
    }

    public void loadDataFromFile(String address) throws DAOException {

        for (String val : new FileParser().loadDataFromFile(address)) {
            parsingUserFromString(val);

        }
    }

    private void parsingUserFromString(String text) {
        Pattern pattern = Pattern.compile(FIELD + "\\s" + FIELD + "\\s" + IS_ADMIN);// именуй константы
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            clientList.put(++id, new User(matcher.group(1), matcher.group(2), Boolean.parseBoolean(matcher.group(3))));
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public boolean registration(String login, String password) throws DAOException {
        if (!signIn(login, password)) {
            User registeredUser = new User();
            registeredUser.setUserName(login);
            registeredUser.setUserPassword(password);
            registeredUser.setAdmin(false);
            saveNewUserToFile(registeredUser);
        }
        loadDataFromFile(address);
        return true;
    }

    private void saveNewUserToFile(User userForRegistr) throws DAOException {
        try (FileWriter writer = new FileWriter(address, true)) {
            writer.append(System.lineSeparator());
            writer.write(userForRegistr.getUserName() + " "
                    + cryptThePassword(userForRegistr.getUserPassword())
                    + " " + userForRegistr.isAdmin());
            writer.flush();
        } catch (IOException ex) {
            throw new DAOException("Error at saving new user", ex);
        }
    }
}
