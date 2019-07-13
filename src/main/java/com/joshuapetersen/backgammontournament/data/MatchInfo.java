package com.joshuapetersen.backgammontournament.data;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MatchInfo
{

    //TODO make sure 11 cannot in tie break.

    private SimpleStringProperty contestantOne, contestantTwo;
    private SimpleIntegerProperty contestantOnePoints, contestantTwoPoints;
    private transient boolean gameFinished;
    private transient SimpleBooleanProperty gameFinishedProperty;
    private transient Player playerOne,playerTwo;
    private transient SimpleIntegerProperty contestantOneOldScore;
    private transient SimpleIntegerProperty  contestantTwoOldScore;

    private transient String winner;
    private transient MatchWonBy wonBy;

    public MatchInfo(String contestantOne, String contestantTwo, int contestantOnePoints, int contestantTwoPoints)
    {
        this.contestantOne = new SimpleStringProperty(contestantOne);
        this.contestantTwo = new SimpleStringProperty(contestantTwo);
        this.contestantOnePoints = new SimpleIntegerProperty(contestantOnePoints);
        this.contestantTwoPoints = new SimpleIntegerProperty(contestantTwoPoints);
        this.contestantOneOldScore = new SimpleIntegerProperty(0);
        this.contestantTwoOldScore = new SimpleIntegerProperty(0);

        playerOne = DataManager.getBackgammonTournamentData().findPlayer(this.getContestantOne());
        playerTwo = DataManager.getBackgammonTournamentData().findPlayer(this.getContestantTwo());


        checkForGameFinished();

        this.gameFinishedProperty = new SimpleBooleanProperty(this.gameFinished);
        this.contestantOneOldScore.addListener((observable, oldValue, newValue) ->
        {
            int value = (int) (newValue != null ? newValue : oldValue);
            this.playerOne.addPoints(this.getScoreToAddOn(this.playerOne));

        });
        this.contestantTwoOldScore.addListener((observable, oldValue, newValue) ->
        {
            int value = (int) (newValue != null ? newValue : oldValue);
            this.playerTwo.addPoints(this.getScoreToAddOn(this.playerTwo));
        });
        this.contestantOnePoints.addListener((observable, oldValue, newValue) ->
        {
            int value = (int) (newValue != null ? newValue : oldValue);
            System.out.println("Property changed");
            if (TournamentWinConditions.wonGame(value))
            {
                System.out.println("Game finished");
                gameFinished = true;
            }

        });
        this.contestantTwoPoints.addListener((observable, oldValue, newValue) ->
        {
            int value = (int) (newValue != null ? newValue : oldValue);
            if (TournamentWinConditions.wonGame(value))
            {
                this.playerTwo.addPoints(this.getScoreToAddOn(this.playerTwo));
                gameFinished = true;
            }
        });

        winner = getContestantTwo();
        wonBy = MatchWonBy.CONTESTENT_TWO;

    }

    public boolean gameFinishedPropertyProperty()
    {
        return gameFinishedProperty.get();
    }

    public void setGameFinishedProperty(boolean gameFinishedProperty)
    {
        this.gameFinishedProperty.set(gameFinishedProperty);
    }


    public void checkForGameFinished()
    {
        this.gameFinished = TournamentWinConditions.wonGame(this.contestantOnePoints.get()) || TournamentWinConditions.wonGame(
                this.getContestantTwoPoints());
        if (TournamentWinConditions.wonGame(this.getContestantOnePoints()))
        {
            this.wonBy = MatchWonBy.CONTESTENT_ONE;
            winner = this.getContestantOne();
        }
        else if (TournamentWinConditions.wonGame(this.contestantTwoPoints.get()))
        {
            System.out.println("TWO!");
            this.wonBy = MatchWonBy.CONTESTENT_TWO;
            this.winner = this.contestantTwo.getName();
        }
        else
        {
            this.wonBy = MatchWonBy.NONE;
        }
        System.out.println(this.winner);
        System.out.println(winner);
    }

    public String getContestantOne()

    {
        return contestantOne.get();
    }

    public SimpleStringProperty contestantOneProperty()
    {
        return contestantOne;
    }

    public void setContestantOne(String contestantOne)
    {
        this.contestantOne.set(contestantOne);
    }

    public String getContestantTwo()
    {
        return contestantTwo.get();
    }

    public SimpleStringProperty contestantTwoProperty()
    {
        return contestantTwo;
    }

    public void setContestantTwo(String contestantTwo)
    {
        this.contestantTwo.set(contestantTwo);
    }

    public int getContestantOnePoints()
    {
        return contestantOnePoints.get();
    }

    public SimpleIntegerProperty contestantOnePointsProperty()
    {
        return contestantOnePoints;
    }

    public void setContestantOnePoints(int contestantOnePoints)
    {
        this.contestantOnePoints.set(contestantOnePoints);
    }

    public int getContestantTwoPoints()
    {
        return contestantTwoPoints.get();
    }

    public SimpleIntegerProperty contestantTwoPointsProperty()
    {
        return contestantTwoPoints;
    }

    public void setContestantTwoPoints(int contestantTwoPoints)
    {
        this.contestantTwoPoints.set(contestantTwoPoints);
    }


    public String getWinner()
    {
        return winner;
    }

    public void setWinner(String winner)
    {
        this.winner = winner;
    }

    public MatchWonBy getWonBy()
    {
        return wonBy;
    }

    public void setWonBy(MatchWonBy wonBy)
    {
        this.wonBy = wonBy;
    }

    public boolean getGameFinished()
    {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished)
    {
        this.gameFinished = gameFinished;
    }

    public boolean isGameFinished()
    {
        return gameFinished;
    }


    public boolean isGameFinishedProperty()
    {
        return gameFinishedProperty.get();
    }

    public Player getPlayerOne()
    {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne)
    {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo()
    {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo)
    {
        this.playerTwo = playerTwo;
    }

    public SimpleIntegerProperty contestantOneOldScoreProperty()
    {
        return contestantOneOldScore;
    }
    public SimpleIntegerProperty contestantTwoOldScoreProperty()
    {
        return contestantTwoOldScore;
    }

    public int getContestantOneOldScore()
    {
        return contestantOneOldScore.get();
    }

    public void setContestantOneOldScore(int contestantOneOldScore)
    {
        this.contestantOneOldScore.set(contestantOneOldScore);
    }

    public int getContestantTwoOldScore()
    {
        return contestantTwoOldScore.get();
    }

    public void setContestantTwoOldScore(int contestantTwoOldScore)
    {
        this.contestantTwoOldScore.set(contestantTwoOldScore);
    }

    public int getScoreToAddOn(Player player)
    {
        int currentPoints = getContestantPoints(player);
        return currentPoints - getConstestantOldPoints(player);
    }

    private int getConstestantOldPoints(Player player)
    {
        switch (getContestentID(player))
        {
            case ONE:
                return getContestantOneOldScore();
            case TWO:
                return getContestantTwoOldScore();
        }
        return Integer.parseInt(null);
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj instanceof Player)
        {
            Player player = (Player) obj;
            return player.getName().equals(this.getContestantOne()) || player.getName().equals(this.getContestantTwo());
        }
        if (obj instanceof MatchInfo)
        {
            MatchInfo matchInfo = (MatchInfo) obj;
            return matchInfo.getContestantOne().equals(this.getContestantOne()) || matchInfo.getContestantTwo().equals(
                    this.getContestantTwo());
        }
        return false;

    }
    public Contestant getContestentID(Player player)
    {
        if(player.getName().equals(this.getContestantOne()))
            return Contestant.ONE;
        else if(player.getName().equals(this.getContestantTwo()))
            return Contestant.TWO;
        return null;
    }
    public int getContestantPoints(Player player)
    {
        switch (getContestentID(player))
        {
            case ONE:
                return getContestantOnePoints();
            case TWO:
                return getContestantTwoPoints();
        }
        return Integer.parseInt(null);
    }

    @Override
    public String toString()
    {
        return "MatchInfo{" +
                "contestantOne=" + contestantOne +
                ", contestantTwo=" + contestantTwo +
                ", contestantOnePoints=" + contestantOnePoints +
                ", contestantTwoPoints=" + contestantTwoPoints +
                ", gameFinished=" + gameFinished +
                ", winner=" + winner +
                ", wonBy=" + wonBy +
                '}';
    }
}

