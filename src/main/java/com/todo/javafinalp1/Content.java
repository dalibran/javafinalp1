package com.todo.javafinalp1;

import java.time.LocalDate;

abstract class Content {
    //member variables
    protected int id;
    protected String title;
    protected String description;
    protected LocalDate createdAt;

    public abstract void setId();
    public abstract void setTitle();
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
