package utils;

import java.io.Serializable;

/**
 * Created by robert on 13.11.2016.
 */
public class InterpretorException extends RuntimeException implements  Serializable {
    public InterpretorException(String s) {
        super(s);
    }

    public InterpretorException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
