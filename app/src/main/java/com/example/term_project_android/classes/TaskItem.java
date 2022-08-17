package com.example.term_project_android.classes;

import java.util.Objects;

public class TaskItem {
    private String taskName;
    private String description;
    private String dueDate;
    private int priority;

    public TaskItem(String taskName, String description, String dueDate, int priority) {
        this.taskName = taskName;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    //only taskName is required
    public TaskItem(String taskName) {
        this.taskName = taskName;
        this.description = "";
        this.dueDate = "";
        this.priority = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaskItem taskItem = (TaskItem) o;
        return taskName.equals(taskItem.taskName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskName);
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {this.dueDate = dueDate;}

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
