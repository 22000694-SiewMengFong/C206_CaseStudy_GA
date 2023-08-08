package Unit_Package;

import java.util.ArrayList;
import java.util.List;

public class Registration {
    private List<User> registeredUsers = new ArrayList<>();

    public void registerUser(String username, String password) {
        registeredUsers.add(new User(username, password));
    }

    public boolean loginUser(String username, String password) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isUsernameTaken(String username) {
        for (User user : registeredUsers) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean isPasswordValid(String password) {
        String passwordPattern = "^(?=.*[A-Z]).{8,}$";
        return password.matches(passwordPattern);
    }

    private static class User {
        private String username;
        private String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }
    }
}
