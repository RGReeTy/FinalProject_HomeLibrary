package by.javatr.library.dao.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.FileDAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDAOImpl implements BookDAO, FileDAO {

    private ArrayList<Book> books;// а если у тебя будет 2, 3, 20  реализаций различных дао - то как будешь работать с этой переменной? дублировать
    //слушай внимательноо условия, мы определяли, что каждый метод в дао самостоятельно обращается к источнику
    private String address = "src\\by\\javatr\\library\\resource\\library\\Library.txt";// src не должно присутствовать в пути к файлу, ты сможешь выполнить такой код только из ide

    public BookDAOImpl() throws DAOException {
        books = new ArrayList<>();
        loadDataFromFile(address);
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    @Override
    public void addBook(Book book) {
        books.add(book);
    }

    @Override
    public void deleteBook() throws DAOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose book's number for deleting:");
        int bookIDForDeleting = scanner.nextInt();// что за бред!!!, почему дао вдруг что-то начинает читать из консоли?
        if (bookIDForDeleting <= books.size() & bookIDForDeleting > 0)// {} и code convention придумали, видно, не для тебя
            books.remove(bookIDForDeleting - 1);
        saveLibraryToTXT();
    }

    @Override
    public void loadDataFromFile(String address) throws DAOException {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(address), "UTF-8");// класс FileReader, читай чуть больше, чем тебе рассказали на лекциях
            // а close() на этом объекте Пушкин будет вызывать?
        } catch (FileNotFoundException e) {
            throw new DAOException("Error at loading Library", e);
        }
        String BooksInString = scanner.useDelimiter("\\A").next(); // \\A - The beginning of the input(docs.oracle.com)

        Pattern pattern = Pattern.compile("([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+([а-яА-яA-Za-z0-9 .,?!\"]+)\\+(.+)@");
        Matcher matcher = pattern.matcher(BooksInString);

        while (matcher.find()) {
            addBook(new Book(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4)));// именуй константы, и почитай code convention
        }
    }

    public void saveLibraryToTXT() throws DAOException {
        try (FileWriter writer = new FileWriter(address, false)) {
            for (Book book : books) {
                writer.append(System.lineSeparator());
                writer.write(book.getBookName() + " + " + book.getAuthor() + " + " + book.getTypeOfBook() + " + " + book.getAboutBook() + "@");
            }
            writer.flush();
        } catch (IOException ex) {
            throw new DAOException("Error at saving Library", ex);
        }
    }
}
