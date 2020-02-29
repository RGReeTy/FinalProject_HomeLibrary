package by.javatr.library.dao;

public class DAOException extends Exception {

    public DAOException() {
        super();
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable e) {
        super("Error at DAO layer" + message, e);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }
}
