package com.example.servicenovigrad;

public class User {
    private String id;
    private String usertype ;
    private String username;



    public User(){

    }

    public User(String id, String usertype, String username, String email){
        this.id = id;
        this.usertype = usertype;
        this.username = username;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsertype() {
        return usertype;
    }

    public String getUsername() {
        return username;
    }
}



