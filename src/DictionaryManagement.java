import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

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
     * same export to file
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

    /**
     * same write to file
     */
    public void dictionaryExportToFile() {
        try {
            FileWriter writer = new FileWriter(pathFile);
            for (int i = 0; i< dictionary.getLengthDictionary(); i++) {
                Word word = dictionary.getWordIndex(i);
                writer.write(word.getWordTarget() + "<>" + word.getWordExplain() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * look up in the dictionary
     */
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

    public void dictionarySearcher() {
        System.out.print("Enter your word : ");
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

    //Them ham doc-ghi file.
    public void insertFromFile() {
        BufferedReader reader;
        try {
            reader =
                    new BufferedReader(
                            new FileReader(pathFile));
            String line = reader.readLine();
            while (line != null) {
                String[] temp = line.split("<>");
                Word Temp = new Word(temp[0], "<>" + temp[1]);
                database.add(Temp);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void dictionaryExportToFile(String word, String mean) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        String word_add = word + "<>" + mean;
        try {
            File file = new File(pathFile);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(word_add);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
