package Model;

public class User {
    private String username;
    private String password;

    public User() {
        username = null;
        password = null;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean equals(User u) {
        return (this.username.equals(u.username) && this.password.equals(u.password));
    }

}
