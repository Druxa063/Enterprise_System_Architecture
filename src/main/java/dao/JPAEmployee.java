package dao;

import model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JPAEmployee extends CrudRepository<Employee, Integer> {

    List<Employee> findAll();

}
