package com.VB2020.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Writer implements Serializable {

    private int id;
    private String firstName;
    private String lastName;
    private List<Post> postsList;

    public Writer() {

    }

    public Writer(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Writer(int id, String firstName, String lastName, ArrayList<Post> postsList) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.postsList = postsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Post> getPostsList() {
        return postsList;
    }

    public void setPostsList(List<Post> postsList) {
        this.postsList = postsList;
    }

    @Override
    public String toString() {
        return "Writer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", postsList=" + postsList +
                '}';
    }

}
