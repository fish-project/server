package src.helper;

import java.util.List;

public class roleHelper {
    private static final List<String> ROLE_MAP = List.of(
            "user", "admin", "ship_manager"
    );

    public static boolean checkRole(String role) {
        return ROLE_MAP.contains(role);
    }
}
