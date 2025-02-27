package src.view;

public class serverError extends respond<String>{
    public serverError(String message) {
        super(message, 500, "Internal Server Error");
    }
}
