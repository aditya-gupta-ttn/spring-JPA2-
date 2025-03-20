package in.aditya.springboot.springdatajpap2assignment.entity;

import jakarta.persistence.*;

@Entity
//@DiscriminatorValue("DEVELOPER")
public class Developer extends Employee {

    @Column(name = "programming_language")
    private String programmingLanguage;

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    // Getters and Setters
}

