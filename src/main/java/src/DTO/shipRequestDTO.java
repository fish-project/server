package src.DTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import src.exception.shipRequestDTOException;

@Getter
@AllArgsConstructor
public class shipRequestDTO {
    private int crewSize;
    private String shipName;

    public void setName(String shipName) {
        if (!shipName.matches("^[a-zA-Z0-9]{7,15}$")) {
            throw new shipRequestDTOException("Tên phải có 7-15 ký tự, không chứa ký tự đặc biệt!");
        }
        this.shipName = shipName;
    }
    public void setCrewSize(int crewSize) {
        if (crewSize < 1 || crewSize > 50) {
            throw  new shipRequestDTOException("Số lượng thành viên phải từ 1 tới 50");
        }
        this.crewSize = crewSize;
    }
}
