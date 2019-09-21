package controller;

import model.Department;
import model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.EmployeeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Arrays;

@Controller
public class ControllerServlet {

    private final EmployeeService service;

    @Autowired
    public ControllerServlet(EmployeeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String doGet(HttpServletRequest req, Model model) {
        String action = req.getParameter("action");
        if (action != null) {
            if (action.equalsIgnoreCase("delete")) {
                service.delete(getEmpno(req));
                model.addAttribute("emps", service.getAll());
            } else if (action.equalsIgnoreCase("update")) {
                model.addAttribute("emp", service.getById(getEmpno(req)));
                model.addAttribute("emps", service.getAll());
            } else if (action.equalsIgnoreCase("find")) {
                model.addAttribute("emps", Arrays.asList(service.getById(getEmpno(req))));
            }
        } else {
            model.addAttribute("emps", service.getAll());
        }
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String doPost(HttpServletRequest req) {
        Employee employee = new Employee();
        String empno = req.getParameter("empno");
        employee.setEname(req.getParameter("ename"));
        employee.setDept(new Department(Integer.parseInt(req.getParameter("deptno"))));
        if (empno == null || empno.isEmpty()) {
            service.create(employee);
        } else {
            employee.setEmpno(Integer.parseInt(empno));
            service.update(employee);
        }
        return "redirect:";
    }

    private int getEmpno(HttpServletRequest req) {
        return Integer.parseInt(req.getParameter("empno"));
    }
}
