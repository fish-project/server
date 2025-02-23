package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;

import src.exception.validateTokenException;
import src.service.apiCaller;
import src.view.respond;

@Service
public class tokenService {
    @Autowired
    private apiCaller apiCaller;

    public void validateToken(String token) throws validateTokenException {
        token = token.substring(7);

        JsonNode res = apiCaller.callApi("https://sso.hcmutssps.id.vn/api/verifyToken.php?token=" + token);

        int code = res.get("code").asInt();

        if(code == 200) {
            String sub = res.get("message").get("sub").asText();
            System.out.println(sub);
        } 

        throw validateTokenException(new respond<String>(

        ));
    }
}
