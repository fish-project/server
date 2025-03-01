package src.controller.adminController;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.exception.userNotFound;
import src.model.ship;
import src.model.user;
import src.repository.ShipRepository;
import src.service.GetUserService;
import src.service.userService;
import src.view.notFound;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private GetUserService getUserService;

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private userService userService;

    @GetMapping("/getUser")
    public ResponseEntity<Page<?>> getUser(HttpServletRequest request,
                                           @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<user> users = getUserService.getUsers(page);
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/deleteUser/{email}")
    public ResponseEntity<?> deleteUser(HttpServletRequest request,
                                        @PathVariable String email) {
        try {
            List<ship> ships = shipRepository.findByShipOwner(email);
            for (ship owningShip: ships) shipRepository.delete(owningShip);
            userService.deleteUser(email);
            return ResponseEntity.ok(email + " has been deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting user: " + e.getMessage());
        }
    }
}
