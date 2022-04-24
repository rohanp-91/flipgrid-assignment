package com.flipgrid.assignment.flipgridsignup.app.utils;

import com.flipgrid.assignment.flipgridsignup.R;

public enum ErrorTypes {
    EMPTY_EMAIL(R.string.empty_email),
    INVALID_EMAIL(R.string.invalid_email),
    EMPTY_PASSWORD(R.string.empty_password),
    PASSWORD_LENGTH(R.string.password_length),
    PASSWORD_NO_CAPITAL_LETTER(R.string.password_no_caps),
    PASSWORD_NO_DIGIT(R.string.password_no_number),
    PASSWORD_NO_SPECIAL_CHAR(R.string.password_no_special_char);

    public int errorMessageResourceId;

    ErrorTypes(int resourceId) {
        this.errorMessageResourceId = resourceId;
    }
}
