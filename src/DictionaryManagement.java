import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.*;
public class DictionaryManagement extends Dictionary {
    private static final String DATA_FILE_PATH = "D:\\GITHUB\\OOP_Dictionary\\dictionaries.txt";
    private static final String SPLITTING_CHARACTERS = "<>";

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

    //Them ham insertFromFile.
    public void insertFromFile() {
        BufferedReader reader;
        try {
            reader =
                    new BufferedReader(
                            new FileReader(DATA_FILE_PATH));
            String line = reader.readLine();
            while (line != null) {
                String[] temp = line.split(SPLITTING_CHARACTERS);
                Word Temp = new Word(temp[0], SPLITTING_CHARACTERS + temp[1]);
                database.add(Temp);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Them ham deleteWord.
    public void deleteWord(String word) throws IOException {
        File inFile = new File(DATA_FILE_PATH);
        if (!inFile.isFile()) {
            return;
        }
        File tempFile = new File(inFile.getAbsoluteFile() + ".tmp");
        BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_PATH));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        String line;
        while ((line = br.readLine()) != null) {
            String[] temp = line.split(SPLITTING_CHARACTERS);
            if (!temp[0].equals(word)) {
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();

        if (!inFile.delete()) {
            System.out.println("Couldn't delete file.");
            return;
        }
        if (!tempFile.renameTo(inFile)) {
            System.out.println("Couldn't delete file.");
        }
    }

    //Them ham export.
    public void dictionaryExportToFile(String word, String mean) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        String word_add = word + "<>" + mean;
        try {
            File file = new File(DATA_FILE_PATH);
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

    //Them ham lookup tra ve Word.
    public Word dictionaryLookup(String a) {
        for (Word e : database) {
            if (e.getWord_target().equals(a)) {
                return e;
            }
        }
        return null;
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
                write.write(word.getWord_target() + "<>" + word.getWord_explain() + "\n");

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
                writer.write(word.getWord_target() + "<>" + word.getWord_explain() + "\n");
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

}

}
