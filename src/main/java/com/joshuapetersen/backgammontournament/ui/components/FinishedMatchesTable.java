package com.joshuapetersen.backgammontournament.ui.components;

import com.google.gson.Gson;
import com.joshuapetersen.backgammontournament.data.GlobalData;
import com.joshuapetersen.backgammontournament.data.MatchInfo;
import com.joshuapetersen.backgammontournament.data.MatchWonBy;
import com.joshuapetersen.backgammontournament.ui.utilities.TableViewUtilites;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import org.hildan.fxgson.FxGsonBuilder;

import static com.joshuapetersen.backgammontournament.data.GlobalData.*;


public class FinishedMatchesTable extends TableView<MatchInfo>
{

    private TableColumn<MatchInfo, char[]> opponentNameColumn = new TableColumn<>("Opponents");
    private TableColumn<MatchInfo, char[]> opponentOneNameColumn = new TableColumn<>("Opponent One");
    private TableColumn<MatchInfo, char[]> opponentTwoNameColumn = new TableColumn<>("Opponent Two");


    private TableColumn<MatchInfo, Integer> pointsColumn = new TableColumn<>("Points");
    //TODO Add change listeners to cell values for the point columns.
    private TableColumn opponentOnePoints = new PointsTableColumn("Opponent One", MatchWonBy.CONTESTENT_ONE);
    private TableColumn opponentTwoPoints = new PointsTableColumn("Opponent Two", MatchWonBy.CONTESTENT_TWO);

    private TableColumn<MatchInfo, Boolean> gameFinishedColumn = new TableColumn<>("Finished");
    private TableColumn<MatchInfo, String> wonByTableColumn = new TableColumn<>("Won By");
 

    private SimpleStringProperty contestantName = new SimpleStringProperty("Joshua");

    public FinishedMatchesTable()
    {
        setup();
    }


    public void setup()
    {
        Gson gson = new FxGsonBuilder().create();
        MatchInfo matchInfo = gson.fromJson("" +
                "{" +
                "\"contestantOne\":\"Joshua\"," +
                "\"contestantTwo\":\"Dominic\"," +
                "\"contestantOnePoints\":0," +
                "\"contestantTwoPoints\":0" +
                "}", MatchInfo.class);
        //setup table

        final TableView<MatchInfo> resultsTable = this;
        resultsTable.setEditable(false);
        resultsTable.getColumns().setAll(opponentNameColumn, pointsColumn, gameFinishedColumn, wonByTableColumn);
        resultsTable.setItems(FINISHED_MATCHES);
        resultsTable.setTableMenuButtonVisible(true);
        resultsTable.getStylesheets().setAll(getClass().getClassLoader().getResource(
                "stylesheets/DefaultTableStyles.css").toExternalForm());
        // GUIUtils.autoFitTable(this);
        //setup columns
        //setupColumn(rankColumn,"rank",false);
        //TODO Get rid of opponent name columns being editable.
        setupColumn(opponentNameColumn, "", false);
        setupColumn(opponentOneNameColumn, "contestantOne", false);
        opponentOneNameColumn.setCellFactory(param -> new TextFieldTableCell<>());
        setupColumn(opponentTwoNameColumn, "contestantTwo", false);
        opponentTwoNameColumn.setCellFactory(param -> new TextFieldTableCell<>());
        opponentNameColumn.getColumns().addAll(opponentOneNameColumn, opponentTwoNameColumn);

        //GUIUtils.autoFitTable(this, opponentNameColumn);
        setupColumn(pointsColumn, "", false);


        pointsColumn.getColumns().setAll(opponentOnePoints, opponentTwoPoints);

        setupColumn(gameFinishedColumn, "gameFinished", false);
        gameFinishedColumn.setCellFactory(param ->
        {
            CheckBoxTableCell<MatchInfo, Boolean> matchInfoBooleanCheckBoxTableCell = new CheckBoxTableCell<>();
            matchInfoBooleanCheckBoxTableCell.setSelectedStateCallback(new Callback<Integer, ObservableValue<Boolean>>()
            {
                @Override
                public ObservableValue<Boolean> call(Integer param)
                {

                    return new SimpleBooleanProperty(getItems().get(param).getGameFinished());
                }
            });

            return matchInfoBooleanCheckBoxTableCell;
        });
        setupColumn(wonByTableColumn, "winner", false);
        resultsTable.refresh();
        for (TableColumn<MatchInfo, ?> column : resultsTable.getColumns())
        {
            column.setSortable(false);
        }

    }

    private <T> void setupColumn(TableColumn<MatchInfo, T> tableColumn, String propertyName, boolean editable,
            String... cssClasses)
    {
        TableViewUtilites.setupColumn(tableColumn, propertyName, editable);
        tableColumn.getStyleClass().setAll(cssClasses);
    }

    public String getContestantName()
    {
        return contestantName.get();
    }

    public SimpleStringProperty contestantNameProperty()
    {
        return contestantName;
    }

    public void setContestantName(String contestantName)
    {
        this.contestantName.set(contestantName);
    }
}

