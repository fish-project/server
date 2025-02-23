package src.view;

public class success<T> extends respond<T> {
    public success(T message) {
        super(message, 200,"success");
    }
}
