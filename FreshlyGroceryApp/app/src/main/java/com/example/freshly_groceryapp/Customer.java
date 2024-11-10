package com.example.freshly_groceryapp;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "customer")
public class Customer {
    private String name;
    @PrimaryKey
    @NonNull
    private String email;
    private String password;
    private String  profilePicture;
    private String gender;

    public Customer(String name, String email, String password, String profilePicture, String gender) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profilePicture = profilePicture;
        this.gender = gender;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
