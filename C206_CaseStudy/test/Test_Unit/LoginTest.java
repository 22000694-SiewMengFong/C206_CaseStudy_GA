package Test_Unit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.jupiter.api.Test;

import Unit_Package.Login;

class LoginTest {

	@Test
    public void testCheckFieldsBothBlank() {
        Login login = new Login("", ""); 
        boolean result = login.checkFields();
        assertFalse(result);
    }

    @Test
    public void testCheckFieldsEmailBlank() {
        Login login = new Login("", "somePassword");
        boolean result = login.checkFields();
        assertFalse(result);
    }

    @Test
    public void testCheckFieldsPasswordBlank() {
        Login login = new Login("test@example.com", "");
        boolean result = login.checkFields();
        assertFalse(result);
    }

    @Test
    public void testCheckFieldsValid() {
        Login login = new Login("test@example.com", "somePassword");
        boolean result = login.checkFields();
        assertTrue(result);
    }

}
