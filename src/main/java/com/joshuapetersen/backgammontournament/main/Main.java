package com.joshuapetersen.backgammontournament.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application
{

    public static final int APP_WIDTH = 800, APP_HEIGHT = 600;
    public static Stage stage;
    @Override
    public void start(Stage primaryStage) throws Exception
    {
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/sample.fxml"));
        primaryStage.setTitle("Backgammon Tournament");
        primaryStage.setScene(new Scene(root, APP_WIDTH, APP_HEIGHT));
        primaryStage.getIcons().setAll(new Image("/images/iconbig.png"));
        primaryStage.getScene().getStylesheets().add("/stylesheets/DarkMode.css");
        primaryStage.show();
    }

}
