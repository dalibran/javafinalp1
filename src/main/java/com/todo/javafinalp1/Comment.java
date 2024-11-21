package com.todo.javafinalp1;

public class Comment extends Content {
    private static int counter = 0;
    private int id;
    protected static final int COMMENT_MAX_LENGTH = 300;

    public Comment(String desc) {
        this.id = incrementId();
        this.description = desc;
    }

    private int incrementId() {
        return ++counter;
    }

    //override unused method to do nothing
    @Override
    public void setTitle(String title) {
        throw new UnsupportedOperationException("This method is not supported in this subclass.");
    }

    //set appropriate character limit for comments
    @Override
    public void setDescription(String desc) {
        if (desc.length() > COMMENT_MAX_LENGTH) {
            throw new IllegalArgumentException("Description cannot exceed " + COMMENT_MAX_LENGTH + " characters.");
        }
        this.description = desc;
    }
}

