package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${config.sso-domain.verifyTokenApi}")
    private String verifyTokenApi;

    public String validateToken(String token) throws apiCallException {
        token = token.substring(7);

        JsonNode res = apiCaller.callApi(verifyTokenApi + "?token=" + token);

        int code = res.get("code").asInt();

        return res.get("message").get("sub").asText();
    }
}
