package dev.justme.snapmebackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.xml.crypto.Data;
import java.util.UUID;

// @SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class SnapmeBackendApplication implements CommandLineRunner {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(SnapmeBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        DataManager.getInstance().jdbcTemplate = jdbcTemplate;

        /*String uuid = UUID.randomUUID().toString();
        String name = "Emu";
        int age = 99;
        String[] pictureUrls = {"1", "2", "3"};
        int friends = 0;
        String bio = "This is myy bio uwuw";
        String[] interests = {"Music", "Gaming"};

        int rows = jdbcTemplate.update("INSERT INTO profiles (uuid, name, age, pictureurls, friends, bio, interests) VALUES (?,?,?,?,?,?,?);", uuid, name, age, pictureUrls, friends, bio, interests);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }*/
    }
}