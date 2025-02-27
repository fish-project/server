package src.exception;

public class shipNotFound extends RuntimeException {
    public shipNotFound(String message) {
        super(message);
    }
}
