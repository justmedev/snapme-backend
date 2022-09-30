package dev.justme.snapmebackend.rest;

import dev.justme.snapmebackend.DataManager;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class Profile {
    private JdbcTemplate jdbcTemplate;

    private final String uuid;
    private final String name;
    private final @NotNull Date birthday;
    private final String[] pictureUrls;
    private final int friends;
    private final String bio;
    private final String[] interests;

    public Profile(String uuid, String name, @NotNull Date birthday, String[] pictureUrls, int friends, String bio, String[] interests) {
        this.uuid = uuid;
        this.name = name;
        this.birthday = birthday;
        this.pictureUrls = pictureUrls;
        this.friends = friends;
        this.bio = bio;
        this.interests = interests;

        this.jdbcTemplate = DataManager.getInstance().jdbcTemplate;
    }

    /**
     * @return amount of rows affected
     */
    public int insertIntoDatabase() {
        return jdbcTemplate.update("INSERT INTO profiles (uuid, name, birthday, pictureurls, friends, bio, interests) VALUES (?,?,?,?,?,?,?);", uuid, name, birthday, pictureUrls, friends, bio, interests);
    }

    public String getUuid() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public @NotNull Date getBirthday() {
        return birthday;
    }

    public String[] getPictureUrls() {
        return pictureUrls;
    }

    public int getFriends() {
        return friends;
    }

    public String getBio() {
        return bio;
    }

    public String[] getInterests() {
        return interests;
    }
}
