package com.joshuapetersen.backgammontournament.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BackgammonTournamentData
{
    @SerializedName(value = "tournamentName", alternate = {"Name", "name", "Tournament Name", "TournamentName"})
    private String tournamentName = "Backgammon Tournament";
    @SerializedName(value = "pointsToWin", alternate = {"Points To Win", "PointsToWin"})
    private int pointsToWinGame = 11;
    @SerializedName(value = "players", alternate = {"Players", "Contestants", "contestants"})
    private Player[] players;

    private List<MatchInfo> currentMatches = new ArrayList<>();
    private List<MatchInfo> finishedMatches = new ArrayList<>();

    //transient MatchInfo[][] currentMatches;
//    private BackgammonTournamentData(Player[] players)
//    {BackgammonTournamentData
//        this.players.setAll(Arrays.asList(players));
//    }
//    public BackgammonTournamentData() {}

    public BackgammonTournamentData(String tournamentName, int pointsToWinGame, Player... players)
    {
        this.tournamentName = tournamentName;
        this.pointsToWinGame = pointsToWinGame;
        this.players = players;

    }

    public BackgammonTournamentData(String tournamentName, int pointsToWinGame,
            Player[] players, MatchInfo... matches)
    {
        this.tournamentName = tournamentName;
        this.pointsToWinGame = pointsToWinGame;
        this.players = players;
        this.currentMatches = Arrays.asList(matches);

    }

    public BackgammonTournamentData(Player[] players,
            List<MatchInfo> currentMatches,
            List<MatchInfo> finishedMatches)
    {
        this.players = players;
        this.currentMatches = currentMatches;
        this.finishedMatches = finishedMatches;
    }
    //    public  void storeMatches()
//    {
//        Player[] players1 = this.players;
//        currentMatches = new MatchInfo[this.getPlayers().length-2][this.getPlayers().length-2];
//        for (int i = 0; i < players1.length; i++)
//        {
//            Player player = players1[i];
//            player.setMatches(new MatchInfo[players.length-2]);
//            for(int j = 0; i < players.length; i++)
//            {
//
//                String name = player.getContestantOne();
//                if(!name.equalsIgnoreCase(player.getContestantOne()) && !player.hasMatches())
//                {
//                    player.getCurrentMatches()[j] = new MatchInfo(name,0);
//                }
//                else if (player.hasMatches())
//                {
//                    break;
//                }
//            }
//                currentMatches[i] = player.getCurrentMatches();
//
//
//        }
//        DataManager.storeTournamentData();
//    }

    public BackgammonTournamentData(String tournamentName, int pointsToWinGame)
    {
        this.tournamentName = tournamentName;
        this.pointsToWinGame = pointsToWinGame;
    }

    public BackgammonTournamentData(Player[] player, MatchInfo[] currentMatches, MatchInfo[] finishedMatches)
    {
        this.players = player;
        this.currentMatches = Arrays.asList(currentMatches);
        this.finishedMatches = Arrays.asList(finishedMatches);
    }


    private void setup()
    {

    }

    public String getTournamentName()
    {
        return tournamentName;
    }

    public Player[] getPlayers()
    {
        return players;
    }

    public int getPointsToWin()
    {
        return pointsToWinGame;
    }


    public void setPlayers(Player[] players)
    {
        this.players = players;
    }


    public Player findPlayer(String name)
    {
        for (Player player : players)
        {
            if (name.equalsIgnoreCase(player.getName()))
                return player;
        }
        return null;
    }


    public List<MatchInfo> getCurrentMatches()
    {
        return currentMatches;
    }

    public List<MatchInfo> getFinishedMatches()
    {
        return finishedMatches;
    }
}
