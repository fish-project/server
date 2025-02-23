package src.exception;

public class userNotFound extends RuntimeException {
    public userNotFound() {
        super("user not found");
    }
}
