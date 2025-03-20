package in.aditya.springboot.springdatajpap2assignment.service;

import in.aditya.springboot.springdatajpap2assignment.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public List<Object[]> getEmployeesWithHighSalary() {
        return repository.findEmployeesWithSalaryAboveAverage();
    }

    @Transactional
    public int updateSalaryForEmployeesBelowAverage(Double newSalary) {
        Double averageSalary = repository.findAverageSalary();
        if (averageSalary != null) {
            return repository.updateSalaryForEmployeesBelowAverage(newSalary, averageSalary);
        }
        return 0;
    }

    @Transactional
    public int deleteEmployeesWithMinSalary() {
        Double minSalary = repository.findMinSalary();
        if (minSalary != null) {
            return repository.deleteBySalary(minSalary);
        }
        return 0;
    }
}
