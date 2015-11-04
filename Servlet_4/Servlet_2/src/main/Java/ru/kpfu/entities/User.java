package ru.kpfu.entities;

/**
 * Created by hp on 07.10.2015.
 */
public class User {
    String name;
    String email;
    String password;
    int sex;
    int newsletter;


    public User(String name, String email, String password, int sex, int newsletter) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.newsletter = newsletter;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getNewsletter() {
        return newsletter;
    }

    public void setNewsletter(int newsletter) {
        this.newsletter = newsletter;
    }
}
