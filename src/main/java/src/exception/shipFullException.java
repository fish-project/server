package src.exception;

public class shipFullException extends  Exception {
    public shipFullException() {
        super("ship is already full of members");
    }
}
