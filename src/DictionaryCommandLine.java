
import java.util.Scanner;

public class DictionaryCommandLine extends DictionaryManagement{
    Scanner sc = new Scanner(System.in);

    DictionaryCommandLine() {
        super();
    }

    public void dictionaryBasic() {
        insertFromCommandLine();
        showAllWord();
    }

    public void showAllWord() {
        for (int i = 0; i < dictionary.getLengthDictionary(); i++) {
            System.out.printf("%-5d ", i);
            dictionary.getInfoWord(i);
        }
    }

    public void dictionaryAdvanced() {
        insertFromFile();
        showAllWord();
        dictionaryLookup();
    }


}
