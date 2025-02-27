package src.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "ship")
public class ship {
    @Id
    private String id;
    private String createdDate = ZonedDateTime.now().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);

    private String shipOwner;
    private String shipName;
    private int crewSize;
    private List<String> member = new ArrayList<String>();

    public ship(String shipName, int crewSize, String shipOwner) {
        this.crewSize = crewSize;
        this.shipOwner = shipOwner;
        this.shipName = shipName;
    }
}