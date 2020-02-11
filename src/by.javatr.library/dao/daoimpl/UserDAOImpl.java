package by.javatr.library.dao.daoimpl;

import by.javatr.library.bean.User;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.FileDAO;
import by.javatr.library.dao.UserDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static by.javatr.library.service.validation.Validation.checkTheUserOnAuth;
import static by.javatr.library.service.validation.Validation.cryptThePassword;

public class UserDAOImpl implements UserDAO, FileDAO {

    private String address = "src\\by.javatr.library\\resource\\user\\users.txt";
    // String address = "C:\\Users\\RGReeTy\\IdeaProjects\\FinalProject_HomeLibrary\\src\\by.javatr.library\\resource\\user\\users.txt";

    private static final Map<Integer, User> clientList = new HashMap<Integer, User>();
    private static int id = 0;
    private User currentUser = new User();

    {
        try {
            loadDataFromFile(address);
        } catch (DAOException e) {
            System.out.println(e.getMessage());
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

    @Override
    public void loadDataFromFile(String address) throws DAOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(address), "UTF-8");
        } catch (FileNotFoundException e) {
            throw new DAOException("File not found!");
        }
        String usersInString = scanner.useDelimiter("\\A").next();

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9]+)\\s([а-яА-яA-Za-z0-9]+)\\s(true|false)");
        Matcher matcher = pattern.matcher(usersInString);

        while (matcher.find()) {
            clientList.put(++id, new User(matcher.group(1), matcher.group(2), Boolean.parseBoolean(matcher.group(3))));
        }
    }

    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void registration(String login, String password) throws DAOException {
        if (!signIn(login, password)) {
            User registeredUser = new User();
            registeredUser.setUserName(login);
            registeredUser.setUserPassword(password);
            registeredUser.setAdmin(false);
            saveNewUserToFile(registeredUser);
        }
        loadDataFromFile(address);
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
