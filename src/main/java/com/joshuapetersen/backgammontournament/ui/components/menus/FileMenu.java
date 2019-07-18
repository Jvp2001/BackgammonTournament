package com.joshuapetersen.backgammontournament.ui.components.menus;

import com.joshuapetersen.backgammontournament.ui.components.menus.menuitems.DarkModeToggleMenuItem;
import com.joshuapetersen.backgammontournament.ui.components.menus.menuitems.SaveMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;

public class FileMenu extends Menu
{
    public FileMenu()
    {
        super("File", null, new SaveMenuItem(), new SeparatorMenuItem(),
                new Menu("Settings", null, new DarkModeToggleMenuItem()));
        setup();
    }

    private void setup()
    {
    }
}
