package src.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import src.DTO.ShipDTO;

import java.io.IOException;

public interface IShipService {
    ResponseEntity<?> createShip(ShipDTO shipDTO) throws IOException;

    ResponseEntity<?> addMember(String email, String shipName);

    ResponseEntity<?> getShip(String shipName);
}
