package by.epam.christmasgift.exception;

/**
 * Created by Vika on 19.08.2014.
 */
public class CandyParsingException extends Exception {
    public CandyParsingException() {
    }

    public CandyParsingException(String message) {
        super(message);
    }

    public CandyParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public CandyParsingException(Throwable cause) {
        super(cause);
    }

    public CandyParsingException(String message, Throwable cause,
                                 boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
