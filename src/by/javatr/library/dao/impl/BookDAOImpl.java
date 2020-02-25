package by.javatr.library.dao.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.fileUtil.FileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDAOImpl implements BookDAO {

    //DELETE: private ArrayList<Book> books;// а если у тебя будет 2, 3, 20  реализаций различных дао - то как будешь работать с этой переменной? дублировать
    //слушай внимательноо условия, мы определяли, что каждый метод в дао самостоятельно обращается к источнику
    //private String address = "src\\by\\javatr\\library\\resource\\library\\Library.txt";// src не должно присутствовать в пути к файлу, ты сможешь выполнить такой код только из ide
    private final String address = "resource/Library.txt";
    private final File FILE = new File(address);


    @Override
    public List<Book> getAllBooks() throws DAOException {
        List<Book> library = new ArrayList<>();
        FileManager manager = new FileManager();
        try {
            for (String val : manager.loadDataFromFile(FILE)) {
                library.add(parsingBookFromString(val));
            }
        } catch (DAOException e) {
            throw new DAOException("Error during loading library!", e);
        }
        return library;

    }

    @Override
    public void addBook(Book book) throws DAOException {
        FileManager manager = new FileManager();
        manager.writeBookToFile(book, FILE, true);
    }

    @Override
    public void deleteBook(int id) throws DAOException {
        List<Book> books = getAllBooks();
        if (id <= books.size() & id > 0) {// {} и code convention придумали, видно, не для тебя
            books.remove(id - 1);
        }
        FileManager manager = new FileManager();
        for (Book book : books) {
            manager.writeBookToFile(book, FILE, false);
        }
    }


    private Book parsingBookFromString(String text) {
        Book book = null;
        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+(.+)@");
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            book = new Book(matcher.group(1).trim(), matcher.group(2).trim(), matcher.group(3).trim(), matcher.group(4).trim());// именуй константы, и почитай code convention
        }
        return book;
    }
}
