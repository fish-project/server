package src.filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import src.exception.apiCallException;
import src.exception.customApiException;
import src.exception.validateTokenException;
import src.service.tokenService;
import src.view.respond;

@Component
public class apiFilter extends HttpFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private tokenService tokenService;

    @Override
    protected void doFilter(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain chain
    ) throws IOException, ServletException {
        String authHeaderToken = request.getHeader("Authorization");

        if(authHeaderToken == null) {
            respond<String> errorResponse = new respond<>(
                "Unauthorized",
                "Missing or Invalid token",
                401
            );

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
            return;
        }

         try {
             tokenService.validateToken(authHeaderToken);
             chain.doFilter(request, response);
         } catch(validateTokenException e) {
             respond<String> errResponse = new respond<>(
                 "Unauthorized",
                 "Missing or Invalid token",
                 401
             );

             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             response.setContentType("application/json");
             response.getWriter().write(objectMapper.writeValueAsString(errResponse));
         } catch (apiCallException e) {
             respond<String> errResponse = new respond<>(
                 "Unauthorized",
                 e.getMessage(),
                 e.getCode()
             );

             response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
             response.setContentType("application/json");
             response.getWriter().write(objectMapper.writeValueAsString(errResponse));
         }
    }
}
