package dev.justme.snapmebackend.rest;

import java.sql.*;
import java.util.*;
import java.util.Date;

import dev.justme.snapmebackend.DataManager;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
public class ProfileController {
    @PutMapping("/profile")
    public Profile putProfile(@RequestBody @Valid ProfileRequestModel profileRequestModel) {
        String uuid = UUID.randomUUID().toString();
        Profile profile = new Profile(uuid, profileRequestModel.getName(), profileRequestModel.getBirthday(), new String[]{}, 0, profileRequestModel.getBio(), profileRequestModel.getInterests());

        int affectedRows = profile.insertIntoDatabase();
        if (affectedRows != 1) System.out.printf("Something went wrong... %s rows affected%n", affectedRows);

        System.out.printf("%s Rows affected with insert into operation called on profiles", affectedRows);
        return profile;
    }

    @DeleteMapping("/profile")
    public void deleteProfile(@RequestParam(value = "uuid") String uuid) {
        String sql = "DELETE FROM profiles WHERE uuid = ?;";
        Object[] args = new Object[] {uuid};
        DataManager.getInstance().jdbcTemplate.update(sql, args);
    }

    @GetMapping("/profile")
    public Profile getProfile(@RequestParam(value = "uuid", required = false) String uuid) {
        try {
            Connection conn = Objects.requireNonNull(DataManager.getInstance().jdbcTemplate.getDataSource()).getConnection();
            PreparedStatement statement;
            if (uuid == null) {
                statement = conn.prepareStatement("SELECT * FROM profiles ORDER BY random() LIMIT 1");
            } else {
                statement = conn.prepareStatement("SELECT * FROM profiles WHERE uuid = ?;");
                statement.setString(1, uuid);
            }

            ResultSet queryResult = statement.executeQuery();
            if (queryResult.next()) {
                String rowUUID = queryResult.getString("uuid");
                String name = queryResult.getString("name");
                String bio = queryResult.getString("bio");
                @NotNull Date birthdayTimestamp = queryResult.getDate("birthday");
                int friends = queryResult.getInt("friends");
                String[] interests = (String[]) queryResult.getArray("interests").getArray();
                String[] pictureUrls = (String[]) queryResult.getArray("pictureurls").getArray();

                return new Profile(rowUUID, name, birthdayTimestamp, pictureUrls, friends, bio, interests);
            }

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "entity not found"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}