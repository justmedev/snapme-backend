package dev.justme.snapmebackend.rest;

import javax.validation.constraints.NotNull;

public class ProfileRequestModel {
    @NotNull
    private String name;
    @NotNull
    private int birthdayTimestamp;
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

    public int getBirthdayTimestamp() {
        return birthdayTimestamp;
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

    public void setBirthdayTimestamp(int birthdayTimestamp) {
        this.birthdayTimestamp = birthdayTimestamp;
    }
}
