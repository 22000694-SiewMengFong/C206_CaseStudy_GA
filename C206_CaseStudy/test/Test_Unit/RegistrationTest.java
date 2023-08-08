package Test_Unit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import Unit_Package.Registration;

public class RegistrationTest {

    @Test
    public void testSuccessfulLogin() {
        Registration registrationLogic = new Registration();
        registrationLogic.registerUser("testuser", "Test1234");

        assertTrue(registrationLogic.loginUser("testuser", "Test1234"));
    }

    @Test
    public void testInvalidLogin() {
        Registration registrationLogic = new Registration();
        registrationLogic.registerUser("testuser", "Test1234");

        assertFalse(registrationLogic.loginUser("testuser", "WrongPassword"));
    }

    @Test
    public void testSuccessfulRegistration() {
        Registration registrationLogic = new Registration();

        assertTrue(registrationLogic.isPasswordValid("Test1234"));
        assertTrue(registrationLogic.isPasswordValid("AnotherPassword"));
    }

    @Test
    public void testUsernameExistsDuringRegistration() {
        Registration registrationLogic = new Registration();
        registrationLogic.registerUser("testuser", "Test1234");

        assertTrue(registrationLogic.isUsernameTaken("testuser"));
    }

    @Test
    public void testPasswordValidationDuringRegistration() {
        Registration registrationLogic = new Registration();

        assertFalse(registrationLogic.isPasswordValid("weak"));
    }
}
