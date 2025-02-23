package src.controller.adminController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import src.exception.userNotFound;
import src.model.user;
import src.repository.userRepository;
import src.service.userService;
import src.view.badRequest;
import src.view.notFound;
import src.view.success;

@RestController
@RequestMapping("/admin")
public class role {
    @Autowired
    userService userservice;

    @Autowired
    userRepository userRepo;

    @PatchMapping("/assign")
    public ResponseEntity<?> assignRole(HttpServletRequest request, @RequestParam String role, @RequestParam String email) {
        try {
            userservice.assignRole(email, role);

            return new  ResponseEntity<>(
                    new success<String>("Assign role successfully")
                    , HttpStatus.OK);
        } catch (userNotFound e) {
            return new ResponseEntity<>(
                    new notFound<String>(e.getMessage())
                    ,HttpStatus.NOT_FOUND
            );
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(
                    new badRequest(e.getMessage())
                    , HttpStatus.BAD_REQUEST
            );
        }
    }
}
