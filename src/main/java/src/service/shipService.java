package src.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import src.exception.shipNotFound;
import src.exception.userNotFound;
import src.exception.userNotOnShip;
import src.model.ship;
import src.model.user;
import src.repository.ShipRepository;

@Service
public class shipService {

    @Autowired
    private ShipRepository shipRepository;

    @Autowired
    private userService userService;

    // Leave ship
    public void leaveShip(String shipId, String userEmail) {
        ship ship = shipRepository.findById(shipId)
                .orElseThrow(() -> new shipNotFound("Ship not found with ID: " + shipId));

        // Check if the user is the owner
        if (ship.getShipOwner().equals(userEmail)) {
            throw new IllegalStateException("Owner cannot leave the ship");
        }

        // Remove the user from the member list
        if (ship.getMember().contains(userEmail)) {
            ship.getMember().remove(userEmail);
            shipRepository.save(ship);
        } else {
            throw new IllegalStateException("User is not a member of this ship");
        }
    }

    // Promote crew
    public void promoteToOwner(String userId, String ownerEmail) {
        // Find the ship owned by the current user
        ship ship = shipRepository.findByShipOwner(ownerEmail)
                .stream()
                .findFirst()
                .orElseThrow(() -> new shipNotFound("Ship not found for owner: " + ownerEmail));

        // Verify the user exists
        user newOwner = userService.findByEmail(userId);
        if (newOwner == null) {
            throw new userNotFound("User not found with email: " + userId);
        }

        // Check if the user is a crew member of this ship
        if (!ship.getMember().contains(userId)) {
            throw new userNotOnShip("User is not a crew member of this ship");
        }

        // Promote the user to owner and demote the current owner to crew
        ship.getMember().add(ownerEmail); // Add current owner to crew
        ship.setShipOwner(userId); // Set new owner
        ship.getMember().remove(userId); // Remove new owner from crew 
        shipRepository.save(ship);
    }
}
