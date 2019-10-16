package model;

import javax.persistence.*;
import java.util.Date;

@Entity()
@Table(name = "emp")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "empno")
    private Integer empno;

    @Column(name = "ename")
    private String ename;

    @ManyToOne()
    @JoinColumn(name = "deptno")
    private Department dept;

    public Employee() {
    }

    public Employee(Integer empno, String ename, Department dept) {
        this.empno = empno;
        this.ename = ename;
        this.dept = dept;
    }

    public int getEmpno() {
        return empno;
    }

    public void setEmpno(int empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public Department getDept() {
        return dept;
    }

    public void setDept(Department dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", dept=" + dept +
                '}';
    }
}
