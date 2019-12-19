package controller;

import model.Employee;
import model.EmployeeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import service.EmployeeServiceImpl;
import service.HttpRequestHelper;

import javax.xml.transform.TransformerException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Controller
public class ApplicationController {

    @Autowired
    private EmployeeServiceImpl employeeService;

    @Autowired
    private ResourceLoader loader;

    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.GET)
    public String watchIndex(Model model){
        return "index";
    }

    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public String watchEmployees(Model model) throws TransformerException {
        List<Employee> employees = employeeService.getAll();
        String element = HttpRequestHelper.jaxbObjectToXML(new EmployeeList(employees), EmployeeList.class);
        Document document = HttpRequestHelper.getDocument(element);
        model.addAttribute("employees", document);
        return "employees";
    }

}
