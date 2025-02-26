package src.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "ship") // Định nghĩa collection trong MongoDB
public class Ship {
    @Id
    private String id;
    private String shipName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate createdDate;

    private List<String> member; // Danh sách email thành viên

    public List<String> getMember() {
        return member;
    }

    public void setMember(List<String> member) {
        this.member = member;
    }

    // Constructor có tham số
    public Ship(String shipName, LocalDate createdDate, List<String> member) {
        this.shipName = shipName;
        this.createdDate = createdDate;
        this.member = member;

    }

    // Getters & Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }



}
