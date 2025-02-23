package src.exception;

import lombok.Getter;
import src.view.respond;

@Getter
public class validateTokenException extends customApiException {
    private respond<String> respond;

    public validateTokenException(respond<String> res) {
        super(res);
    }
}
