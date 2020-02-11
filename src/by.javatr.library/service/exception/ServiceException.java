package by.javatr.library.service.exception;

public class ServiceException extends Exception {

    public ServiceException() {
        super("Error at Service layer");
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}
