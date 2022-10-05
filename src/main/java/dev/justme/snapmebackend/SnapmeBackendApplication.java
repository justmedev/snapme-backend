package dev.justme.snapmebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;

// @SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SnapmeBackendApplication implements CommandLineRunner  {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(SnapmeBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Server started. Reachable at http://%s:%s", environment.getProperty("server.address"), environment.getProperty("server.port"));
        DataManager.getInstance().jdbcTemplate = jdbcTemplate;
    }
}