DROP TABLE IF EXISTS emp;
DROP TABLE IF EXISTS dept;
DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START 100;

CREATE TABLE dept
(
    deptno              INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    dname               VARCHAR,
    local               VARCHAR
);

CREATE TABLE emp
(
    empno               INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    ename               VARCHAR,
    deptno              INTEGER,
    FOREIGN KEY (deptno) REFERENCES dept (deptno) ON DELETE CASCADE
);

DELETE FROM emp;
DELETE FROM dept;
ALTER SEQUENCE global_seq RESTART WITH 100;

INSERT INTO dept (dname, local) VALUES
    ('departament_1', 'Samara'),
    ('departament_2', 'Moscow'),
    ('departament_3', 'new york');

INSERT INTO emp (ename, deptno) VALUES
    ('Vasia', 100),
    ('Pypkin', 101),
    ('Bob', 102);