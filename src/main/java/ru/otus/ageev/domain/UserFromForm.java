package ru.otus.ageev.domain;

public class UserFromForm {
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userCaptcha;

    public UserFromForm(String login, String firstName, String lastName, String email, String password, String userCaptcha) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userCaptcha = userCaptcha;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserCaptcha() {
        return userCaptcha;
    }

    public void setUserCaptcha(String userCaptcha) {
        this.userCaptcha = userCaptcha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User getUser() {
        return new User(this.login,
                this.firstName,
                this.lastName,
                this.email,
                this.password,
                 UserRole.USER);
    }
}
