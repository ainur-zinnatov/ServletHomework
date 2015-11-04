package ru.kpfu.repositories;


import ru.kpfu.entities.User;
import ru.kpfu.exceptions.DBException;
import ru.kpfu.exceptions.UserExistException;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.kpfu.repositories.MysqlConnect.getDbCon;
import static ru.kpfu.repositories.MysqlConnect.insert;
import static ru.kpfu.repositories.MysqlConnect.query;

/**
 * Created by hp on 07.10.2015.
 */
public  class UserRepositories {


    public void addUsers(User user) throws UserExistException, FileNotFoundException, DBException {
        if (!nameIsValid(user.getName())) {
            throw new UserExistException("User with the same name already exists, please select another!");
        } else if (!emailIsValid(user.getEmail())) {
            throw new UserExistException("Email is not correct!");
        } else if (user.getPassword().length() < 6) {
            throw new UserExistException("Password length must be greater than or equal to 6!");
        } else if (user.getPassword().contains(" ")) {
            throw new UserExistException("Passwords should not contain spaces!");
        } else if (user.getName().contains(" ")) {
            throw new UserExistException("Name should not contain spaces!");
        }else if(searchEmail(user.getEmail())!=null){
            throw new UserExistException("This email is already used!");
        }else if(searchName(user.getName())!=null){
            throw new UserExistException("This name is already used!");
        }

        try {

            append(user);

        } catch (DBException t){
            throw t;
        }
    }

    private static void append(User user) throws DBException {

        try {
            getDbCon();
        }catch (Exception sqle){
            sqle.getMessage();
        }
        try {

            insert("INSERT INTO users(email, password, sex, subscription, name) VALUE('"+user.getEmail()+"','"+user.getPassword()+"',"+user.getSex()+","+user.getNewsletter()+",'"+user.getName()+"')");
        }  catch (SQLException e) {
            throw new DBException();
        }
    }

    public static User[] getAll() throws DBException {
        try {

            getDbCon();
            ResultSet resultSet = null;
            try {
                resultSet = query("SELECT * FROM users");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int usersCount = 0;
            if(resultSet != null) {
                while (resultSet.next()) {
                    usersCount++;
                }
                User[] users = new User[usersCount];
                int i = 0;
                while (resultSet.next()) {
                    users[i] = new User(resultSet.getString(6), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getInt(5));
                    i++;
                }
                return users;
            }else return null;
        } catch (SQLException e) {
            throw new DBException();
        }


    }

    public static boolean nameIsValid(String name) {
        Pattern pattern = Pattern.compile("^[A-Za-z0-9_-]{3,15}$");
       Matcher matcher = pattern.matcher(name);
        return matcher.matches();

    }


    public static User searchEmail(String email) throws DBException {
        try {
            getDbCon();
            ResultSet resultSet = null;
            try {
                resultSet = query("SELECT * FROM users");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(resultSet != null) {
                while (resultSet.next()) {
                    if(resultSet.getString("email").equals(email)) {
                        User user = new User(resultSet.getString("name"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getInt("sex"), resultSet.getInt("subscription"));
                        return user;
                    }
                }
             return null;
            }else return null;
        } catch (SQLException e) {
            throw new DBException();
        }


    }

    public static User searchName(String name) throws DBException {
        try {
            getDbCon();
            ResultSet resultSet = null;
            try {
                resultSet = query("SELECT * FROM users");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(resultSet != null) {
                while (resultSet.next()) {
                    if(resultSet.getString("name").equals(name)) {
                        User user = new User(resultSet.getString(5), resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4));
                        return user;
                    }
                }
                return null;
            }else return null;
        } catch (SQLException e) {
            throw new DBException();
        }


    }



    public static boolean emailIsValid(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}