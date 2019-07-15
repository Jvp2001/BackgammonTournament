package com.joshuapetersen.backgammontournament.ui.components.menus.menuitems;

import com.joshuapetersen.backgammontournament.data.DataManager;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class SaveMenuItem extends MenuItem
{
    public SaveMenuItem()
    {
        super("Save");
        this.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.META_DOWN, KeyCombination.SHORTCUT_DOWN));
        this.setOnAction(event ->
        {
            System.out.println("Saving...");
            DataManager.saveData();
        });
    }

}
