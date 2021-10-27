package com.example.javafx;

import javafx.application.Application;
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

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;



public class DictionaryApplication extends Application{
    Stage window;
    Scene scene1, scene2;
    private DictionaryManagement dictionary;
    DictionaryApplication() {
        window = new Stage();
        dictionary = new DictionaryManagement();
        dictionary.connectDatabase();
        /*
        dictionary.insertFromFile();
        dictionary.sortDictionary();
        dictionary.writeToFile();
         */
        //dictionary.writeToFile();
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
        exit.setMaxWidth(250);
        exit.setMinWidth(250);
        exit.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        Button addWord = new Button("Goto add word");
        addWord.setMinWidth(250);
        addWord.setMaxWidth(250);
        addWord.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");

        addWord.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == addWord) {
                addWordGUI();
            }
        });

        exit.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == exit) {
                window.close();
            }
        });

        HBox layoutAddWord = new HBox();
        layoutAddWord.getChildren().addAll(addWord, exit);
        layoutAddWord.setSpacing(75);
        layoutAddWord.setAlignment(Pos.BASELINE_CENTER);
        layoutAddWord.getBoundsInParent();

        //search
        Button search = new Button("Search");
        search.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        search.setMaxWidth(250);
        search.setMinWidth(250);
        search.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == search) {
                window.setScene(scene2);
            }
        });

        Button showAll = new Button("Show all word");
        showAll.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        showAll.setMinWidth(250);
        showAll.setMaxWidth(250);
        showAll.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == showAll) {
                window.setScene(showAll());
            }
        });
        // layout search
        HBox layoutSearch = new HBox();
        layoutSearch.getChildren().addAll(search, showAll);
        layoutSearch.setAlignment(Pos.BASELINE_CENTER);
        layoutSearch.setSpacing(95);

        HBox convert = new HBox();
        Button convertEnglish = new Button("Translate english");
        convertEnglish.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        convertEnglish.setMinWidth(250);
        convertEnglish.setMaxWidth(250);
        convertEnglish.setOnAction(actionEvent -> {
            window.setScene(translateEnToVi());
        });

        Button convertVietNam = new Button("Translate Vietnamese");
        convertVietNam.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        convertVietNam.setMinWidth(250);
        convertVietNam.setMaxWidth(250);
        convertVietNam.setOnAction(actionEvent -> {
            window.setScene(translateViToEn());
        });

        convert.getChildren().addAll(convertEnglish, convertVietNam);
        convert.setSpacing(75);
        convert.setAlignment(Pos.BASELINE_CENTER);

        VBox layout1 = new VBox();
        layout1.setSpacing(100);
        layout1.getChildren().addAll(label, layoutAddWord, layoutSearch, convert);
        scene1 = new Scene(layout1,800, 600, Color.RED);

        // scene Search
        Button goBack = new Button("BACK");
        goBack.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == goBack) {
                window.setScene(scene1);
            }
        });

        window.setScene(scene1);
        window.setTitle("DICTIONARY");
        window.show();
    }


    /**
     * add word to dictionary
     */
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
                /*
                dictionary.sort();
                dictionary.writeToFile();
                 */
            }
        });
    }

    /**
     * scene search word in dictionary
     *
     * @return scene GUI search
     */
    private Scene searchScene() {
        Label sameTitile = new Label ("                     WORD SEARCH");
        sameTitile.setFont(new Font("Arial", 20));
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
                String s = word.getText();
                if (!s.trim().isEmpty()) {
                    List<Word> words = dictionary.lookup(s);
                    for (int i = 0; i < words.size(); i++) {
                        Button newBt = new Button(words.get(i).toString());
                        newBt.setFont(new Font("Arial", 20));
                        newBt.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
                        newBt.setMinWidth(600);
                        newBt.setMaxWidth(600);
                        int finalI = i;
                        newBt.setOnAction(actionEvent1 -> {
                            if (actionEvent1.getSource() == newBt) {
                                if (words.size() > 0) {
                                    layoutWord.getChildren().subList(0, words.size()).clear();
                                }
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

        return new Scene(layout, 800, 600);
    }

    /**
     * change information word in dictionary
     *
     * @param word
     * @return
     */
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
                    //dictionary.writeToFile();
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
                    //dictionary.writeToFile();

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
                try {
                    Sound.init(word.getWordTarget());
                } catch (PropertyVetoException e) {
                    e.printStackTrace();
                }
            }
        });
        listen.getChildren().addAll(listenWord);


        VBox layout = new VBox();
        layout.getChildren().addAll(h1, h2, removeHBox, target, explain, listen, back);
        layout.setSpacing(50);

        return new Scene(layout, 700, 600, Color.RED);
    }

    /**
     *
     * show all word in dictionary
     * @return
     */
    private Scene showAll() {
        VBox layout = new VBox();

        HBox head = new HBox();
        Button stt = new Button("STT");
        stt.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        stt.setMinWidth(100);

        Button wordExplain = new Button("Word explain");
        wordExplain.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        wordExplain.setMinWidth(300);

        Button wordTarget = new Button("Word target");
        wordTarget.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        wordTarget.setMinWidth(300);

        head.getChildren().addAll(stt, wordExplain, wordTarget);


        HBox end = new HBox();
        Button back = new Button("BACK");
        back.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        back.setAlignment(Pos.BASELINE_RIGHT);
        back.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == back) {
                window.setScene(scene1);
            }
        });
        back.setAlignment(Pos.BASELINE_RIGHT);
        end.getChildren().addAll(back);
        end.setAlignment(Pos.BASELINE_RIGHT);

        layout.getChildren().addAll(head);
        for (int i = 0; i < dictionary.lengthOfDictionary(); i++) {
            HBox mid = new HBox();
            Button n = new Button(String.valueOf(i + 1));
            n.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
            n.setMinWidth(100);

            Button wordT = new Button(dictionary.getWordIndex(i).getWordTarget());
            wordT.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
            wordT.setMinWidth(300);


            Button wordE = new Button((dictionary.getWordIndex(i).getWordExplain()));
            wordE.setMinWidth(300);
            wordE.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");

            Button view = new Button("Show");
            view.setMinWidth(100);
            view.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");

            int finalI = i;
            view.setOnAction(actionEvent -> {
                if (actionEvent.getSource() == view) {
                    window.setScene(changeWord(dictionary.getWordIndex(finalI)));
                }
            });
            mid.getChildren().addAll(n, wordT, wordE, view);
            layout.getChildren().add(mid);
        }

        layout.getChildren().addAll(new Label(), end);
        return new Scene(layout, 800, 700);
    }

    /**
     * GUI translate english to vietnamese
     *
     * @return
     */
    private Scene translateEnToVi() {
        HBox head = new HBox();

        HBox body1 = new HBox();
        TextField text = new TextField();
        text.setAlignment(Pos.BASELINE_CENTER);
        text.setMinWidth(500);
        text.setMaxWidth(500);
        text.setMinHeight(50);
        text.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");

        Button translate = new Button("Translate");
        translate.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");

        body1.getChildren().addAll(translate, text);

        HBox body2 = new HBox();
        Label label = new Label();
        BackgroundFill background_fill = new BackgroundFill(Color.PINK, CornerRadii.EMPTY, Insets.EMPTY);

        Background background = new Background(background_fill);
        label.setBackground(background);
        label.setMinWidth(800);
        label.setMaxWidth(800);
        label.setMinHeight(300);
        label.setWrapText(true);

        body2.getChildren().addAll(label);

        HBox end = new HBox();
        Button back = new Button("BACK");
        back.setStyle("-fx-font: 20 arial; -fx-base: #b6e7c9;");
        back.setAlignment(Pos.BASELINE_RIGHT);
        back.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == back) {
                window.setScene(scene1);
            }
        });
        back.setAlignment(Pos.BASELINE_RIGHT);
        end.getChildren().addAll(back);
        end.setAlignment(Pos.BASELINE_RIGHT);

        translate.setOnAction(actionEvent -> {
            if (actionEvent.getSource() == translate) {
                String textTranslate = text.getText().trim();
                try {
                    label.setText(GoogleAPI.translate("en", "vi", textTranslate));
                    label.setFont(Font.font(null, FontWeight.BOLD, 20));
                    label.setAlignment(Pos.BASELINE_CENTER);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        VBox layout = new VBox();
        layout.getChildren().addAll(head,body1, body2, end);
        layout.setSpacing(40);
        Scene scene = new Scene(layout, 800, 600);
        return scene;
    }

    /**
     * GUI translate vietnamese to english
     *
     * @return
     */
    private Scene translateViToEn() {
        
    }
}


