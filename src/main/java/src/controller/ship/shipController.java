package src.controller.ship;

import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import src.DTO.ErrorResponseDTO;
import src.DTO.shipAddMemRequestDTO;
import src.DTO.shipRequestDTO;
import src.exception.shipNotFound;
import src.exception.shipRequestDTOException;
import src.exception.userNotFound;
import src.exception.userNotOnShip;
import src.model.ship;
import src.model.shipInvitation;
import src.repository.ShipRepository;
import src.repository.shipInvitationRepository;
import src.repository.userRepository;
import src.service.hmacService;
import src.service.mailService;
import src.service.shipService;
import src.view.badRequest;
import src.view.notFound;
import src.view.serverError;
import src.view.success;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/ship")
public class shipController {
    @Value("${config.server-domain}")
    private String serverDomain;

    @Autowired
    private mailService mailService;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    userRepository userRepository;

    @Autowired
    shipInvitationRepository invitationRepository;

    @Autowired
    hmacService hmacService;

    @PostMapping("")
    public ResponseEntity<?> func(@RequestBody shipRequestDTO shipRequestDTO, HttpServletRequest request) {
        try {
            String email = (String) request.getAttribute("email");

            src.model.ship ship = new src.model.ship(
                    shipRequestDTO.getShipName(),
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

            shipInvitation inv = invitationRepository.save(new shipInvitation(shipAddMemRequestDTO.getShipID(), hmacService.hmacSHA256(shipAddMemRequestDTO.getUserEmail())));

            mailService.sendEmail(shipAddMemRequestDTO.getUserEmail(), email + " muốn mời bạn gia nhập thuyền của họ!",
                    "<h1>Chào mừng bạn gia nhập thuyền!</h1>\n" +
                            "<p> Lưu ý thư mời chỉ có hiệu lực trong 12 giờ </p>\n" +
                            "<a href=\"" + serverDomain + "/invitation/join/" + inv.getId() + "?email=" + shipAddMemRequestDTO.getUserEmail()
                            +  "\" style=\"display: inline-block; padding: 12px 24px; font-size: 16px; font-weight: bold; text-decoration: none; color: white; background: linear-gradient(90deg, #6EC6FF, #2D9CDB); border-radius: 8px;\">Tham gia ngay</a>"
            );

            return new ResponseEntity<>(new success<String>("Đã gửi lời mời tới user: " + shipAddMemRequestDTO.getUserEmail()), HttpStatus.OK);
        } catch (shipNotFound | userNotFound e) {
            return new ResponseEntity<>(new notFound(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (shipRequestDTOException | MessagingException | UnsupportedEncodingException e) {
            return new ResponseEntity<>(new badRequest(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(new serverError(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //    Leave ship
    @Autowired
    shipService shipService;

    @GetMapping("/leave/{shipId}")
    public ResponseEntity<?> leaveShip(@PathVariable String shipId, Principal principal) {
        try {
            String currentUserEmail = principal.getName();
            shipService.leaveShip(shipId, currentUserEmail);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        } catch (shipNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        }
    }

    // Promote crew
    @GetMapping("/promote/{userId}")
    public ResponseEntity<?> promoteToOwner(@PathVariable String userId, Principal principal) {
        try {
            String currentUserEmail = principal.getName(); // Assumes JWT provides the owner's email
            shipService.promoteToOwner(userId, currentUserEmail);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO(e.getMessage()));
        } catch (userNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        } catch (userNotOnShip e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDTO(e.getMessage()));
        }
    }
}
