/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Sarim Jatt
 */

package ucf.assignments;

import javafx.scene.control.*;

public class Item {
    // Class's attributes
    private String Name;
    private String Description;
    private String DueDate;
    private CheckBox MarkComplete;
    private CheckBox Remove;

    public Item() {
        this.Name = "Item";
        this.Description = "Description";
        this.DueDate = "1900-01-01";
        this.MarkComplete = new CheckBox();
        this.Remove = new CheckBox();
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getDueDate() {
        return DueDate;
    }

    public void setDueDate(String DueDate) {
        this.DueDate = DueDate;
    }

    public CheckBox getMarkComplete() {
        return MarkComplete;
    }

    public Boolean getMarkCompleteBoolean() {
        return MarkComplete.isSelected();
    }

    public void setMarkComplete(Boolean check) {
        this.MarkComplete.setSelected(check);
    }

    public CheckBox getRemove() {
        return Remove;
    }

    public Boolean getRemoveBoolean() {
        return Remove.isSelected();
    }

    public void setRemove(Boolean check) {
        this.Remove.setSelected(check);
    }

}