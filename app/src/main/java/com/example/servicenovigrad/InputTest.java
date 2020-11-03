package com.example.servicenovigrad;

import android.widget.Toast;

public class InputTest {


    public static boolean verifyEmail(String email){
        boolean b = false;
        try {
            String emailregex = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            b = email.matches(emailregex);

        }
        catch(Exception e) {
        }
        return b;
    }

    public static boolean verifyPassword(String password){

        String passwordregex = "^(?=.*[0-9])" + "(?=.*[a-z])(?=.*[A-Z])"+ "(?=\\S+$).{8,20}$" ;
        boolean valid = false;

        try {
            valid = password.matches(passwordregex);
        }
        catch(Exception e) {
        }
        return valid;
    }
    public static boolean numberOrNot(String input)
    {
        try
        {
            Integer.parseInt(input);
        }
        catch(NumberFormatException ex)
        {
            return false;
        }
        return true;
    }



}
