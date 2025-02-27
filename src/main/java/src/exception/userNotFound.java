package src.exception;

public class userNotFound extends RuntimeException {
    public userNotFound(String userEmail) {
        super("user not found: " + userEmail);
    }
}
