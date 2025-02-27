package src.controller.invitation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.exception.badRequest;
import src.exception.notFoundException;
import src.exception.shipFullException;
import src.model.ship;
import src.model.shipInvitation;
import src.repository.ShipRepository;
import src.repository.shipInvitationRepository;
import src.service.hmacService;
import src.view.serverError;
import src.view.success;

@RestController
@RequestMapping("/invitation")
public class invitationController {
    @Autowired
    hmacService hmacService;

    @Autowired
    shipInvitationRepository shipInvitationRepository;

    @Autowired
    ShipRepository shipRepository;

    @GetMapping("/join/{id}")
    public ResponseEntity<?> join(@PathVariable("id") String id, @RequestParam String email, HttpServletRequest request) throws Exception {
        shipInvitation shipInv;

        try {
            shipInv = shipInvitationRepository.findById(id).orElseThrow(
                    () -> new notFoundException("ShipInvitation not found")
            );
        } catch (notFoundException e) {
            return new ResponseEntity<>(new src.view.notFound(e.getMessage()), HttpStatus.NOT_FOUND);
        }

        try {
            String hashEmail = hmacService.hmacSHA256(email);
            if(!hashEmail.equals(shipInv.getEmail())) {
                throw new badRequest("Wrong email");
            }

            ship ship = shipRepository.findById(shipInv.getShipId()).orElseThrow(
                    () -> new notFoundException("Ship not found")
            );

            if(ship.getMember().contains(email)) {
                throw new badRequest("User already in ship");
            }

            ship.getMember().add(email);

            shipRepository.save(ship);

            return new ResponseEntity<>(new success<String>("successfully join ship"), HttpStatus.OK);
        } catch (notFoundException e) {
            return new ResponseEntity<>(new src.view.notFound(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (badRequest | shipFullException e) {
          return new ResponseEntity<>(new src.view.badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new serverError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        } finally {
            shipInvitationRepository.delete(shipInv);
        }
    }
}
