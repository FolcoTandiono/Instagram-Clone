package com.example.folco.instagramclone.models;

import android.graphics.Bitmap;
import android.util.Pair;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class User {
    private static int userIdIncrement = 1;
    public static List<User> listUser;
    private int id;
    public String name;
    public String username;
    public String phonenumber;
    public String email;
    private String password;

    public Bitmap profilePicture;
    public List<Pair<Timestamp, Bitmap>> posts;

    public List<User> followers;
    public List<User> following;

    public static int getUserIdIncrement() {
        return userIdIncrement;
    }

    public static void setUserIdIncrement(int userIdIncrement) {
        User.userIdIncrement = userIdIncrement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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

    public Bitmap getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(Bitmap profilePicture) {
        this.profilePicture = profilePicture;
    }

    public static List<User> getListUser() {
        return listUser;
    }

    public static void setListUser(List<User> listUser) {
        User.listUser = listUser;
    }

    public List<Pair<Timestamp, Bitmap>> getPosts() {
        return posts;
    }

    public void setPosts(List<Pair<Timestamp, Bitmap>> posts) {
        this.posts = posts;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public void setFollowers(List<User> followers) {
        this.followers = followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }

    public User() {
        this.listUser = new ArrayList<User>();
        this.id = userIdIncrement++;
        this.name = "Folco";
        this.username = "folco";
        this.phonenumber = "+6281111111111";
        this.email = "folco@gmail.com";
        this.password = "folco";
        this.profilePicture = null;
        this.posts = new ArrayList<Pair<Timestamp, Bitmap>>();
        this.followers = new ArrayList<User>();
        this.following = new ArrayList<User>();
        try {
            this.listUser.add(User.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public User (String name, String username, String phonenumber, String email, String password) throws Exception {
        this.id = userIdIncrement++;
        this.name = name;
        this.username = username;
        this.phonenumber = phonenumber;
        this.email = email;
        this.password = password;
        this.profilePicture = null;
        this.posts = new ArrayList<Pair<Timestamp, Bitmap>>();
        this.followers = new ArrayList<User>();
        this.following = new ArrayList<User>();
    }

}
