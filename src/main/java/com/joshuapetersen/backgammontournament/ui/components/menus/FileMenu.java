package com.joshuapetersen.backgammontournament.ui.components.menus;

import com.joshuapetersen.backgammontournament.ui.components.menus.menuitems.SaveMenuItem;
import javafx.scene.control.Menu;

public class FileMenu extends Menu
{
    public FileMenu()
    {
        super("File",null,new SaveMenuItem());
        setup();
    }

    private void setup()
    {
    }
}
