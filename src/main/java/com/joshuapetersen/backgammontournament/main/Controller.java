package com.joshuapetersen.backgammontournament.main;

import com.joshuapetersen.backgammontournament.data.*;
import com.joshuapetersen.backgammontournament.ui.components.BackgammonTournamentTable;
import com.joshuapetersen.backgammontournament.ui.components.CurrentMatchesTable;
import com.joshuapetersen.backgammontournament.ui.components.FinishedMatchesTable;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;

import java.util.Arrays;

import static com.joshuapetersen.backgammontournament.data.GlobalData.*;

public class Controller
{


    @FXML
    BackgammonTournamentTable btt;
    @FXML
    CurrentMatchesTable cmt;
    @FXML
    FinishedMatchesTable fmt;

    public static BackgammonTournamentTable backgammonTournamentTable;
    public static CurrentMatchesTable currentMatchesTable;
    public static FinishedMatchesTable finishedMatchesTable;
    private static BackgammonTournamentData backgammonTournamentData;

    private static void onChanged(ListChangeListener.Change<? extends Player> c)
    {
        System.out.println("changed!");
    }

    @FXML
    public void initialize()
    {
        backgammonTournamentTable = btt;
        currentMatchesTable = cmt;
        finishedMatchesTable = fmt;
        loadData();
        setupListeners();
    }

    private static void loadData()
    {
        Controller.backgammonTournamentData = DataManager.getBackgammonTournamentData();
        PLAYERS_DATA.setAll(backgammonTournamentData.getPlayers());
        backgammonTournamentTable.setItems(PLAYERS_DATA);
        backgammonTournamentData.getCurrentMatches();
        MATCHES_DATA.setAll(backgammonTournamentData.getCurrentMatches());
        currentMatchesTable.setItems(MATCHES_DATA);
        FINISHED_MATCHES.setAll(backgammonTournamentData.getFinishedMatches());

    }

    private void setupListeners()
    {
        PLAYERS_DATA.addListener((ListChangeListener<? super Player>) Controller::onChanged);
        FINISHED_MATCHES.addListener((ListChangeListener<? super MatchInfo>) Controller::onChanged1);
    }

    private static void onChanged1(ListChangeListener.Change<? extends MatchInfo> change)
    {
        System.out.println("changed!");

    }




    public static void updatePlayer(String name)
    {
        Player foundPlayer = null;
        for (Player player : backgammonTournamentTable.getItems())
        {
            if(player.getName().equals(name))
            {
                foundPlayer = player;
                break;
            }
        }
        if(foundPlayer == null) return;
        int total = 0;
        for (MatchInfo matchInfo : MATCHES_DATA)
        {


            if(Arrays.asList(matchInfo.getContestantOne(),matchInfo.getContestantTwo()).contains(foundPlayer.getName()))
            {
                int contestantPoints = matchInfo.getContestantPoints(foundPlayer);
                total += contestantPoints;
            }
        }
        foundPlayer.setTotalPoints(total);
        loadData();
    }
    private static void removeDiscrepancies()
    {
        for (MatchInfo matchInfo : MATCHES_DATA)
        {
//            matchInfo.
        }
    }
}
