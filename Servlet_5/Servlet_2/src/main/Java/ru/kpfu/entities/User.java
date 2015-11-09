package ru.kpfu.entities;

/**
 * Created by hp on 07.10.2015.
 */
public class User {
   private int id;
   private String name;
   private String email;
   private String password;
   private int sex;
   private int newsletter;


    public User(String name, String email, String password, int sex, int newsletter) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.newsletter = newsletter;
    }

    public User(int id, String name, String email, String password, int sex, int newsletter) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.newsletter = newsletter;
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
