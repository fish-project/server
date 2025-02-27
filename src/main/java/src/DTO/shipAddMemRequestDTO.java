package src.DTO;

import lombok.Getter;
import lombok.Setter;
import src.exception.shipRequestDTOException;

@Getter
public class shipAddMemRequestDTO {
    private String shipID;
    private String userEmail;
}
