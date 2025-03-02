package src.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "Ship")
public class Ship {
    @Id
    private String id;
    private String createdDate = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);


    private String shipOwner;
    private String shipName;
    private List<String> member = new ArrayList<String>();

    public void addMember(String email) {
        member.add(email);
    }

    public ship(String shipName, String shipOwner) {
        this.shipOwner = shipOwner;
        this.shipName = shipName;
    }
}