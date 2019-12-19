package controller;

import model.Department;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import service.EmployeeService;

import java.util.Arrays;
import java.util.List;

@RestController
public class ControllerServlet {

    private final EmployeeService service;

    @Autowired
    private ResourceLoader loader;

    @Autowired
    public ControllerServlet(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ResponseEntity create(@RequestBody Employee employee) {
        if (StringUtils.isEmpty(employee.getEmpno())) {
            service.create(employee);
            return new ResponseEntity("Employee created", HttpStatus.CREATED);
        } else {
            service.update(employee);
            return new ResponseEntity("Employee update", HttpStatus.OK);
        }
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        service.delete(id);
    }

    @GetMapping("/get/{id}")
    public List<Employee> get(@PathVariable int id) {
        return Arrays.asList(service.getById(id));
    }

    @GetMapping("/list")
    public List<Employee> getAll() {
        return service.getAll();
    }
}
