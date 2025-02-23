package src.model;

import io.micrometer.common.KeyValues;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import src.helper.roleHelper;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@Document(collection = "user")
@AllArgsConstructor
public class user {
    @Id
    private String email;
    private String displayName;
    private List<String> role;

    public void setDisplayName(String displayName) throws IllegalArgumentException {
        String regex = "^[a-zA-Z0-9_]{5,20}$";

        if (displayName == null || !displayName.matches(regex)) {
            throw new IllegalArgumentException("Display name must be between 5 and 20 characters and contain no special characters.");
        }

        this.displayName = displayName;
    }

    public void setRole(String role) throws IllegalArgumentException {
        if (role == null || role.trim().isEmpty() ||!roleHelper.checkRole(role)) {
            throw new IllegalArgumentException("Role cannot be null or empty or invalid.");
        }

        if (this.role.contains(role.toLowerCase())) {
            throw new IllegalArgumentException("Role '" + role + "' already exists.");
        }

        this.role.add(role.toLowerCase());
    }
}
