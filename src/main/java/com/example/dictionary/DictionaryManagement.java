package com.example.javafx;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class DictionaryManagement {

    final String pathFile = "dictionaries.txt";
    protected Dictionary dictionary;
    Scanner sc = new Scanner(System.in);
    DictionaryManagement() {
        dictionary = new Dictionary();
    }

    /**
     * insert word to dictionary
     */
    public void insertFromCommandLine()
    {
        System.out.print("Target word: ");
        String word_target = sc.nextLine();
        word_target = word_target.toLowerCase();
        word_target = word_target.trim();

        System.out.print("Explain word: ");
        String word_explain = sc.nextLine();
        word_explain = word_explain.toLowerCase();
        word_explain = word_explain.trim();

        Word word = new Word(word_target, word_explain);
        dictionary.addWordToDictionary(word);
    }

    public void insertFromCommandLine(Word word) {
        dictionary.addWordToDictionary(word);
    }

    /**
     * remove from command line
     */
    public void removeFromCommandLine() {
        System.out.print("Target word: ");
        String word_target = sc.nextLine();
        word_target = word_target.toLowerCase(Locale.ROOT);
        System.out.print("Explain word: ");
        String word_explain = sc.nextLine();
        word_explain = word_explain.toLowerCase(Locale.ROOT);
        Word word = new Word(word_target, word_explain);
        if (dictionary.containsWordInDictionary(word)) {
            dictionary.removeWordInDictionary(word);
            System.out.println("This word is remove dictionary");
        }
        else {
            System.out.println("This word is't contain");
        }
    }

    public void removeFromCommandLine(Word word) {
        dictionary.removeWordInDictionary(word);
    }

    /**
     * print all word in dictionary
     */
    public void showAllWord() {
        for (int i = 0; i < dictionary.getLengthDictionary(); i++) {
            System.out.printf("%-5d ", i);
            dictionary.getInfoWord(i);
        }
    }

    public void sortDictionary() {
        dictionary.sortDictionary();
    }

    public int indexOf(Word word) {
        return dictionary.indexOf(word);
    }

    /**
     *
     * get information word from file.
     */
    public void insertFromFile() {
        Path path = Path.of(pathFile);
        try {
            List<String> list = Files.readAllLines(path);
            for (String str : list) {
                String[] data = str.split("<>");
                String word_target = data[0];
                String word_explain = data[1];
                Word newWord = new Word(word_target, word_explain);
                insertFromCommandLine(newWord);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * use commandline version
     */
    public void writeToFile() {
        try {
            FileWriter write = new FileWriter(pathFile);
            for (int i = 0; i < dictionary.getLengthDictionary(); i++) {
                Word word = dictionary.getWordIndex(i);
                write.write(word.getWordTarget() + "<>" + word.getWordExplain() + "\n");
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dictionaryLookup() {
        System.out.print("Your word lookup: ");
        String charLookUp = sc.next();
        charLookUp = charLookUp.toLowerCase();
        int count = 0;
        for (int i = 0; i < dictionary.getLengthDictionary(); i++) {
            if (dictionary.getWordIndex(i).getWordTarget().contains(charLookUp)) {
                System.out.println(dictionary.getWordIndex(i));
                count ++;
            }
        }
        System.out.println("Have " + count + " word in dictionary");
    }

    /**
     * for 0 -> end
     *
     * @param wordLookup
     * @return
     */
    public String dictionaryLookup(String wordLookup) {
        System.out.println("Your word lookup: " + wordLookup);
        String res = "Word Target        Word Explain  \n";
        for (int i = 0; i < dictionary.getLengthDictionary(); i++) {
            if (dictionary.getWordIndex(i).getWordTarget().contains(wordLookup)) {
                res += dictionary.getWordIndex(i) + "\n";
            }
        }
        return res;
    }

    /**
     * for 0 -> end
     * @param wordLookup
     * @param b
     * @return word target of dictionảy
     */
    public String dictionaryLookup(String wordLookup, boolean b) {
        if (b) {
            System.out.println("Your word lookup: " + wordLookup);
            //String res = "Word Target        Word Explain  \n";
            String res = "";
            if (-1 != dictionary.indexOf(wordLookup)) {
                int n = dictionary.indexOf(wordLookup);
                res += getWordIndex(n).getWordTarget() + "    " + getWordIndex(n).getWordExplain() + "\n";
            } else {
                return "no have word";
            }
            return res;
        } else {
            return "Hello World";
        }
    }

    /**
     * binary search
     *
     * @param wordLookup
     * @return
     */
    public List<Word> lookup(String wordLookup) {
        List<Word> list = new LinkedList<Word>();
        int n = dictionary.indexOf(wordLookup);
        if (n != -1) {
            list.add(dictionary.getWordIndex(n));
            return list;
        }
        System.out.println(wordLookup);
        System.out.println(n);
        return null;
    }

    public static synchronized void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:/Javafx/file.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }

    public static synchronized void playSound(String name) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("D:/Javafx/" + name + ".wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


    public int lengthOfDictionary() {
        return dictionary.getLengthDictionary();
    }

    public void removeWordInDictionary (Word word) {
        dictionary.removeWordInDictionary(word);
    }

    public void sort() {
        dictionary.insertionSortDictionary();
    }

    public Word getWordIndex(int index) {
        return dictionary.getWordIndex(index);
    }

    public void connectDatabase() {
        try {
            Connection connect = DriverManager.getConnection("jdbc:sqlite:D:\\database\\dict_hh.db");
            Statement state = connect.createStatement();
            ResultSet res = state.executeQuery("SELECT word, Description FROM av");
            while (res.next()) {
                Word word = new Word();
                word.setWordTarget(res.getString("word"));
                word.setWordExplain(res.getString("description"));
                dictionary.addWordToDictionary(word);
            }
            System.out.println("done");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("fail sql");
        }
    }


}
