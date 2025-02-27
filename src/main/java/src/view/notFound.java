package src.view;

public class notFound extends respond<String>{
    public notFound(String message) {
        super(message, 404, "not found");
    }
}
