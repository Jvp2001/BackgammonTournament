package com.joshuapetersen.backgammontournament.ui.components;

import com.joshuapetersen.backgammontournament.data.Player;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import static com.joshuapetersen.backgammontournament.ui.utilities.TableViewUtilites.setupColumn;

public class BackgammonTournamentTable extends TableView<Player>
{
    private TableColumn<Player, char[]> nameColumn = new TableColumn<>("Name");
    private TableColumn<Player, Integer> gamesWonColumn = new TableColumn<>("Games Won");
    private TableColumn<Player, Integer> totalPointsColumn = new TableColumn<>("Total Points");

    public BackgammonTournamentTable()
    {
        super();
        setup();
    }

    public BackgammonTournamentTable(
            ObservableList<Player> items)
    {
        super(items);
        setup();
    }

    private void setup()
    {
        this.setEditable(true);
        this.getColumns().setAll(nameColumn, gamesWonColumn, totalPointsColumn);

        this.setTableMenuButtonVisible(true);
        this.getSortOrder().setAll(totalPointsColumn);

        //setup columns
        //setupColumn(rankColumn,"rank",false);
        setupColumn(nameColumn, "name", false);
        nameColumn.setSortable(false);
        setupColumn(gamesWonColumn, "gamesWon", false);
        gamesWonColumn.getStyleClass().setAll("centredText");
        gamesWonColumn.setStyle("-fx-alignment: CENTER;");
        gamesWonColumn.setSortable(false);
        setupColumn(totalPointsColumn, "totalPoints", false);
        totalPointsColumn.getStyleClass().setAll("centredText");
        totalPointsColumn.setStyle("-fx-alignment: CENTER;");
        this.getColumns().setAll(nameColumn,gamesWonColumn,totalPointsColumn);

    }
}
