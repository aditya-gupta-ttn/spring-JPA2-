package in.aditya.springboot.springdatajpap2assignment.question4.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class SalaryDetailsQ4 {

    private double basicSalary;
    private double bonusSalary;
    private double taxAmount;
    private double specialAllowanceSalary;

    // Constructors
    public SalaryDetailsQ4() {}

    public SalaryDetailsQ4(double basicSalary, double bonusSalary, double taxAmount, double specialAllowanceSalary) {
        this.basicSalary = basicSalary;
        this.bonusSalary = bonusSalary;
        this.taxAmount = taxAmount;
        this.specialAllowanceSalary = specialAllowanceSalary;
    }

    // Getters and Setters
    public double getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(double basicSalary) {
        this.basicSalary = basicSalary;
    }

    public double getBonusSalary() {
        return bonusSalary;
    }

    public void setBonusSalary(double bonusSalary) {
        this.bonusSalary = bonusSalary;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getSpecialAllowanceSalary() {
        return specialAllowanceSalary;
    }

    public void setSpecialAllowanceSalary(double specialAllowanceSalary) {
        this.specialAllowanceSalary = specialAllowanceSalary;
    }
}