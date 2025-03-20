package in.aditya.springboot.springdatajpap2assignment.question4.repository;


import in.aditya.springboot.springdatajpap2assignment.question4.entity.EmployeeQ4;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeQ4Repository extends JpaRepository<EmployeeQ4, Long> {
}
