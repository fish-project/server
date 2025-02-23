package src.exception;

import src.view.respond;

public class validateTokenException extends Exception {
    private respond<String> respond;

    public validateTokenException(respond<String> res) {
        super(res.getMessage());
        this.respond = res;
    }
}
