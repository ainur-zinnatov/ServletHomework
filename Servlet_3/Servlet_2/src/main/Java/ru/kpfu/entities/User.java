package ru.kpfu.entities;

/**
 * Created by hp on 07.10.2015.
 */
public class User {
    String name;
    String email;
    String password;
    String sex;
    String newsletter;
    String comment;

    public User(String name, String email, String password, String sex, String newsletter, String comment) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.newsletter = newsletter;
        this.comment = comment;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(String newsletter) {
        this.newsletter = newsletter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
