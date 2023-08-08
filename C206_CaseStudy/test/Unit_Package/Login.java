package Unit_Package;

public class Login {

    private String email;
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public boolean checkFields() {
        if (email.isEmpty() && password.isEmpty()) {
            return false;
        } else if (email.isEmpty()) {
            return false;
        } else if (password.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
