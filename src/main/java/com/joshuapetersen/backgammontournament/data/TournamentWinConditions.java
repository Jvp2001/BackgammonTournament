package com.joshuapetersen.backgammontournament.data;

public class TournamentWinConditions
{
    public static final int POINTS_TO_WIN_MATCH = DataManager.getBackgammonTournamentData().getPointsToWin();
    public static boolean wonGame(int points)
    {
        return points >= POINTS_TO_WIN_MATCH;
    }
}
