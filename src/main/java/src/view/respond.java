package src.view;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class respond<T> {
    private final String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    private T message;
    private String error;
    private int status;

    public respond(T message, int status, String error) {
        this.message = message;
        this.status = status;
        this.error = error;
    }
}
