package com.todo.javafinalp1;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task extends Content {
    protected ArrayList<Comment> comments = new ArrayList<>(5);
    protected LocalDate dueDate;
    protected Status currentStatus;

    //uses Builder design pattern as constructor to allow for optional params
    protected Task(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.createdAt = builder.createdAt;
        this.dueDate = builder.dueDate;
        this.currentStatus = builder.currentStatus;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }
    @Override
    public void setDescription(String desc) {}

    public void setStatus(Status status) {
        this.currentStatus = status;
    }

    public Status getStatus() {
        return currentStatus;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public Comment getComment(int index) {
        return comments.get(index);
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setDueDate(LocalDate date) {
        this.dueDate = date;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public static class Builder {
        private static final int TITLE_MAX_LENGTH = 60;
        private final String title;
        private String description;
        private LocalDate createdAt;
        private LocalDate dueDate;
        private Status currentStatus = Status.TODO;

        public Builder(String title) {
            this.title = validateTitle(title);
            this.createdAt = LocalDate.now();
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder dueDate(LocalDate date) {
            this.dueDate = date;
            return this;
        }

        private String validateTitle(String title) {
            if (title.length() > TITLE_MAX_LENGTH) {
                throw new IllegalArgumentException("Title cannot exceed " + TITLE_MAX_LENGTH + " characters.");
            }
            return title;
        }

        public Task build() {
            return new Task(this);
        }
    }
}

