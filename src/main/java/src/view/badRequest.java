package src.view;

public class badRequest extends respond<String> {

    public badRequest(String message) {
        super(message, 400, "Bad Request");
    }
}
