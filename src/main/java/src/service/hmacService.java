package src.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import src.helper.hmacHelper;

@Service
public class hmacService {
    @Value("${config.hmac_key}")
    private String secretKey;

    public String hmacSHA256(String data) throws Exception {
        return hmacHelper.hmacSHA256(secretKey, data);
    }
}
