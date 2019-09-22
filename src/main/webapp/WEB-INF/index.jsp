<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>List Employee</title>
    <base href="${pageContext.request.contextPath}/"/>
</head>
<body>
<form method="post" action="/">
    <input type="number" name="empno" value="${emp.empno}" hidden/><br>
    Ename : <input type="text" name="ename" value="${emp.ename}"/><br>
    Number department : <input type="number" name="deptno" value="${emp.dept.deptno}"/><br>
    <input type="submit" value="Submit"/>
</form>
<br/>

<form method="get" action="/">
    <input type="text" name="action" value="find" hidden/><br>
    Number Worker : <input type="text" name="empno">
    <button>Submit</button>
</form>
<br/>

<table border="1">
    <thead>
    <th>EmpNO</th>
    <th>Ename</th>
    <th>Number department</th>
    <th>Dept name</th>
    <th>Location</th>
    <th>Delete</th>
    <th>Update</th>
    </thead>
    <tbody>
    <c:forEach items="${emps}" var="emp">
        <jsp:useBean id="emp" scope="page" type="model.Employee"/>
        <tr>
            <td>${emp.empno}</td>
            <td>${emp.ename}</td>
            <td>${emp.dept.deptno}</td>
            <td>${emp.dept.dname}</td>
            <td>${emp.dept.loc}</td>
            <td><a href="?action=delete&empno=${emp.empno}">Delete</a></td>
            <td><a href="?action=update&empno=${emp.empno}">Update</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>