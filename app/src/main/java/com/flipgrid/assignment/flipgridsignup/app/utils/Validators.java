package com.flipgrid.assignment.flipgridsignup.app.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Validators {

    private final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private final String passwordCapitalLetterPattern = "[A-Z]";
    private final String passwordDigitPattern = "[0-9]";
    private final String passwordSpecialCharPattern = "[^a-zA-Z0-9 ]";


    public List<ErrorTypes> isEmailValid(String email) {
        List<ErrorTypes> errors = new ArrayList<>();
        if (email == null || email.isEmpty()) {
            errors.add(ErrorTypes.EMPTY_EMAIL);
            return errors;
        }
        if (!Pattern.compile(emailPattern).matcher(email).find()) {
            errors.add(ErrorTypes.INVALID_EMAIL);
        }
        return errors;
    }

    public List<ErrorTypes> isPasswordValid(String password) {
        List<ErrorTypes> errors = new ArrayList<>();
        if (password == null || password.isEmpty()) {
            errors.add(ErrorTypes.EMPTY_PASSWORD);
            return errors;
        }
        if (password.length() < 8 || password.length() > 12) {
            errors.add(ErrorTypes.PASSWORD_LENGTH);
        }
        if (!Pattern.compile(passwordCapitalLetterPattern).matcher(password).find()) {
            errors.add(ErrorTypes.PASSWORD_NO_CAPITAL_LETTER);
        }
        if (!Pattern.compile(passwordDigitPattern).matcher(password).find()) {
            errors.add(ErrorTypes.PASSWORD_NO_DIGIT);
        }
        if (!Pattern.compile(passwordSpecialCharPattern).matcher(password).find()) {
            errors.add(ErrorTypes.PASSWORD_NO_SPECIAL_CHAR);
        }
        return errors;
    }
}
