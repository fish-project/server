package src.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import src.exception.apiCallException;


@Service
public class apiCaller {
    private static final RestTemplate restTemplate = new RestTemplate();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public JsonNode callApi(String url) throws apiCallException {
        try {
            String response = restTemplate.getForObject(url, String.class);
            return objectMapper.readTree(response); 
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            String errorMessage = "Fail to fetch from SSO";
            try {
                JsonNode errorResponse = objectMapper.readTree(e.getResponseBodyAsString());
                if (errorResponse.has("message")) {
                    errorMessage = errorResponse.get("message").asText();
                }
            } catch (Exception parseException) {
                // Do nothing
            } finally {
                throw new apiCallException(errorMessage, e.getStatusCode().value());
            }
        } catch (Exception e) {
            throw new apiCallException("Fail to fetch from SSO", 500);
        }
    }
}
