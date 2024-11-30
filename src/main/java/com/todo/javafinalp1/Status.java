package com.todo.javafinalp1;

public enum Status {
    TODO {
        @Override
        public String toString() {
            return "Todo";
        }
    },
    IN_PROGRESS {
        @Override
        public String toString() {
            return "In Progress";
        }
    },
    COMPLETE {
        @Override
        public String toString() {
            return "Complete";
        }
    },
    CLOSED {
        //status for when a user closes a task without completing it
        @Override
        public String toString() {
            return "Closed";
        }
    }
}
