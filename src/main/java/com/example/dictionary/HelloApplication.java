package com.example.javafx;

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
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Hello!");
        stage.setScene(scene);

        Button button = new Button("dme m hoc di");

        StackPane layout = new StackPane();
        Scene scene1 = new Scene(layout, 500, 700);
        stage.setScene(scene1);
        stage.show();

        stage.setTitle("Dictionary");
        button = new Button("search");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("title");
                alert.setHeaderText("Alert heading");
                alert.setContentText("choose");

                ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.YES);
                ButtonType no = new ButtonType("No", ButtonBar.ButtonData.NO);
                ButtonType cancel = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

                alert.getButtonTypes().setAll(yes, no, cancel);
                Optional<ButtonType> res = alert.showAndWait();

                if (res.get().getButtonData() == ButtonBar.ButtonData.YES) {
                    System.out.println("Yes");
                }
                if (res.get().getButtonData() == ButtonBar.ButtonData.NO) {
                    System.out.println("No");
                }
                if (res.get().getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE) {
                    System.out.println("Cancel");
                }

                String message = res.get().getText();
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setTitle("alert1");
                alert1.setHeaderText("Heading 1");
                alert1.setContentText("content alert1");

            }
        });
        //Button button1 = new Button("remove");
        StackPane layout = new StackPane();
        layout.getChildren().addAll(button);
        Scene scene = new Scene(layout, 500, 700);
        stage.setScene(scene);
        stage.show();

        Label label = new Label("Các chức năng của Từ điển");
        CheckBox box1 = new CheckBox("add word");
        CheckBox box2 = new CheckBox("remove word");
        CheckBox box3 = new CheckBox("change information word");

        Button button = new Button();
        button.setText("hello ban oi");
        button.setMaxHeight(100);
        button.setMaxWidth(150);
        button.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == button) {
                addWordGUI();
            }
        });

        box1.setSelected(true);
        Button button1 = new Button("submit");
        button1.setLayoutX(300);
        button1.setLayoutY(300);
        button1.setOnAction(e -> {
            String choose = null;
            if (box1.isSelected()) {
                choose = box1.getText();
            }
            if (box2.isSelected()) {
                choose = box2.getText();
            }
            if (box3.isSelected()) {
                choose = box3.getText();
            }
            System.out.println(choose);

        });

        HBox layoutH = new HBox(20);
        layoutH.getChildren().addAll(box1, box2, box3);

        VBox layoutV = new VBox(50);
        layoutV.getChildren().addAll(label, layoutH, button1, button);
        Scene scene = new Scene(layoutV, 500, 700);
        stage.setScene(scene);
        stage.show();

         */

        DictionaryApplication app = new DictionaryApplication();
        try {
            app.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void runApplication(Stage stage) {

    }

    public static void addWordGUI() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("ADD WORD TO DICTIONARY");
        dialog.setHeaderText("Fill in the gaps");
        ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField userName = new TextField();
        //userName.setPromptText("Word target");

        //PasswordField passWord = new PasswordField();

        TextField passWord = new TextField();
        passWord.setPromptText("Word explain ");

        grid.add(new Label("Word target :"), 0 , 0);
        grid.add(userName, 1, 0);
        grid.add(new Label("Word explain :"), 0, 1);
        grid.add(passWord, 1, 1);



        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(dialogButton ->{
            if (dialogButton == loginButtonType) {
                return new Pair<>(userName.getText(), passWord.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> res = dialog.showAndWait();
        res.ifPresent(userNamePassword -> {
            String wordTarget = userNamePassword.getKey();
            String wordExplain = userNamePassword.getValue();
            System.out.println(wordExplain);
            System.out.println(wordTarget);
            Word word = new Word(wordTarget, wordExplain);

        });
    }


}