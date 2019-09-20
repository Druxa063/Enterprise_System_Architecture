package model;

import javax.persistence.*;

@Entity
@Table(name = "dept")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "deptno")
    private int deptno;

    @Column(name = "dname")
    private String dname;

    @Column(name = "loc")
    private String loc;

    public Department() {
    }

    public Department(int deptno) {
        this.deptno = deptno;
    }

    public Department(int deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public int getDeptno() {
        return deptno;
    }

    public void setDeptno(int deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}