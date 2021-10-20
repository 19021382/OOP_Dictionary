package com.example.dictionary;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

import static javafx.scene.paint.Color.*;

public class HelloApplication extends Application{
    Button button;
    final String pathFile = "dictionaries.txt";
    protected DictionaryManagement dictionary = new DictionaryManagement();

    public static void main(String[] args) {
        launch();

    }

    @Override
    public void start(Stage stage) throws IOException {
        

        DictionaryApplication app = new DictionaryApplication();
        try {
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}