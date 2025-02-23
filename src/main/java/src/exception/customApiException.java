package src.exception;

import lombok.Getter;
import src.view.respond;

@Getter
public class customApiException extends Exception {
    private src.view.respond<String> respond;

    public customApiException(respond<String> res) {
        super(res.getMessage());
        this.respond = res;
    }
}
