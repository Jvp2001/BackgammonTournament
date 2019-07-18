package com.joshuapetersen.backgammontournament.ui.components.menus.menuitems;

import com.joshuapetersen.backgammontournament.main.Main;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckMenuItem;

public class DarkModeToggleMenuItem extends CheckMenuItem
{
    public DarkModeToggleMenuItem()
    {
        super("Dark Mode");
        this.selectedProperty().addListener(new ChangeListener<Boolean>()
        {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue)
            {
                boolean value = (boolean) (newValue != null ? newValue : oldValue);
                if(value)
                {
                    Main.stage.getScene().getStylesheets().add("/stylesheets/DarkMode.css");
                }
                else
                    Main.stage.getScene().getStylesheets().remove("/stylesheets/DarkMode.css");

            }
        });
        this.selectedProperty().setValue(true);


    }
}
