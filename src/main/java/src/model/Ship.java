package src.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "ships") // Định nghĩa collection trong MongoDB
public class Ship {
    @Id
    private String id;
    private String name;
    private LocalDate createdDate;
    private int crewSize;



    // Constructor có tham số
    public Ship(String name, LocalDate createdDate, int crewSize) {
        this.name = name;
        this.createdDate = createdDate;
        this.crewSize = crewSize;
    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
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
