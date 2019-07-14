package com.joshuapetersen.backgammontournament.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GlobalData
{
    public static final ObservableList<Player> PLAYERS_DATA = FXCollections.observableArrayList();
    public static final ObservableList<MatchInfo> MATCHES_DATA = FXCollections.observableArrayList();
    public static final ObservableList<MatchInfo> FINISHED_MATCHES = FXCollections.observableArrayList();

    public static Player findPlayer(ObservableList<Player> players, String name)
    {
        final Player[] selectedPlayer = {null};
        players.forEach(player ->
        {
            selectedPlayer[0] = player.getName() == name ? player : null;

        });
        return selectedPlayer[0];
    }


}
