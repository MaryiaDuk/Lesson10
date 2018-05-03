package by.it_academy.Patient;

import java.util.Date;

public class Patient {
    private String firstName;
    private String lastName;
    private Date birthday;
    private boolean healthy;

    public Patient(String firstName, String lastName, Date birthday, boolean healthy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.healthy = healthy;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public boolean isHealthy() {
        return healthy;
    }
    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", healthy=" + healthy +
                '}';
    }
}