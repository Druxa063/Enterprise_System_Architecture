package controller;

import model.Department;
import model.Employee;
import service.EmployeeService;
import service.JMSService;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ControllerServlet extends HttpServlet {

    @EJB(beanName = "EmployeeServiceImpl")
    private EmployeeService service;

    @EJB(beanName = "JMSService")
    private JMSService jmsService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            if (action.equalsIgnoreCase("delete")) {
                service.delete(getEmpno(req));
                jmsService.sendString("Deleted Employee with number");
                req.setAttribute("emps", service.getAll());
            } else if (action.equalsIgnoreCase("update")) {
                req.setAttribute("emp", service.getById(getEmpno(req)));
            } else if (action.equalsIgnoreCase("find")) {
                req.setAttribute("emps", Arrays.asList(service.getById(getEmpno(req))));
            }
        } else {
            req.setAttribute("emps", service.getAll());
        }
        RequestDispatcher view = req.getRequestDispatcher("/index.jsp");
        view.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        String empno = req.getParameter("empno");
        employee.setEname(req.getParameter("ename"));
        employee.setDept(new Department(Integer.parseInt(req.getParameter("deptno"))));
        if (empno == null || empno.isEmpty()) {
            service.create(employee);
            jmsService.sendString("Created Employee: " + employee);
        } else {
            employee.setEmpno(Integer.parseInt(empno));
            service.update(employee);
            jmsService.sendString("Updated Employee: " + employee);
        }
        resp.sendRedirect("");
    }

    private int getEmpno(HttpServletRequest req) {
        return Integer.parseInt(req.getParameter("empno"));
    }
}
