import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.FileReader;
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
}
