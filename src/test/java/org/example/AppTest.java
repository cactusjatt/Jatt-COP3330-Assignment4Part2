/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Sarim Jatt
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    ObservableList<Item> itemList = FXCollections.observableArrayList();

    @Test
    void AddItem() {
        itemList.add(new Item());
        assertEquals(1, itemList.size());
    }

    @Test
    void removeItem() {
        ObservableList<Item> itemList = FXCollections.observableArrayList();
        itemList.add(new Item());
        itemList.add(new Item());
        itemList.get(0).setRemove(true);
        for(Item item: itemList)
            if(item.getRemove().isSelected())
                itemList.remove(item);
        assertEquals(itemList.size(), 1);
    }

}