package cl.postulacion.nisum.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
    public static Boolean emailValidate(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+(?:\\.[a-zA-Z0-9_!#$%&amp;'*+/=?`{|}~^-]+)*@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    public static Boolean passwordValidate(String password){
        Pattern pattern = Pattern.compile("^(?=.*\\d{2})(?=.*[a-z])(?=.*[A-Z]).{4,20}$");
        Matcher mather = pattern.matcher(password);
        return mather.find();
    }
}
