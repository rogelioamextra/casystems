package com.amextra.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author rzavaleta
 */
public class UtilGenerico {

    public static boolean validatePassword(String password) {
        // Regular expression to match the criteria
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Create matcher object
        Matcher matcher = pattern.matcher(password);

        // Return true if the password matches the criteria, false otherwise
        return matcher.matches();
    }

}