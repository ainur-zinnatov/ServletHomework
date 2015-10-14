package ru.kpfu.repositories;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import ru.kpfu.entities.User;
import ru.kpfu.exceptions.DBException;
import ru.kpfu.exceptions.EmailValidException;
import ru.kpfu.exceptions.UserExistException;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hp on 07.10.2015.
 */
public  class UserRepositories2 {
    private final static File DATA = new File("C://Java/text.csv");

    public void addUsers(User user) throws UserExistException, FileNotFoundException, EmailValidException, DBException {
        if (!emailIsValid(user.getEmail())) {
            throw new EmailValidException("Email is not valid!");
        }
        if (!nameIsValid(user.getName())) {
            throw new UserExistException("User with the same name already exists, please select another!");
        } else if (!emailIsValid(user.getEmail())) {
            throw new UserExistException("This email is already used!");
        } else if (user.getPassword().length() < 6) {
            throw new UserExistException("Password length must be greater than or equal to 6!");
        } else if (user.getPassword().contains(" ")) {
            throw new UserExistException("Passwords should not contain spaces!");
        } else if (user.getName().contains(" ")) {
            throw new UserExistException("Name should not contain spaces!");
        }

        try {
            append(user);

        } catch (DBException t){
            throw t;
        }
    }

    private static void append(User user) throws DBException {
        try {
            CSVWriter writer = new CSVWriter(new FileWriter(DATA, true));
            String[] record = {user.getName(), user.getEmail(), user.getPassword(), user.getSex(), user.getNewsletter()};
            writer.writeNext(record);
            writer.close();
        } catch (IOException e) {
            throw new DBException();
        }
    }

    public User[] getAll() throws DBException {
        try {

            CSVReader checkLength = new CSVReader(new FileReader(DATA), ',', '"', 1);

            int i = 0;
            while (checkLength.readNext() != null) {
                i++;
            }
            checkLength.close();


            User[] users = new User[i];
            String[] nextLine;
            i = 0;

            CSVReader reader = new CSVReader(new FileReader(DATA), ',', '"', 1);

            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    users[i] = new User(nextLine[0], nextLine[1], nextLine[2],
                            nextLine[3], nextLine[4]);
                }
                i++;
            }
            return users;

        } catch (IOException e) {
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
            CSVReader reader = new CSVReader(new FileReader(DATA), ',', '"', 1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    if (nextLine[1].equals(email)) {
                        User user = new User(nextLine[0], nextLine[1], nextLine[2],
                                nextLine[3], nextLine[4]);
                        return user;
                    }
                }
            }
            return null;
        } catch (IOException e) {
            throw new DBException();
        }


    }

    public static User searchName(String name) throws DBException {
        try {
            CSVReader reader = new CSVReader(new FileReader(DATA), ',', '"', 1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                if (nextLine != null) {
                    if (nextLine[0].equals(name)) {
                        User user = new User(nextLine[0], nextLine[1], nextLine[2],
                                nextLine[3], nextLine[4]);
                        return user;
                    }
                }
            }
            return null;
        } catch (IOException e) {
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