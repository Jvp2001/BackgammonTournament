package com.joshuapetersen.backgammontournament.utilities;

import com.sun.javafx.PlatformUtil;

import java.io.File;

public class DataFileHandler
{
    public static File getDataFile()
    {
        if(PlatformUtil.isWindows())
        {
            return new File("d:\\Users\\mirro\\Documents\\BoardGames\\Tournaments\\Backgammon\\TournamentData.json");
        }
        else
        {
            return new File("~/Documents/BoardGames/Tournaments/Backgammon/TournamentData.json");
        }
    }
}
