package com.todo.javafinalp1;

import java.io.Serializable;
import java.time.LocalDate;

abstract class Content implements Serializable {
    //member variables
    protected String title;
    protected String description;
    protected LocalDate createdAt;
    private static final long serialVersionUID = 1L;

    public abstract void setTitle(String title);
    public abstract void setDescription(String desc);

    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }

    public void setCreatedAt(LocalDate date) {
        this.createdAt = date;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
