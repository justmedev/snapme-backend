package dev.justme.snapmebackend;

import org.springframework.jdbc.core.JdbcTemplate;

public class DataManager {
    private static DataManager instance;
    public JdbcTemplate jdbcTemplate;

    private DataManager() {}

    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }
}
