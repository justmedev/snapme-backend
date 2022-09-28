package dev.justme.snapmebackend.rest;

import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import dev.justme.snapmebackend.DataManager;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.crypto.Data;

@RestController
public class ProfileController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @PutMapping("/profile")
    public Profile putProfile(@RequestBody @Valid ProfileRequestModel profileRequestModel) {
        String uuid = UUID.randomUUID().toString();
        Profile profile = new Profile(uuid, profileRequestModel.getName(), profileRequestModel.getBirthdayTimestamp(), new String[]{}, 0, profileRequestModel.getBio(), profileRequestModel.getInterests());

        int affectedRows = profile.insertIntoDatabase();
        if (affectedRows != 1) System.out.printf("Something went wrong... %s rows affected%n", affectedRows);

        System.out.printf("%s Rows affected with insert into operation called on profiles", affectedRows);
        return profile;
    }

    @GetMapping("/profile")
    public Profile getProfile(@RequestParam(value = "uuid") String uuid) {
        try {
            Connection conn = Objects.requireNonNull(DataManager.getInstance().jdbcTemplate.getDataSource()).getConnection();
            PreparedStatement statement = conn.prepareStatement("SELECT * FROM profiles WHERE uuid == ?;");
            statement.setString(1, uuid);
            ResultSet queryResult = statement.executeQuery();

            String name = queryResult.getString("name");
            int birthdayTimestamp = queryResult.getInt("birthday_timestamp");
            Array pictureUrls = queryResult.getArray("pictureurls");
            int friends = queryResult.getInt("friends");
            String bio = queryResult.getString("bio");
            Array interests = queryResult.getArray("interests");

            Profile profile = new Profile(uuid, name, birthdayTimestamp, Arrays.asList(pictureUrls), friends, bio, interests);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // DataManager.getInstance().jdbcTemplate.query("SELECT * FROM profiles WHERE uuid == ?", uuid);
    }
}