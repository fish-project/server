package src.view;

public class forbiden extends respond<String>{

    public forbiden(String message) {
        super(message, 403, "Forbidden");
    }
}
