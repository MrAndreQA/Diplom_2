package ru.yandex.praktikum.model;

public class UserCredentials {
    private String email;
    private String password;

    public UserCredentials(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static UserCredentials from(User user) {
        return new UserCredentials(user.getEmail(), user.getPassword());
    }

    public static UserCredentials withIncorrectEmail(User user) {
        return new UserCredentials(user.getEmail() + "new", user.getPassword());
    }

    public static UserCredentials withIncorrectPassword(User user) {
        return new UserCredentials(user.getEmail(), user.getPassword() + "new");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
