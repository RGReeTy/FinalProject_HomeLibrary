package by.javatr.library.bean;

import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private String bookName;
    private String author;
    private String typeOfBook;
    private String aboutBook;
    private static int genID = 0;
    private int id = 0;


    public Book() {
    }

    public Book(String bookName, String author, String typeOfBook, String aboutBook) {
        this.bookName = bookName;
        this.author = author;
        this.typeOfBook = typeOfBook;
        this.aboutBook = aboutBook;
        this.id = ++genID;
    }

    public final String getBookName() {
        return bookName;
    }

    public final void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public final String getAuthor() {
        return author;
    }

    public final void setAuthor(String author) {
        this.author = author;
    }

    public final String getTypeOfBook() {
        return typeOfBook;
    }

    public final void setTypeOfBook(String typeOfBook) {
        this.typeOfBook = typeOfBook;
    }

    public final String getAboutBook() {
        return aboutBook;
    }

    public final void setAboutBook(String aboutBook) {
        this.aboutBook = aboutBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookName, book.bookName) &&
                Objects.equals(author, book.author) &&
                Objects.equals(typeOfBook, book.typeOfBook) &&
                Objects.equals(aboutBook, book.aboutBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, author, typeOfBook, aboutBook);
    }

    @Override
    public String toString() {
        return id + "\t" + bookName +
                ",\tAuthor - " + author +
                ",\tType of book - " + typeOfBook +
                ",\tAbout - " + aboutBook;
    }
}
