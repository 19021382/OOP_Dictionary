package com.example.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class DictionaryApplication extends Application{
    Stage window;
    Scene scene1, scene2;
    private DictionaryManagement dictionary;
    DictionaryApplication() {
        window = new Stage();
        dictionary = new DictionaryManagement();
        dictionary.insertFromFile();
        dictionary.writeToFile();
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        //scene 1

        Label label = new Label("Main Application Dictionary");
        label.setFont(new Font("Arial", 30));
        label.setTextFill(Color.RED);
        label.setFont(Font.font(null, FontWeight.BOLD, 50));
        label.setTranslateY(100);
        label.setTranslateX(50);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            int count = 0;
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (count % 2 == 0) {
                    label.setScaleY(1.2);
                    label.setScaleX(1.2);
                } else {
                    label.setScaleY(1);
                    label.setScaleX(1);
                }
                count ++;
            }
        });

        Button exit = new Button("EXIT");
        exit.setLayoutX(400);
        exit.setLayoutY(100);
        exit.setLineSpacing(50);
        exit.setStyle("-fx-font: 25 arial; -fx-base: #b6e7c9;");
        Button search = new Button("Goto search");
        search.setStyle("-fx-font: 25 arial; -fx-base: #b6e7c9;");

        search.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == search) {
                //addWordGUI();
                window.setScene(scene2);
            }
        });

        exit.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == exit) {
                window.close();
            }
        });

        HBox layoutSearch = new HBox();
        layoutSearch.getChildren().addAll(search, new Label("                      "), exit);
        layoutSearch.setSpacing(75);
        layoutSearch.getBoundsInParent();
        layoutSearch.setAlignment(Pos.BOTTOM_RIGHT);
        layoutSearch.getChildren().add(new Label("      "));

        VBox layout1 = new VBox();
        layout1.setSpacing(200);
        layout1.getChildren().addAll(label, layoutSearch);
        scene1 = new Scene(layout1,800, 600, Color.RED);

        // scene Search

        Button goBack = new Button("BACK");
        goBack.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == goBack) {
                window.setScene(scene1);
                //addWordGUI();
            }
        });
        StackPane layout2 = new StackPane();
        layout2.getChildren().addAll(goBack);
        scene2 = new Scene(layout2, 500, 700, Color.BLUE);

        window.setScene(scene1);
        window.setTitle("DICTIONARY");

        window.show();
    }




}
