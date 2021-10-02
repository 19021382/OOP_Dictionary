import java.util.Locale;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
public class DictionaryManagement {
    protected Dictionary dictionary;
    DictionaryManagement() {
        dictionary = new Dictionary();
    }
    Scanner sc = new Scanner(System.in);

    /**
     * insert word to dictionary
     */
    public void insertFromCommandLine()
    {
        System.out.print("Target word: ");
        String word_target = sc.nextLine();
        word_target = word_target.toLowerCase(Locale.ROOT);
        System.out.print("Explain word: ");
        String word_explain = sc.nextLine();
        word_explain = word_explain.toLowerCase(Locale.ROOT);
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
    public void insertFromFile() {
        File file = new File ("dictionaries.txt");
    }
}
