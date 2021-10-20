package com.example.javafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.*;

public class DictionaryApplication extends Application{
    Stage window;
    Scene scene1, scene2, controlWord;
    private DictionaryManagement dictionary;
    DictionaryApplication() {
        window = new Stage();
        dictionary = new DictionaryManagement();
        dictionary.insertFromFile();
        dictionary.writeToFile();
        scene2 = searchScene();
    }

    @Override
    public void start(Stage stage) throws Exception {
        window = stage;

        //scene 1
        Label label = new Label("Main Application Dictionary");
        label.setFont(new Font("Arial", 20));
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
        Button addWord = new Button("Goto add word");
        addWord.setStyle("-fx-font: 25 arial; -fx-base: #b6e7c9;");

        addWord.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == addWord) {
                addWordGUI();
                //window.setScene(scene2);
            }
        });

        exit.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == exit) {
                window.close();
            }
        });

        HBox layoutAddWord = new HBox();
        layoutAddWord.getChildren().addAll(addWord, new Label("                      "), exit);
        layoutAddWord.setSpacing(75);
        layoutAddWord.setAlignment(Pos.BASELINE_CENTER);
        layoutAddWord.getBoundsInParent();
        //layoutAddWord.setAlignment(Pos.BOTTOM_RIGHT);
        //layoutAddWord.getChildren().add(new Label("      "));

        //search
        Button search = new Button("Search");
        search.setStyle("-fx-font: 25 arial; -fx-base: #b6e7c9;");
        search.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == search) {
                //addWordGUI();
                window.setScene(scene2);
            }
        });


        Button showAll = new Button("SHOW ALL WORD");
        showAll.setStyle("-fx-font: 25 arial; -fx-base: #b6e7c9;");
        showAll.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == showAll) {

            }
        });
        // layout search
        HBox layoutSearch = new HBox();
        layoutSearch.getChildren().addAll(search, showAll);
        layoutSearch.setAlignment(Pos.BASELINE_CENTER);
        layoutSearch.setSpacing(95);

        VBox layout1 = new VBox();
        layout1.setSpacing(100);
        layout1.getChildren().addAll(label, layoutAddWord, layoutSearch);
        scene1 = new Scene(layout1,800, 600, Color.RED);

        // scene Search
        Button goBack = new Button("BACK");
        goBack.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == goBack) {
                window.setScene(scene1);
                //addWordGUI();
            }
        });

        window.setScene(scene1);
        window.setTitle("DICTIONARY");
        window.show();
    }


    public void addWordGUI() {
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("ADD WORD TO DICTIONARY");
        dialog.setHeaderText("Fill in the gaps");
        ButtonType loginButtonType = new ButtonType("ADD WORD", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField userName = new TextField();
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
            if (!wordTarget.trim().isEmpty() && !wordExplain.trim().isEmpty()) {
                System.out.println(wordTarget);
                System.out.println(wordExplain);
                Word word = new Word(wordTarget, wordExplain);
                dictionary.insertFromCommandLine(word);

                dictionary.sort();
                dictionary.writeToFile();
            }
        });
    }

    private Scene searchScene() {
        Label sameTitile = new Label ("                     WORD SEARCH");
        sameTitile.setFont(new Font("Arial", 20));
        //sameTitile.setBackground(new BackgroundFill(Color.RED));
        sameTitile.setAlignment(Pos.BASELINE_CENTER);

        TextField word = new TextField();
        word.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        word.setPromptText("searching");
        HBox layout0 = new HBox();
        Button search0 = new Button("WORD SEARCH");
        search0.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        layout0.getChildren().addAll(search0, word);
        layout0.setSpacing(50);

        HBox layoutSearch1 = new HBox();
        Button search = new Button("SEARCH");
        search.setStyle("-fx-font: 25 arial; -fx-base: #b6e7c9;");
        Button back = new Button("BACK");
        back.setStyle("-fx-font: 25 arial; -fx-base: #b6e7c9;");
        Label label = new Label();

        layoutSearch1.getChildren().addAll(search, new Label("                        "), back);
        layoutSearch1.setAlignment(Pos.BASELINE_RIGHT);
        layoutSearch1.setSpacing(175);

        back.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == back) {
                window.setScene(scene1);
            }
        });


        VBox layoutWord = new VBox();
        search.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == search) {
                Label labelButton = new Label();
                dictionary.showAllWord();
                String s = word.getText();
                if (!s.trim().isEmpty()) {
                    List<Button> list = new LinkedList<Button>();
                    List<Word> words = dictionary.lookup(s);
                    for (int i = 0; i < words.size(); i++) {
                        Button newBt = new Button(words.get(i).toString());
                        newBt.setFont(new Font("Arial", 20));
                        int finalI = i;
                        newBt.setOnAction(actionEvent1 -> {
                            if (actionEvent1.getSource() == newBt) {
                                window.setScene(changeWord(words.get(finalI)));
                            }
                        });
                        layoutWord.getChildren().add(newBt);
                    }
                    System.out.println();
                }
            }
        });

        HBox line1 = new HBox();
        Label inLine1 = new Label("         English               Vietnamese");
        inLine1.setFont(new Font("Arial", 20));
        line1.getChildren().addAll(inLine1);

        VBox layout = new VBox();
        layout.getChildren().addAll(sameTitile, layout0, layoutSearch1, line1, label, layoutWord);
        layout.setSpacing(30);

        Scene scene = new Scene(layout, 800, 600);
        return scene;
    }

    private Scene changeWord(Word word) {
        Label word_target = new Label ("Word Target         " + word.getWordTarget());

        HBox h1 = new HBox();
        word_target.setFont(Font.font(null, FontWeight.BOLD, 20));
        h1.getChildren().addAll(word_target);
        h1.setAlignment(Pos.BASELINE_CENTER);

        Label word_explain = new Label ("Word explain        " + word.getWordExplain());
        word_explain.setFont(Font.font(null, FontWeight.BOLD, 20));
        HBox h2 = new HBox();
        h2.getChildren().addAll(word_explain);
        h2.setAlignment(Pos.BASELINE_CENTER);

        Button back = new Button("BACK");
        back.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        back.setAlignment(Pos.BASELINE_RIGHT);
        back.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == back) {
                window.setScene(scene1);
            }
        });

        HBox removeHBox = new HBox();
        Button remove = new Button("REMOVE");
        remove.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        remove.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == remove) {
                int index = dictionary.indexOf(word);
                dictionary.removeFromCommandLine(word);
                System.out.println("da xoa di tu" + word.toString());
                window.setScene(scene1);
            }
        });
        removeHBox.getChildren().addAll(remove);

        HBox target = new HBox();
        TextField setTarget = new TextField();
        setTarget.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        Button setter = new Button("SET WORD TARGET");
        setter.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        setter.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == setter) {
                String newTarget = setTarget.getText().trim();
                if (newTarget.isEmpty()) {
                    System.out.println("word target is empty");
                } else {
                    int index = dictionary.indexOf(word);
                    dictionary.getWordIndex(index).setWordTarget(newTarget);
                    dictionary.showAllWord();
                    dictionary.writeToFile();
                    window.setScene(changeWord(dictionary.getWordIndex(index)));
                }
            }
        });
        target.getChildren().addAll(setter, setTarget);
        target.setSpacing(50);


        HBox explain = new HBox();
        TextField setExplain = new TextField();
        explain.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        Button setterExplain = new Button("SET WORD EXPLAIN");
        setterExplain.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        setterExplain.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == setterExplain) {
                String newExplain = setExplain.getText().trim();
                if (newExplain.isEmpty()) {
                    System.out.println("word explain is empty");
                } else {
                    int index = dictionary.indexOf(word);
                    dictionary.getWordIndex(index).setWordExplain(newExplain);
                    dictionary.showAllWord();
                    dictionary.writeToFile();
                    window.setScene(changeWord(dictionary.getWordIndex(index)));
                }
            }
        });
        explain.getChildren().addAll(setterExplain, setExplain);
        explain.setSpacing(50);


        HBox listen = new HBox();
        Button listenWord = new Button("LISTEN");
        listenWord.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        listenWord.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == listenWord) {
                dictionary.listenedWord(word);
            }
        });
        listen.getChildren().addAll(listenWord);


        VBox layout = new VBox();
        layout.getChildren().addAll(h1, h2, removeHBox, target, explain, listen, back);
        layout.setSpacing(50);
        Scene scene = new Scene(layout, 700, 600, Color.RED);

        return scene;
    }
}


