package com.flipgrid.assignment.flipgridsignup;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import com.flipgrid.assignment.flipgridsignup.app.utils.ErrorTypes;
import com.flipgrid.assignment.flipgridsignup.app.utils.Validators;

import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ValidatorUnitTest {

    private Validators validators;

    @Before
    public void setup() {
        validators = new Validators();
    }

    @Test
    public void emptyEmailValidation() {
        String email = "";
        List<ErrorTypes> errors = validators.isEmailValid(email);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e == ErrorTypes.EMPTY_EMAIL));
    }

    @Test
    public void invalidEmailValidation() {
        String email = "thisisinvalidemail";
        List<ErrorTypes> errors = validators.isEmailValid(email);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e == ErrorTypes.INVALID_EMAIL));
    }

    @Test
    public void emailValid() {
        String email = "valid@gmail.com";
        List<ErrorTypes> errors = validators.isEmailValid(email);
        assertEquals(0, errors.size());
    }

    @Test
    public void emptyPasswordValidation() {
        String password = "";
        List<ErrorTypes> errors = validators.isPasswordValid(password);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e == ErrorTypes.EMPTY_PASSWORD));
    }

    @Test
    public void passwordNoCapsValidation() {
        String password = "abcd#123";
        List<ErrorTypes> errors = validators.isPasswordValid(password);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e == ErrorTypes.PASSWORD_NO_CAPITAL_LETTER));
    }

    @Test
    public void passwordNoNumberValidation() {
        String password = "Abcdefgh#";
        List<ErrorTypes> errors = validators.isPasswordValid(password);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e == ErrorTypes.PASSWORD_NO_DIGIT));
    }

    @Test
    public void passwordNoSpecialCharValidation() {
        String password = "Abcd1234";
        List<ErrorTypes> errors = validators.isPasswordValid(password);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e == ErrorTypes.PASSWORD_NO_SPECIAL_CHAR));
    }

    @Test
    public void passwordLengthValidation() {
        String password = "Abcd#12";
        List<ErrorTypes> errors = validators.isPasswordValid(password);
        assertEquals(1, errors.size());
        assertTrue(errors.stream().anyMatch(e -> e == ErrorTypes.PASSWORD_LENGTH));
    }

    @Test
    public void passwordValid() {
        String password = "Abcd#123";
        List<ErrorTypes> errors = validators.isPasswordValid(password);
        assertEquals(0, errors.size());
    }
}