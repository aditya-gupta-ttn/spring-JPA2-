package in.aditya.springboot.springdatajpap2assignment.repository;

import in.aditya.springboot.springdatajpap2assignment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("SELECT AVG(e.salary) FROM Employee e")
    Double findAverageSalary();

    @Query("SELECT MIN(e.salary) FROM Employee e")
    Double findMinSalary();


    // 1) Display first name, last name of employees with salary > avg salary, ordered by age and salary
    @Query("SELECT e.firstName, e.lastName FROM Employee e WHERE e.salary > (SELECT AVG(e2.salary) FROM Employee e2) ORDER BY e.age ASC, e.salary DESC")
    List<Object[]> findEmployeesWithSalaryAboveAverage();

    // 2) Update salary of employees with salary < avg salary
    @Modifying
    @Query("UPDATE Employee e SET e.salary = :newSalary WHERE e.salary < :averageSalary")
    int updateSalaryForEmployeesBelowAverage(@Param("newSalary") Double newSalary, @Param("averageSalary") Double averageSalary);


// 3) Delete employees with minimum salary
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.salary = :salary")
    int deleteBySalary(@Param("salary") Double salary);

    // 4) Find employees whose last name ends with "Singh"
    @Query("SELECT e.id, e.firstName, e.age FROM Employee e WHERE e.lastName LIKE %:lastName%")
    List<Object[]> findByLastNameEndingWithSingh(@Param("lastName") String lastName);
    // 5) Delete employees whose age is greater than a specified value
    @Modifying
    @Query("DELETE FROM Employee e WHERE e.age > :age")
    int deleteByAgeGreaterThan(@Param("age") int age);}
