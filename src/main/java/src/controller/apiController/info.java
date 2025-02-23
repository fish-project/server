package src.controller.apiController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.exception.userNotFound;
import src.model.user;
import src.repository.userRepository;
import src.service.userService;
import src.view.badRequest;
import src.view.respond;
import src.view.success;

@RestController
@RequestMapping("/api/info")
public class info {
    @Autowired
    userRepository userRepo;

    @Autowired
    userService service;

    @GetMapping
    public ResponseEntity<?> getUserInfo(HttpServletRequest request) {
        String username = (String) request.getAttribute("email");

        try {
            user user = userRepo.findById(username).orElseThrow(userNotFound::new);

            return new ResponseEntity<>(
                    new success<user>(user),
                    HttpStatus.OK
            );
        }   catch (userNotFound e) {
            return new ResponseEntity<>(
                    new respond<String>(
                            "user not found",
                            404,
                            "not found"
                    ),
                    HttpStatus.NOT_FOUND
            );
        }
    }

    @PatchMapping("/changeDisplayName")
    public ResponseEntity<?> changeDisplayName(HttpServletRequest request, @RequestParam String name) {
        try {
            String email = (String) request.getAttribute("email");
            service.updateDisplayName(email, name);

            return new ResponseEntity<>(
                    new success<String>("success"),
                    HttpStatus.OK
            );
        } catch (userNotFound userNotFound) {
            return new ResponseEntity<>(
                    new respond<String>(
                        "user not found",
                            404,
                            "not found"
                    ),
                    HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            return new ResponseEntity<>(
                    new badRequest(e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
