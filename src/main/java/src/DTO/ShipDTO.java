package src.DTO;
import java.time.LocalDate;

public class ShipDTO {
    private String name;
    private LocalDate createdDate;
    private int crewSize;

    private String id;
    public ShipDTO(String id, String name, LocalDate createdDate, int crewSize) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.crewSize = crewSize;
    }


    // Getters và Setters
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public int getCrewSize() {
        return crewSize;
    }

    public void setCrewSize(int crewSize) {
        this.crewSize = crewSize;
    }
}
