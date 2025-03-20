package in.aditya.springboot.springdatajpap2assignment;

import in.aditya.springboot.springdatajpap2assignment.entity.Employee;
import in.aditya.springboot.springdatajpap2assignment.question4.entity.EmployeeQ4;
import in.aditya.springboot.springdatajpap2assignment.question4.entity.SalaryDetailsQ4;
import in.aditya.springboot.springdatajpap2assignment.question4.repository.EmployeeQ4Repository;
import in.aditya.springboot.springdatajpap2assignment.question4.service.EmployeeQ4Service;
import in.aditya.springboot.springdatajpap2assignment.repository.EmployeeRepository;
import in.aditya.springboot.springdatajpap2assignment.service.EmployeeService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

@SpringBootTest
class SpringDataJpaP2AssignmentApplicationTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;


    @Autowired
    private EmployeeQ4Service employeeQ4Service;

    @BeforeEach
    void setup() {
        employeeRepository.deleteAll(); // Clear data before each test to avoid conflicts
        employeeRepository.save(new Employee("John", "Doe", 50000, 30));
        employeeRepository.save(new Employee("Jane", "Smith", 60000, 25));
        employeeRepository.save(new Employee("Peter", "Parker", 40000, 35));
        employeeRepository.save(new Employee("Tony", "Singh", 45000, 50));
        employeeRepository.save(new Employee("Bruce", "Wayne", 55000, 28));
    }

    @Test
    void testFindEmployeesWithSalaryAboveAverage() {
        List<Object[]> result = employeeRepository.findEmployeesWithSalaryAboveAverage();

        assertNotNull(result);
        assertTrue(result.size() > 0);

        for (Object[] record : result) {
            String firstName = (String) record[0];
            String lastName = (String) record[1];
            System.out.println("First Name: " + firstName + ", Last Name: " + lastName);
        }
    }

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    @Commit // Commit transaction after test
    void testUpdateSalaryForEmployeesBelowAverage() {
        double newSalary = 50000.0;
        int updatedCount = employeeService.updateSalaryForEmployeesBelowAverage(newSalary);

        assertTrue(updatedCount > 0);

        // Clear Hibernate cache to force fetching updated data from the database
        entityManager.flush();
        entityManager.clear();

        // Verify that salaries were updated correctly
        List<Employee> updatedEmployees = employeeRepository.findAll();
        updatedEmployees.forEach(employee -> {
            assertTrue(employee.getSalary() >= newSalary,
                    "Employee salary should be updated to at least " + newSalary);
        });
    }



    @Test
    @Transactional
    @Commit
    void testDeleteEmployeesWithMinSalary() {
        int deletedCount = employeeService.deleteEmployeesWithMinSalary();

        assertTrue(deletedCount > 0);

        double minSalary = employeeRepository.findAll()
                .stream()
                .mapToDouble(Employee::getSalary)
                .min()
                .orElse(0);

        assertNotEquals(40000, minSalary);
    }


    @Test
    void testFindByLastNameEndingWithSingh() {
        List<Object[]> result = employeeRepository.findByLastNameEndingWithSingh("singh");

        assertNotNull(result);
        assertEquals(1, result.size());

        Object[] record = result.get(0);
        assertEquals("Tony", record[1]);
        assertEquals(50, record[2]);
    }

    @Test
    @Commit
    @Transactional
    void testDeleteByAgeGreaterThan() {
        int deletedCount = employeeRepository.deleteByAgeGreaterThan(45);

        assertTrue(deletedCount > 0);

        List<Employee> employees = employeeRepository.findAll();
        assertTrue(employees.stream().noneMatch(emp -> emp.getAge() > 45));
    }

    @Transactional
    @Rollback(false)
    @Test
    void testSaveEmployeeWithSalaryDetails() {
        SalaryDetailsQ4 salaryDetails = new SalaryDetailsQ4(50000, 5000, 2000, 3000);
        EmployeeQ4 employee = new EmployeeQ4("John", "Doe", 30, salaryDetails);

        EmployeeQ4 savedEmployee = employeeQ4Service.createEmployee(employee);
        assertThat(savedEmployee.getId()).isNotNull();
        assertThat(savedEmployee.getSalaryDetails().getBasicSalary()).isEqualTo(50000);
    }

    @Test
    void testFindEmployeesWithSalaryDetails() {
        List<EmployeeQ4> employees = employeeQ4Service.getAllEmployees();

        assertThat(employees).isNotEmpty();
        for (EmployeeQ4 emp : employees) {
            SalaryDetailsQ4 salaryDetails = emp.getSalaryDetails();
            System.out.println("Employee: " + emp.getFirstName() +
                    ", Basic Salary: " + salaryDetails.getBasicSalary() +
                    ", Bonus Salary: " + salaryDetails.getBonusSalary() +
                    ", Tax Amount: " + salaryDetails.getTaxAmount() +
                    ", Special Allowance: " + salaryDetails.getSpecialAllowanceSalary());
        }
    }
}
