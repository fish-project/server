package src.controller.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import src.DTO.ShipDTO;
import src.service.IShipService;

@RestController
@RequestMapping("/ship")
public class createShip {
    @Autowired
    private IShipService shipService;

    @PostMapping("/create")
    public ResponseEntity<?> createShip(@ModelAttribute ShipDTO shipDTO) {
        try {
            return shipService.createShip(shipDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi tạo ship: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
    @PostMapping("/addmember")
    public ResponseEntity<?> addMember(@ModelAttribute ShipDTO shipDTO) {
        try{
            return shipService.addMember(shipDTO.getEmail(), shipDTO.getShipName());
        } catch(RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi thêm memeber: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}
