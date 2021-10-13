package com.example.dictionary;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Optional;

public class HelloApplication extends Application {
    private boolean run = true;


    public void addWordGUI() {
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
        userName.setPromptText("Word target");

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
        });
    }


    @Override
    public void start(Stage stage) throws IOException {
        /*FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

         */
        /*Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("this is title");
        dialog.setHeaderText("Add to dictionary");

        ButtonType addWord = new ButtonType("Add Word", ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addWord, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField wordTarget = new TextField();
        wordTarget.setPromptText("Word Target");
        PasswordField word = new PasswordField();
        word.setPromptText("Word Explain");

        grid.add(new Label("Word Target"), 0, 0);
        grid.add(wordTarget, 1, 0);
        grid.add(new Label("Word Explain"), 0, 1);
        grid.add(word,1, 1);

        Node add = dialog.getDialogPane().lookupButton(addWord);
        add.setDisable(true);

        wordTarget.textProperty().addListener((observable, oldValue, newValue) -> {
            word.textProperty().addListener((observable1, oldValue1, newValue1) -> {
                boolean check = newValue.trim().isEmpty() && newValue1.trim().isEmpty();
                add.setDisable(check);
            });
        });

        dialog.getDialogPane().setContent(add);
        dialog.resultConverterProperty(dialogButton -> {
            if (dialogButton == ButtonData.OK_DONE) {

            }
        });

         */

        while (run) {
            addWordGUI();
        }
}

    public static void main(String[] args) {
        launch();
    }
}