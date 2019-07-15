package com.joshuapetersen.backgammontournament.ui.components.menus;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;

public class DynamicMenuBar extends MenuBar
{
    public DynamicMenuBar()
    {
        setup();
    }



    public DynamicMenuBar(Menu... menus)
    {
        super(menus);
        setup();
    }


    private void setup()
    {
        final String os = System.getProperty("os.name");
        if (os != null && os.startsWith("Mac"))
            this.useSystemMenuBarProperty().set(true);
    }
}
