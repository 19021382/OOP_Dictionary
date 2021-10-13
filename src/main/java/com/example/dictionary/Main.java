package com.example.dictionary;

import javafx.stage.Stage;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	// write your code here

        /*Word word1 = new Word("hello", "xin chao");
        Word word2 = new Word("bye", "tam biet");
        Word word3 = new Word("school", "truong hoc");
        Word word4 = new Word("sea", "bien");
        Word word5 = new Word("candy", "keo");
        DictionaryCommandLine dictionary = new DictionaryCommandLine();
        dictionary.insertFromCommandLine(word1);
        dictionary.insertFromCommandLine(word1);
        dictionary.insertFromCommandLine(word2);
        dictionary.insertFromCommandLine(word3);
        dictionary.insertFromCommandLine(word4);
        dictionary.insertFromCommandLine(word5);


        dictionary.showAllWord();
        //dictionary.removeFromCommandLine();
        //dictionary.showAllWord();

        System.out.println();
        System.out.println();
        dictionary.sortDictionary();
        dictionary.showAllWord();

        //System.out.println(word5);
        //System.out.println(dictionary.indexOf(word5));
        System.out.println();

        dictionary.insertFromFile();
        dictionary.showAllWord();

        System.out.println();
        dictionary.sortDictionary();
        dictionary.writeToFile();
        dictionary.showAllWord();

        dictionary.dictionaryLookup();

         */
        HelloApplication application = new HelloApplication();
        try {
            application.start(new Stage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
