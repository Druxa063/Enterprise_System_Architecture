package controller;

import model.Department;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;

import java.util.List;

@RestController
public class ControllerServlet {

    private final EmployeeService service;

    @Autowired
    public ControllerServlet(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody Employee employee) {
        service.create(employee);
        return new ResponseEntity("Employee created", HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity update(@RequestBody Employee employee) {
        service.update(employee);
        return new ResponseEntity("Employee update", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        service.delete(id);
        return new ResponseEntity("Employee deleted", HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Employee get(@PathVariable int id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<Employee> getAll() {
        return service.getAll();
    }
}
