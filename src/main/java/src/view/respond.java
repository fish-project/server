package src.view;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;

@Getter
@Setter
public class respond<T>  {
    private final String timestamp = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    private String status;
    private int code;
    private T message;

    public respond(T message, int code, String status) {
        this.message = message;
        this.code = code;
        this.status = status;
    }
}
