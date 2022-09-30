package dev.justme.snapmebackend.rest;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 *
 * required arguments are:
 * <pre>
 * @String name
 * @Date birthday
 * @String[] pictureUrls
 * @String bio
 * @String[] interests
 * </pre>
 */
public class ProfileRequestModel {
    @NotNull
    private String name;
    private @NotNull Date birthday;
    @NotNull
    private String[] pictureUrls;
    @NotNull
    private String bio;
    @NotNull
    private String[] interests;


    public void setPictureUrls(String[] pictureUrls) {
        this.pictureUrls = pictureUrls;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
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

    public String getBio() {
        return bio;
    }

    public String[] getInterests() {
        return interests;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthday(@NotNull Date birthday) {
        this.birthday = birthday;
    }
}
