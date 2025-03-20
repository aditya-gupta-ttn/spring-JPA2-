package in.aditya.springboot.springdatajpap2assignment.question4.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "employee_q4")
public class EmployeeQ4 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private int age;

    @Embedded
    private SalaryDetailsQ4 salaryDetails;

    // Constructors
    public EmployeeQ4() {}

    public EmployeeQ4(String firstName, String lastName, int age, SalaryDetailsQ4 salaryDetails) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salaryDetails = salaryDetails;
    }

    // Getters and Setters
    public Long getId() {
        return id;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public SalaryDetailsQ4 getSalaryDetails() {
        return salaryDetails;
    }

    public void setSalaryDetails(SalaryDetailsQ4 salaryDetails) {
        this.salaryDetails = salaryDetails;
    }
}