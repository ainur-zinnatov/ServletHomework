package ru.kpfu.repositories;

import ru.kpfu.entities.User;
import ru.kpfu.exceptions.EmailValidException;
import ru.kpfu.exceptions.UserExistException;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hp on 07.10.2015.
 */
public  class UserRepositories {
    private final static File DATA =new File("C://Java/text.txt");
    public static void addUsers (User user) throws UserExistException, FileNotFoundException, EmailValidException {
        String lineSeparator = System.getProperty("line.separator");
        if(!emailIsValid(user)){
            throw new EmailValidException("Email is not valid!");
        }
        if(isExist(user).equals("name")){
            throw new UserExistException("User with the same name already exists, please select another!");
        }else if(isExist(user).equals("email")){
            throw new UserExistException("This email is already used!");
        }else if(user.getPassword().length()<6){
            throw new UserExistException("Password length must be greater than or equal to 6!");
        }else if(user.getPassword().contains(" ")){
            throw new UserExistException("Passwords should not contain spaces!");
        }else if(user.getName().contains(" ")){
            throw new UserExistException("Name should not contain spaces!");
        }

            update(DATA,user.getName()+" "+user.getEmail()+" "+user.getPassword()+" "+user.getSex()+" "+user.getNewsletter()+lineSeparator);


    }
    private static boolean emailIsValid(User user){
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = Pattern.compile(ePattern);
        Matcher m = p.matcher(user.getEmail());
        return m.matches();

    }
    private static String isExist(User user){
        String exist ="false";
        try {

            BufferedReader in = new BufferedReader(new FileReader( DATA.getAbsoluteFile()));
            try {

                String s;
                String name="";
                String email ="";
                String password="";
                String pol="";
                String newsletter="";
                while ((s = in.readLine()) != null) {
                    for (int i=0;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {name += s.charAt(i);}
                    }
                    for (int i=name.length()+1;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {
                            email += s.charAt(i);}
                    }
                    for (int i= email.length()+name.length()+2;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {password += s.charAt(i);}
                    }
                    for (int i=name.length()+ email.length()+password.length()+3;i < s.length() ;i++ ) {
                        if (s.charAt(i)==' '){
                            break;
                        }
                        else {pol += s.charAt(i);}
                    }
                    for (int i=name.length()+ email.length()+password.length()+pol.length()+4;i < s.length() ;i++ ) {


                        newsletter += s.charAt(i);

                    }
                    if(pol.equals("1")){
                        pol="male";
                    }else {pol="female";}
                    if(newsletter.equals("1")){
                        newsletter="yes";
                    }else {newsletter="no";}

                    if(name.equals(user.getName())){
                        exist = "name";
                        break;
                    }else if(email.equals(user.getEmail())){
                        exist = "email";
                        break;
                    }



                    name="";
                    email="";
                    password="";
                    pol="";
                    newsletter="";
                }
            } finally {

                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return exist;

    }

    public static String read(File file) throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        exists(file);

        try {

            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String lineSeparator = System.getProperty("line.separator");
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append(lineSeparator);

                }
            } finally {

                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }


        return sb.toString();


    }

    public static void write(File file, String text) {

        try {

            if(!file.exists()){
                file.createNewFile();
            }

            PrintWriter out = new PrintWriter(file.getAbsoluteFile());

            try {
                out.print(text);
            } finally {
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void exists(File file) throws FileNotFoundException {
        if (!file.exists()){
            throw new FileNotFoundException(file.getName());
        }
    }
    public static void update(File file, String newText) throws FileNotFoundException {
        exists(file);
        StringBuilder sb = new StringBuilder();
        String oldFile = read(file);
        sb.append(oldFile);
        sb.append(newText);
        write(file, sb.toString());
    }


}
