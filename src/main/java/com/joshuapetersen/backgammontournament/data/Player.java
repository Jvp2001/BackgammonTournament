package com.joshuapetersen.backgammontournament.data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Player
{
    private String name;
    private int rank = 1, gamesPlayed = 0, gamesWon = 0, gamesLost = 0, totalPoints = 0;
    private transient ObservableList<MatchInfo> matches = FXCollections.observableArrayList();

    public Player(String name)
    {
        this.name = name;
        setup();
    }

    public Player(String name, int gamesPlayed, int gamesWon, int totalPoints)
    {
        this.name = name;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.totalPoints = totalPoints;
        setup();
    }

    public Player(String name, int rank, int gamesPlayed, int gamesWon, int gamesLost, int totalPoints)
    {
        this.name = name;
        this.rank = rank;
        this.gamesPlayed = gamesPlayed;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.totalPoints = totalPoints;
        setup();


    }

    private void setup()
    {
        System.out.println(name + ": "+String.valueOf(matches.isEmpty()));
//        matches.addListener((ListChangeListener<MatchInfo>) c ->
//        {
//            while (c.next())
//            {
//                if (c.wasUpdated())
//                {
//                    int start = c.getFrom();
//                    int end = c.getTo();
//                    for (int i = start; i < end; i++)
//                    {
//                        MatchInfo matchInfo = c.getList().get(i);
//                        System.out.println(matchInfo);
//                        System.out.println(
//                                "Element at position " + i + " was updated to: " + (matchInfo.getContestentID(this) == Contestant.ONE ? matchInfo.getContestantOne() : matchInfo.getContestantTwo()));
//                        this.addPoints(matchInfo.getScoreToAddOn(this));
//                        //TODO Add the update matches won and matches played.
//
//                    }
//                }
//            }
//        });
    }

    private StringBuilder getStringMatches()
    {
        StringBuilder Matches = new StringBuilder();
        for (MatchInfo match : matches)
        {
            Matches.append(match);
        }
        return Matches;
    }

    public void addPoints(int amount)
    {
        this.totalPoints += amount;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getRank()
    {
        return rank;
    }

    public void setRank(int rank)
    {
        this.rank = rank;
    }

    public int getGamesPlayed()
    {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed)
    {
        this.gamesPlayed = gamesPlayed;
    }

    public int getGamesWon()
    {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon)
    {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost()
    {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost)
    {
        this.gamesLost = gamesLost;
    }

    public int getTotalPoints()
    {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints)
    {
        this.totalPoints = totalPoints;
    }

    public ObservableList<MatchInfo> getMatches()
    {
        return matches;
    }

    public void setMatches(ObservableList<MatchInfo> matches)
    {
        this.matches = matches;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Player)
        {
            Player pl = (Player) obj;
            return this.name.equals(pl.name);

        }
        return false;
    }


    @Override
    public String toString()
    {
        return "Player{" +
                "name='" + name + '\'' +
                ", rank=" + rank +
                ", gamesPlayed=" + gamesPlayed +
                ", gamesWon=" + gamesWon +
                ", gamesLost=" + gamesLost +
                ", totalPoints=" + totalPoints +
//                ", matches=" + getStringMatches().toString() +
                '}';
    }
}
