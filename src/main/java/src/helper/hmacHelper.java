package src.helper;

import org.springframework.beans.factory.annotation.Value;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class hmacHelper {
    public static String hmacSHA256(String key, String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);

        byte[] hashBytes = mac.doFinal(data.getBytes());

        return Base64.getEncoder().encodeToString(hashBytes);
    }
}
