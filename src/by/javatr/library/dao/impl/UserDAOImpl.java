package by.javatr.library.dao.impl;

import by.javatr.library.bean.User;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.fileUtil.FileManager;
import by.javatr.library.dao.UserDAO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.javatr.library.service.validation.Validation.checkTheUserOnAuth;

public class UserDAOImpl implements UserDAO {

    private final String FIELD = "([а-яА-яA-Za-z0-9]+)";
    private final String IS_ADMIN = "(true|false)";
    private final String address = "resource/users.txt";
    private final File FILE = new File(address);


    @Override
    public boolean signIn(String login, String password) throws DAOException {
        List<User> users = getUsersFromFile();
        if (users == null) {
            throw new DAOException("User not found");
        }
        for (User user : users) {
            if (checkTheUserOnAuth(login, password, user)) {
                return true;
            }
        }
        return false;
    }

    public List<User> getUsersFromFile() throws DAOException {
        List<User> clientList = new ArrayList<>();
        FileManager manager = new FileManager();

        for (String val : manager.loadDataFromFile(FILE)) {
            clientList.add(parsingUserFromString(val));
        }
        return clientList;
    }

    private User parsingUserFromString(String text) {
        User user = null;
        Pattern pattern = Pattern.compile(FIELD + "\\s" + FIELD + "\\s" + IS_ADMIN);// именуй константы
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            user = new User(matcher.group(1), matcher.group(2), Boolean.parseBoolean(matcher.group(3)));
        }
        return user;
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
        return true;
    }

    private void saveNewUserToFile(User userForRegistr) throws DAOException {
        FileManager manager = new FileManager();
        manager.writeUserToFile(userForRegistr, FILE, true);
    }
}
