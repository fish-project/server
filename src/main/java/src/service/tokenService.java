package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import src.exception.apiCallException;
import src.exception.customApiException;
import src.exception.validateTokenException;
import src.service.apiCaller;
import src.view.respond;

@Service
public class tokenService {
    @Autowired
    private apiCaller apiCaller;

    public void validateToken(String token) throws validateTokenException, apiCallException {
        token = token.substring(7);

        JsonNode res = apiCaller.callApi("https://sso.hcmutssps.id.vn/api/verifyToken.php?token=" + token);

        int code = res.get("code").asInt();

        if (code == 200) {
            String sub = res.get("message").get("sub").asText();
            System.out.println(sub);
            return;
        }

        throw new validateTokenException(new respond<String>(
                res.get("message").asText(),
                res.get("code").asInt(),
                res.get("error").asText()
        ));
    }
}
