package controller;

import model.Employee;
import model.EmployeeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.EmployeeServiceImpl;

import java.util.List;


@RestController()
public class RestApplicationController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @RequestMapping(value = {"/rest/", "/rest/index"}, method = RequestMethod.GET)
    public String watchIndex(Model model){
        return "index";
    }

    @RequestMapping(value = "/rest/employees", method = RequestMethod.GET, produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<EmployeeList> watchEmployees(){
        List<Employee> employees = employeeService.getAll();
        return new ResponseEntity<>(new EmployeeList(employees), HttpStatus.OK);
    }
}
