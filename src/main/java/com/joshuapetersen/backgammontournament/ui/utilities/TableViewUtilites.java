package com.joshuapetersen.backgammontournament.ui.utilities;

import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableViewUtilites
{


    public static <T, C> void setupColumn(TableColumn<T, C> tableColumn, String propertyName, boolean editable)
    {
        tableColumn.setEditable(editable);
        tableColumn.setCellValueFactory(new PropertyValueFactory<T, C>(propertyName));

    }

}
