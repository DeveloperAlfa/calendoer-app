package me.developeralfa.calendoer;

/**
 * Created by devalfa on 18/2/18.
 */

public class comments {
    int id;
    String Comment;
    int expense_id;

    public comments(String comment, int expense_id) {
        Comment = comment;
        this.expense_id = expense_id;
    }

    public comments(int id, String comment, int expense_id) {
        this.id = id;
        Comment = comment;
        this.expense_id = expense_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getExpense_id() {
        return expense_id;
    }

    public void setExpense_id(int expense_id) {
        this.expense_id = expense_id;
    }
}
