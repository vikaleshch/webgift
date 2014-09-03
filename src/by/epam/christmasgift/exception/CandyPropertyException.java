package by.epam.christmasgift.exception;

/**
 * Created by Vika on 19.08.2014.
 */
public class CandyPropertyException extends Exception {
    public CandyPropertyException() {
    }

    public CandyPropertyException(String message) {
        super(message);
    }

    public CandyPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public CandyPropertyException(Throwable cause) {
        super(cause);
    }

    public CandyPropertyException(String message, Throwable cause,
                                  boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
