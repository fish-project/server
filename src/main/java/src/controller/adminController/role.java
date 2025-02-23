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
import src.view.badRequest;
import src.view.notFound;
import src.view.success;

@RestController
@RequestMapping("/admin")
public class role {
    @Autowired
    userRepository userRepo;

    @PatchMapping("/assign")
    public ResponseEntity<?> assignRole(HttpServletRequest request, @RequestParam String role) {
        try {
            String email = (String) request.getAttribute("email");

            user user = userRepo.findById(email).orElseThrow(userNotFound::new);

            user.setRole(role);

            userRepo.save(user);

            return new  ResponseEntity<>(
                    new success<String>("Assign role successfully")
                    , HttpStatus.OK);
        } catch (userNotFound e) {
            return new ResponseEntity<>(
                    new notFound<String>(e.getMessage())
                    ,HttpStatus.NOT_FOUND
            );
        } catch (IllegalArgumentException ignored) {
            return new ResponseEntity<>(
                    new badRequest(ignored.getMessage())
                    , HttpStatus.BAD_REQUEST
            );
        }
    }
}
