package model;

import java.util.Objects;
import java.util.regex.Pattern;

public class Customer {
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String firstName, String lastName, String email) throws IllegalArgumentException {
        String emailRegex = "^(.+)@(.+).com$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (firstName == null || lastName == null) {
            throw new IllegalArgumentException("please enter first name and last name");
        }
        if(!pattern.matcher(email).matches()) {
            throw new IllegalArgumentException("Error, Invalid Email ");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;

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
    public String toString(){
        return "First Name: " + firstName + " Last Name: " + lastName
                + " Email: " + email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
