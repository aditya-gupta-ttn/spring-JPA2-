package in.aditya.springboot.springdatajpap2assignment.question4.service;


import in.aditya.springboot.springdatajpap2assignment.question4.entity.EmployeeQ4;
import in.aditya.springboot.springdatajpap2assignment.question4.repository.EmployeeQ4Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeQ4Service {

    @Autowired
    private EmployeeQ4Repository employeeQ4Repository;

    @Transactional
    public EmployeeQ4 createEmployee(EmployeeQ4 employee) {
        return employeeQ4Repository.save(employee);
    }

    public List<EmployeeQ4> getAllEmployees() {
        return employeeQ4Repository.findAll();
    }
}