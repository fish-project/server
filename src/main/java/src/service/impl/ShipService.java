package src.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import src.DTO.ShipDTO;
import src.model.Ship;
import src.repository.ShipRepository;
import src.service.IShipService;

import java.io.IOException;

@Service
public class ShipService implements IShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public ResponseEntity<?> createShip(ShipDTO shipDTO) throws IOException {
        try {

            Ship ship = new Ship(
                    shipDTO.getName(),
                    shipDTO.getCreatedDate(),
                    shipDTO.getCrewSize()
            );

            // Lưu vào MongoDB (MongoDB sẽ tự tạo ID)
            Ship savedShip = shipRepository.save(ship);

            // Trả về DTO của ship đã lưu
            ShipDTO responseDTO = new ShipDTO(
                    savedShip.getId(),
                    savedShip.getName(),
                    savedShip.getCreatedDate(),
                    savedShip.getCrewSize()
            );
            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Lỗi dữ liệu đầu vào: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi tạo ship: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }
    }
}
