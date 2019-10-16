package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "logger")
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "message")
    private String message;

    @Column(name = "date")
    private Date date = new Date();

    public Logger() {
    }

    public Logger(String message) {
        this.message = message;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
