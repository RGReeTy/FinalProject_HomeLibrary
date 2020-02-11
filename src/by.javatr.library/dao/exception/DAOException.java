package by.javatr.library.dao.exception;

public class DAOException extends Exception {
    //private String message = "Error at DAO layer";

    public DAOException(String message) {
        super(message);
    }

    public DAOException() {
        super("Error at DAO layer");
    }

    public DAOException(String message, Exception e) {
        super("Error at DAO layer" + message, e);
    }
}
