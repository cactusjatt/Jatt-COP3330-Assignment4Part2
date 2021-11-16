/*
 *  UCF COP3330 Fall 2021 Assignment 4 Solution
 *  Copyright 2021 Sarim Jatt
 */

package ucf.assignments;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Controller {

    @FXML private TableView<Item> tableView;
    @FXML private TableColumn<Item, String> NameColumn;
    @FXML private TableColumn<Item, String> DescriptionColumn;
    @FXML private TableColumn<Item, String> DueDateColumn;
    @FXML private TableColumn<Item, CheckBox> MarkCompleteColumn;
    @FXML private TableColumn<Item, CheckBox> RemoveColumn;

    ObservableList<Item> ItemList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        NameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        NameColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                                       @Override
                                       public void handle(TableColumn.CellEditEvent<Item, String> t) {
                                           (t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
                                       }
                                   }
        );
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        DescriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DescriptionColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                                              @Override
                                              public void handle(TableColumn.CellEditEvent<Item, String> t) {
                                                  String temp = t.getNewValue();
                                                  int tempLength = temp.length();
                                                  if(tempLength <= 256 && tempLength > 0)
                                                      (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDescription(temp);
                                                  else
                                                      (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDescription("Invalid Description!");
                                                  tableView.refresh();
                                              }
                                          }
        );
        DueDateColumn.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
        DueDateColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        DueDateColumn.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Item, String>>() {
                                          @Override
                                          public void handle(TableColumn.CellEditEvent<Item, String> t) {
                                              String temp = t.getNewValue();
                                              LocalDate date = LocalDate.parse(temp);
                                              (t.getTableView().getItems().get(t.getTablePosition().getRow())).setDueDate(date.toString());
                                          }
                                      }
        );
        MarkCompleteColumn.setCellValueFactory(new PropertyValueFactory<>("MarkComplete"));
        RemoveColumn.setCellValueFactory(new PropertyValueFactory<>("Remove"));

        tableView.setItems(ItemList);
        tableView.setEditable(true);
    }


    @FXML
    protected void AddItem() {

        ItemList.add(new Item());
    }

    @FXML
    protected void removeItem() {

        for(Item item: ItemList) {
            if(item.getRemoveBoolean())
                ItemList.remove(item);
        }
    }

    @FXML
    protected void ClearList() {

        tableView.getItems().clear();
    }

    @FXML
    protected void DisplayAllItemsInList() {

        tableView.setItems(ItemList);
    }

    @FXML
    protected void DisplayCompleteItemsInList() {

        ObservableList<Item> tempList = FXCollections.observableArrayList();
        for(Item item: ItemList) {
            if(item.getMarkCompleteBoolean())
                tempList.add(item);
        }
        tableView.setItems(tempList);
    }

    @FXML
    protected void DisplayIncompleteItemsInList() {
        /*  Make ObservableList<Item> tempList
            Add all incomplete items to it using for each loop
            tableView.set(tempList) */

        ObservableList<Item> tempList = FXCollections.observableArrayList();
        for(Item item: ItemList) {
            if(!item.getMarkCompleteBoolean())
                tempList.add(item);
        }
        tableView.setItems(tempList);
    }

    @FXML
    protected void SaveList() throws Exception {
        FileWriter output = new FileWriter(Path.of("").toAbsolutePath()+"\\output.txt");
        for(Item item: ItemList) {
            output.write(item.getName() + " ");
            output.write(item.getDescription() + " ");
            output.write(item.getDueDate() + " ");

            if(item.getMarkCompleteBoolean())
                output.write("1 ");
            else
                output.write("0 ");

            if(item.getRemoveBoolean())
                output.write("1 ");
            else
                output.write("0 ");

            output.write("\n");
        }
        output.close();
    }

    @FXML
    protected void LoadList() throws Exception {
        ItemList.clear();
        File input = new File(Path.of("").toAbsolutePath()+"\\output.txt");
        Scanner scan = new Scanner(input);

        while(scan.hasNextLine()) {
            ArrayList<String> temp = new ArrayList<>();
            String str = scan.nextLine();
            StringTokenizer Tokenizer = new StringTokenizer(str);
            while(Tokenizer.hasMoreTokens()) {
                temp.add(Tokenizer.nextToken());
            }
            ItemList.add(new Item());
            int size = ItemList.size()-1;
            ItemList.get(size).setName(temp.get(0));
            ItemList.get(size).setDescription(temp.get(1));
            ItemList.get(size).setDueDate(temp.get(2));

            if(temp.get(3).equals("1"))
                ItemList.get(size).setMarkComplete(true);
            else
                ItemList.get(size).setMarkComplete(false);

            if(temp.get(4).equals("1"))
                ItemList.get(size).setRemove(true);
            else
                ItemList.get(size).setRemove(false);
        }
    }
}