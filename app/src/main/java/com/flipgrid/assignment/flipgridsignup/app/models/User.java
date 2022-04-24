package com.flipgrid.assignment.flipgridsignup.app.models;

public class User {
    private String firstName;
    private String email;
    private String website;
    private String userToken;

    public User(String firstName, String email, String website) {
        this.firstName = firstName;
        this.email = email;
        this.website = website;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public String getUserToken() {
        return userToken;
    }
}
