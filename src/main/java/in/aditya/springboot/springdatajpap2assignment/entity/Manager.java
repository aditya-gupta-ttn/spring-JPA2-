package in.aditya.springboot.springdatajpap2assignment.entity;

import jakarta.persistence.*;

@Entity
//@DiscriminatorValue("MANAGER")
public class Manager extends Employee {

    @Column(name = "department")
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    // Getters and Setters
}