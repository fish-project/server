package src.DTO;
import java.time.LocalDate;
import java.util.List;

public class ShipDTO {
    private String shipName;
    private LocalDate createdDate;


    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private List<String> member; // Danh sách email thành viên

    public List<String> getMember() {
        return member;
    }

    public void setMember(List<String> member) {
        this.member = member;
    }

    private String id;
    public ShipDTO(String id, String shipName, LocalDate createdDate,List<String>member) {
        this.id = id;
        this.shipName = shipName;
        this.createdDate = createdDate;
        this.member = member;

    }


// Getters và Setters


    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }



    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }



}
