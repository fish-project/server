package src.controller.ship;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import src.DTO.shipAddMemRequestDTO;
import src.DTO.shipRequestDTO;
import src.exception.shipNotFound;
import src.exception.shipRequestDTOException;
import src.exception.userNotFound;
import src.model.ship;
import src.repository.ShipRepository;
import src.repository.userRepository;
import src.view.badRequest;
import src.view.notFound;
import src.view.serverError;
import src.view.success;

import java.util.List;

@RestController
@RequestMapping("/ship")
public class shipController {
    @Autowired
    ShipRepository shipRepository;

    @Autowired
    userRepository userRepository;

    @PostMapping("")
    public ResponseEntity<?> func(@RequestBody shipRequestDTO shipRequestDTO, HttpServletRequest request) {
        try {
            String email = (String) request.getAttribute("email");

            src.model.ship ship = new src.model.ship(
                    shipRequestDTO.getShipName(),
                    shipRequestDTO.getCrewSize(),
                    email
            );
            src.model.ship savedShip = shipRepository.save(ship);

            return new ResponseEntity<>(new success<String>("Tạo thuyền thành công"), HttpStatus.OK);
        } catch (shipRequestDTOException e) {
            return new ResponseEntity<>(new badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new serverError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getShip(HttpServletRequest request) {
        try {
            String email = (String) request.getAttribute("email");

            List<ship> shipList = shipRepository.findByShipOwner(email);
            return new ResponseEntity<>(new success<List<ship>>(shipList), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new serverError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/addMember")
    public ResponseEntity<?> addMember(@RequestBody shipAddMemRequestDTO shipAddMemRequestDTO, HttpServletRequest request) {
        try {
            String email = (String) request.getAttribute("email");

            if(shipAddMemRequestDTO.getUserEmail().equals(email)) {
                throw new shipRequestDTOException("Can not add yourself into your ship");
            }

            if(!userRepository.existsById(shipAddMemRequestDTO.getUserEmail())) {
                throw new userNotFound(shipAddMemRequestDTO.getUserEmail());
            }

            ship ship = shipRepository.findById(shipAddMemRequestDTO.getShipID())
                    .orElseThrow(() -> new shipNotFound("ship not found"));

            if(ship.getMember().contains(shipAddMemRequestDTO.getUserEmail())) {
                throw new shipRequestDTOException("user already exists in the ship");
            }

            return new ResponseEntity<>(new success<ship>(ship), HttpStatus.OK);
        } catch (shipNotFound | userNotFound e) {
            return new ResponseEntity<>(new notFound<String>(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (shipRequestDTOException e) {
            return new ResponseEntity<>(new badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new serverError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
