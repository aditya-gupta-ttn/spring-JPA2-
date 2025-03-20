package in.aditya.springboot.springdatajpap2assignment.entity;

import jakarta.persistence.*;

@Entity
//@DiscriminatorValue("INTERN")
public class Intern extends Employee {

    @Column(name = "school_name")
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    // Getters and Setters
}

