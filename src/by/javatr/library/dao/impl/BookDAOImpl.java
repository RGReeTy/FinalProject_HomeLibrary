package by.javatr.library.dao.impl;

import by.javatr.library.bean.Book;
import by.javatr.library.dao.BookDAO;
import by.javatr.library.dao.DAOException;
import by.javatr.library.dao.fileUtil.FileParser;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookDAOImpl implements BookDAO {

    private ArrayList<Book> books;// а если у тебя будет 2, 3, 20  реализаций различных дао - то как будешь работать с этой переменной? дублировать
    //слушай внимательноо условия, мы определяли, что каждый метод в дао самостоятельно обращается к источнику
    private String address = "src\\by\\javatr\\library\\resource\\library\\Library.txt";// src не должно присутствовать в пути к файлу, ты сможешь выполнить такой код только из ide

    public BookDAOImpl() {
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
    public void deleteBook(int id) throws DAOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose book's number for deleting:");
        int bookIDForDeleting = scanner.nextInt();// что за бред!!!, почему дао вдруг что-то начинает читать из консоли?
        if (bookIDForDeleting <= books.size() & bookIDForDeleting > 0) {// {} и code convention придумали, видно, не для тебя
            books.remove(bookIDForDeleting - 1);
        }
        saveLibraryToTXT();
    }

    public void loadDataFromFile(String address) {
        //ArrayList<Book> books = null;
        try {
            for (String val : new FileParser().loadDataFromFile(address)) {
                Book book = parsingBookFromString(val);
                if (book != null) {
                    books.add(book);
                }
            }
        } catch (DAOException e) {
            e.printStackTrace();
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
