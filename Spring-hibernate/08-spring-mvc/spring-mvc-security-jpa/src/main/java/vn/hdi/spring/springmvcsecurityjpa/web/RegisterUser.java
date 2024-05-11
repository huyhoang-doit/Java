package vn.hdi.spring.springmvcsecurityjpa.web;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUser {
    @NotBlank(message = "This information is required!")
    @Size(min = 1, message = "Minimum length is 1 character")
    private String userName;

    @NotBlank(message = "This information is required!")
    @Size(min=8, message = "Minimum length is 8 characters")
    @Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
    message = "Password must contain at least 1 number and 1 special character")
    private String password;

    @NotBlank(message = "This information is required!")
    private String firstName;

    @NotBlank(message = "This information is required!")
    private String lastName;

    @Email(message = "Invalid email!")
    private String email;

    public RegisterUser() {
    }

    public RegisterUser(String email, String lastName, String firstName, String password, String userName) {
        this.email = email;
        this.lastName = lastName;
        this.firstName = firstName;
        this.password = password;
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RegisterUser{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
