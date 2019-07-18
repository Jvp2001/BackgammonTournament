package com.joshuapetersen.backgammontournament.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.joshuapetersen.backgammontournament.main.Controller;
import com.joshuapetersen.backgammontournament.utilities.DataFileHandler;
import com.joshuapetersen.backgammontournament.utilities.FileIO;
import org.hildan.fxgson.FxGson;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;

public class DataManager
{
    private static BackgammonTournamentData backgammonTournamentData;
    private static String path = Objects.requireNonNull(DataManager.class.getClassLoader().getResource(
            "data/TournamentData.json")).getPath();

    public static String[] names;
    public static MatchInfo[] matches;
    private static GsonBuilder gsonBuilder = new GsonBuilder().setPrettyPrinting();
    private static File dataFile = DataFileHandler.getDataFile();
    static
    {
        FxGson.addFxSupport(gsonBuilder);
        retrieveTournamentData();
    }

    public static final File TEMPLATE_DATA_FILE = new File("data/TournamentData.json");

    //        System.out.println("Players\n====================================\n");
//        for (Player player : GlobalData.PLAYERS_DATA)
//        {
//            System.out.println(player);
//        }

    public static BackgammonTournamentData retrieveTournamentData()
    {

        try
        {
            File file = DataFileHandler.getDataFile();

            //createFile(file);

            System.out.println("Can read: " + dataFile.canRead());
//            if (backgammonTournamentData != null) return backgammonTournamentData;
            Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();

            BackgammonTournamentData backgammonTournamentData = gson.fromJson(new FileReader(dataFile),
                    BackgammonTournamentData.class);

            Player[] players = backgammonTournamentData.getPlayers();

//            for (int i = 0; i < players.length; i++)
//            {
//                Player player = players[i];
//                names[i] = player.getContestantOne();
//            }
            DataManager.backgammonTournamentData = backgammonTournamentData;
            return backgammonTournamentData;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static BackgammonTournamentData getBackgammonTournamentData()
    {


        return DataManager.backgammonTournamentData;
    }

    /**
     * This will set the players in the tournament, overriding any existent players.
     */
    public static void overridePlayers(Player... players)
    {
        getBackgammonTournamentData().setPlayers(players);
        storeTournamentData();
    }

    public static void storeTournamentData()
    {
        Gson gson = FxGson.coreBuilder().setPrettyPrinting().disableHtmlEscaping().create();
        String json = gson.toJson(getBackgammonTournamentData());
        try
        {

            gson.toJson(getBackgammonTournamentData(), new FileWriter(
                    path));
            System.out.println(path);
            try (Writer writer = new FileWriter(
                    path))
            {

                gson.toJson(backgammonTournamentData, writer);
            }

            URL resource = DataManager.class.getClassLoader().getResource("tournament/data/TournamentData.json");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("Output.json")))
            {
                System.out.println(json);
                writer.write(json);
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void setBackgammonTournamentData(
            BackgammonTournamentData backgammonTournamentData)
    {
        DataManager.backgammonTournamentData = backgammonTournamentData;
    }


    public static MatchInfo[] getMatches()
    {
        return getBackgammonTournamentData().getCurrentMatches().toArray(new MatchInfo[0]);
    }

    public static Player findPlayer(String name)
    {
        return getBackgammonTournamentData().findPlayer(name);
    }
    private static void createFile(File file)
    {
        Gson gson = gsonBuilder.create();
        if(!file.exists())
        {
            try
            {
                Files.copy(TEMPLATE_DATA_FILE.toPath(),file.toPath());
                //gson.toJson(backgammonTournamentData,new FileWriter(file));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }

        }
    }

    public static void saveData()
    {

        Gson gson = gsonBuilder.create();
        Player[] player = Controller.backgammonTournamentTable.getItems().toArray(new Player[0]);
        MatchInfo[] currentMatches = Controller.currentMatchesTable.getItems().toArray(new MatchInfo[0]);
        MatchInfo[] finishedMatches = Controller.finishedMatchesTable.getItems().toArray(new MatchInfo[0]);
        FileIO.writeFile(dataFile.getPath(),gson.toJson(new BackgammonTournamentData(
                player, currentMatches, finishedMatches)));
    }
}
