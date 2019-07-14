package com.joshuapetersen.backgammontournament.ui.components;

import com.joshuapetersen.backgammontournament.data.*;

import com.joshuapetersen.backgammontournament.main.Controller;
import com.joshuapetersen.backgammontournament.utilities.Calculator;
import javafx.scene.control.TableCell;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;

import java.util.regex.Pattern;

/**
 * Code base on <a herf="https://stackoverflow.com/questions/27900344/how-to-make-a-table-column-with-String-datatype-editable-without-changing-it-to">James_D</a> code.
 */
public class PointsCell extends TableCell<MatchInfo, String>
{

    private final TextField textField = new TextField();
    private final Pattern intPattern = Pattern.compile("-?\\d+");
    /**
     * Taken from <a herf="https://stackoverflow.com/questions/11009320/validate-mathematical-expressions-using-regular-expression">Kapa and p.s.w.g</a>
     **/
    private final Pattern exprPattern = Pattern.compile("/^(\\\\d+)\\\\s*([+\\-*\\\\/])\\\\s*(\\\\d+)$/\n");
    private MatchWonBy contestantType = MatchWonBy.NONE;

    public void setContestantType(MatchWonBy contestantType)
    {
        this.contestantType = contestantType;
    }

    public MatchWonBy getContestantType()
    {
        return contestantType;
    }

    public MatchInfo getMatchInfo()
    {
        return (MatchInfo) this.getTableRow().getItem();
    }

    private String newValue = "";


    public PointsCell(MatchWonBy contestantType)
    {
        this.contestantType = contestantType;
        textField.focusedProperty().addListener((obs, wasFocused, isNowFocused) ->
        {
            if (!isNowFocused)
            {
                processEdit();
            }
        });

        textField.setOnAction(event -> processEdit());

    }


    //    private Player getPlayerOne()
//    {
//        return GlobalData.findPlayer(getMatchInfo().getContestantOne());
//    }
//    private Player getPlayerTwo()
//    {
//        return GlobalData.findPlayer(getMatchInfo().getContestantTwo());
//    }
//    private void printScore()
//    {
//        String playerOneInfo = String.format("%s: %d",getPlayerOne().getName(),getPlayerOne().getTotalPoints());
//        String playerTwoInfo = String.format("%s: %d",getPlayerTwo().getName(),getPlayerTwo().getTotalPoints());
//        System.out.println(playerOneInfo);
//        System.out.println(playerTwoInfo);
//    }
    private void processEdit()
    {
        String text = textField.getText();
        if (text.equalsIgnoreCase(getItem()) || text.equalsIgnoreCase(""))
        {
            cancelEdit();
        }
        else
        {

            newValue = text;
            Integer floor = Math.toIntExact(Math.round(Calculator.eval(text)));
            System.out.println(contestantType + ": " + floor);
            commitEdit(floor.toString());
        }
//        storeOldScore(newValue);
    }

    @Override
    public void updateItem(String value, boolean empty)
    {
        super.updateItem(value, empty);
        if (empty)
        {

            setText(null);
            setGraphic(null);
        }
        else if (isEditing())
        {
            setText(null);
            textField.setText(value);
            setGraphic(textField);
        }
        else
        {
            setText(value.toString());
            setGraphic(null);
        }
    }

    @Override
    public void startEdit()
    {
        super.startEdit();
        System.out.println("Editing...");
        Integer value = Integer.parseInt(getItem());
        if (value != null)
        {
            textField.setText(value.toString());
            setGraphic(textField);
            setText(null);
        }
    }

    @Override
    public void cancelEdit()
    {
        super.cancelEdit();
        setText(getItem().toString());
        setGraphic(null);
    }

    // This seems necessary to persist the edit on loss of focus; not sure why:
    @Override
    public void commitEdit(String value)
    {
        super.commitEdit(value);
        MatchInfo matchInfo = (MatchInfo) this.getTableRow().getItem();
        switch (this.contestantType)
        {
            case CONTESTENT_ONE:
                int difference = getMatchInfo().getContestantOnePoints() - Integer.parseInt(value);
                matchInfo.setContestantOnePoints(Integer.parseInt(value));
                matchInfo.checkForGameFinished();
                debugInfo(matchInfo);
                Player player1 = getPlayer();
                Controller.updatePlayer(getMatchInfo().getContestantOne());
                transferRow();

                break;
            case CONTESTENT_TWO:
                matchInfo.setContestantTwoPoints(Integer.parseInt(value));
                matchInfo.checkForGameFinished();
                if (TournamentWinConditions.wonGame(matchInfo.getContestantTwoPoints()))
                    matchInfo.setWinner(matchInfo.getContestantTwo());
                stopEditing(matchInfo);
                debugInfo(matchInfo);
                transferRow();
                Controller.updatePlayer(getMatchInfo().getContestantTwo());


                break;
            case NONE:
                break;

        }

        stopEditing(matchInfo);
        getTableView().refresh();
        Controller.backgammonTournamentTable.refresh();
    }

    private Player getPlayer()
    {
        Player player;
        switch (contestantType)
        {

            case CONTESTENT_ONE:
                return GlobalData.findPlayer(Controller.backgammonTournamentTable.getItems(),
                        getMatchInfo().getContestantOne());


            case CONTESTENT_TWO:
                return GlobalData.findPlayer(Controller.backgammonTournamentTable.getItems(),
                        getMatchInfo().getContestantTwo());

            case NONE:
                break;
        }
        return null;
    }

    private void transferRow()
    {
        MatchInfo matchInfo = getMatchInfo();
        if (!GlobalData.FINISHED_MATCHES.contains(matchInfo) && matchInfo.getGameFinished())
        {
            getTableView().getItems().remove(matchInfo);
            GlobalData.FINISHED_MATCHES.add(matchInfo);
//            Player playerOne = DataManager.findPlayer(matchInfo.getContestantOne());
//            Player playerTwo = DataManager.findPlayer(matchInfo.getContestantTwo());
//            playerOne.addPoints(matchInfo.getContestantOnePoints());
//            playerTwo.addPoints(matchInfo.getContestantTwoPoints());

        }
    }

    private int getContestantPoints(MatchInfo matchInfo, Player player)
    {
        if (matchInfo.getContestantOne().equals(player.getName()))
        {
            return matchInfo.getContestantOnePoints();
        }
        if (matchInfo.getContestantTwo().equals(player.getName()))
        {
            return matchInfo.getContestantTwoPoints();
        }
        return Integer.parseInt(null);
    }

    private boolean matchHasPlayer(MatchInfo matchInfo, Player player)
    {
        return matchInfo.getContestantOne().equals(player.getName()) || matchInfo.getContestantTwo().equals(
                player.getName());
    }

    private void stopEditing(MatchInfo matchInfo)
    {
        boolean stopEditing = matchInfo.getGameFinished() && TournamentWinConditions.wonGame(
                matchInfo.getContestantOnePoints()) || TournamentWinConditions.wonGame(
                matchInfo.getContestantTwoPoints());
        this.setEditable(!matchInfo.getGameFinished());
        this.getTableColumn().setEditable(!stopEditing);
    }

    public void setupUpToolTip()
    {
        MatchInfo matchInfo = (MatchInfo) this.getTableRow().getItem();
        switch (contestantType)
        {

            case CONTESTENT_ONE:
                Tooltip.install(this, new Tooltip(matchInfo.getContestantOne()));
                break;
            case CONTESTENT_TWO:
                Tooltip.install(this, new Tooltip(matchInfo.getContestantTwo()));
                break;
            case NONE:
                break;
        }
    }

    private void debugInfo(MatchInfo matchInfo)
    {

        System.out.println("Player 2: " + matchInfo.getContestantTwo());
        System.out.println("Points 1: " + matchInfo.getContestantOnePoints());
        System.out.println("Points 2: " + matchInfo.getContestantTwoPoints());
        System.out.println("Finished: " + matchInfo.getGameFinished());
        System.out.println("Winnder: " + matchInfo.getWinner());
        System.out.println("Row Finished: " + ((MatchInfo) getTableRow().getItem()).getGameFinished());
        System.out.println("\n");
    }


    private void storeOldScore(String score)
    {
        switch (getContestantType())
        {
            case CONTESTENT_ONE:
//                getMatchInfo().getContestentID(GlobalData.findPlayer(Controller.backgammonTournamentTable.getItems(),getMatchInfo().getContestantOne()));
                getMatchInfo().setContestantOneOldScore(Integer.parseInt(score));
                break;
            case CONTESTENT_TWO:
//                getMatchInfo().getContestentID(GlobalData.findPlayer(Controller.backgammonTournamentTable.getItems(),getMatchInfo().getContestantTwo()));
                getMatchInfo().setContestantTwoOldScore(Integer.parseInt(score));
                break;
        }
    }

    private boolean hasMatchFinished(MatchInfo matchInfo)
    {
        return false;
    }

}

