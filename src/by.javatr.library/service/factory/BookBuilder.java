package by.javatr.library.service.factory;

import by.javatr.library.bean.Book;

public class BookBuilder {
    private Book book;


    public BookBuilder() {
        this.book = new Book();
    }

    public BookBuilder withTitle(String bookName) {
        book.setBookName(bookName);
        return this;
    }

    public BookBuilder withAuthors(String author) {
        book.setAuthor(author);
        return this;
    }

    public BookBuilder withTypeOfBook(String typeOfBook) {
        book.setTypeOfBook(typeOfBook);
        return this;
    }

    public BookBuilder withInfo(String aboutBook) {
        book.setAboutBook(aboutBook);
        return this;
    }

    public Book build() {
        return book;
    }
}
