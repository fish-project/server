package src.exception;

import lombok.Getter;
import src.view.respond;

@Getter
public class apiCallException extends Exception {
    private int code;
    private String message;

    public apiCallException(String message, int code) {
        super(message);
        this.code = code;
        this.message = message;
    }
}
