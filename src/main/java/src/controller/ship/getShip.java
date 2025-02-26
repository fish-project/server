package src.controller.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.service.IShipService;

@RestController
@RequestMapping ("/ship")
public class getShip {
    @Autowired
    private IShipService shipService;
    @GetMapping("/get")
    public ResponseEntity<?> getShip(@RequestParam String shipName) {
        try {
            return shipService.getShip(shipName);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Lỗi dữ liệu đầu vào: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi truy vấn thông tin thuyền: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }

}
