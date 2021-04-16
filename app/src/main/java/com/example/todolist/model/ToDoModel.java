package com.example.todolist.model;

import java.util.Date;

public class ToDoModel {
    private int id;
    private int status;
    private String task;
    private Date date;
    private Prio prio;

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPrio() {
        return prio.toString();
    }

    public void setPrio(Prio prio) {
        this.prio = prio;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
