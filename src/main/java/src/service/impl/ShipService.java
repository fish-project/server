package src.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import src.DTO.ShipDTO;
import src.model.Ship;
import src.repository.ShipRepository;
import src.service.IShipService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class ShipService implements IShipService {

    @Autowired
    private ShipRepository shipRepository;

    @Override
    public ResponseEntity<?> createShip(ShipDTO shipDTO) throws IOException {
        try {
            // Tìm thuyền theo tên
            Ship shipsearch = shipRepository.findByshipName(shipDTO.getShipName());

            // Nếu thuyền đã tồn tại, trả về lỗi ngay lập tức
            if (shipsearch != null) {
                return ResponseEntity.badRequest().body("Thuyền có tên '" + shipDTO.getShipName() + "' đã tồn tại.");
            }

            // Nếu chưa tồn tại, tạo mới thuyền
            LocalDate createdDate = LocalDate.now();
            Ship ship = new Ship(
                    shipDTO.getShipName(),
                    createdDate,
                    shipDTO.getMember()
            );


            Ship savedShip = shipRepository.save(ship);




            ShipDTO responseDTO = new ShipDTO(
                    savedShip.getId(),
                    savedShip.getShipName(),
                    savedShip.getCreatedDate(),
                    savedShip.getMember()
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

    @Override
    public ResponseEntity<?> addMember(String email, String shipName) {
        try {
            Ship ship = shipRepository.findByshipName(shipName);
            if (ship == null) {
                return ResponseEntity.badRequest().body("Không tìm thấy thuyền với tên: " + shipName);
            }
            if (ship.getMember() == null) {
                ship.setMember(new ArrayList<>()); // Nếu danh sách members chưa tồn tại, tạo danh sách mới
            }

            if (ship.getMember().contains(email)) {
                return ResponseEntity.badRequest().body("Email '" + email + "' đã có trong danh sách thành viên của thuyền '" + shipName + "'.");
            }

            ship.getMember().add(email);


            Ship savedShip = shipRepository.save(ship);

            ShipDTO responseDTO = new ShipDTO(
                    savedShip.getId(),
                    savedShip.getShipName(),
                    savedShip.getCreatedDate(),
                    new ArrayList<>(savedShip.getMember())
            );
            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Lỗi dữ liệu đầu vào: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi tạo member: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }

    }
    @Override
    public ResponseEntity<?> getShip(String shipName) {
        try {
            Ship ship = shipRepository.findByshipName(shipName);
            if (ship == null) {
                return ResponseEntity.badRequest().body("Không tìm thấy thuyền với tên: " + shipName);
            }

            ShipDTO responseDTO = new ShipDTO(
                    ship.getId(),
                    ship.getShipName(),
                    ship.getCreatedDate(),
                    new ArrayList<>(ship.getMember())
            );

            return ResponseEntity.ok(responseDTO);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Lỗi dữ liệu đầu vào: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("Lỗi khi truy vấn thông tin thuyền: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Lỗi hệ thống: " + e.getMessage());
        }


    }
}
