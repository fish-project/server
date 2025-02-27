package src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Date;

@Document(collection = "ship_invitation")
@Setter
@Getter
public class shipInvitation {
    @Id
    private String id;

    private String shipId;

    @Indexed(expireAfter = "12h")
    private Date expireAt;

    private String email;

    public shipInvitation(String shipId,  String email) {
        this.shipId = shipId;
        this.email = email;
        this.expireAt = Date.from(Instant.now().plusSeconds(12 * 60 * 60));
    }
}
