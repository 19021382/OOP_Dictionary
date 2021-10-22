import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DictionaryCommandLine Dictionary = new DictionaryCommandLine();
        final String pathFile = "dictionaries.txt";

        boolean isRunning = true;
        String command;

        Scanner sc = new Scanner(System.in);
        System.out.println("THIS IS COMMANDLINE DICTIONARY");

        while (isRunning) {
            command = sc.next();
            if (command.equals("AddWord")) {
                Dictionary.insertFromCommandLine();
            }

            if (command.equals("ShowAllWords")) {
                Dictionary.showAllWord();
            }

            if(command.equals("Print")) {
                Dictionary.printDictionary();
            }

            if (command.equals("RemoveWord")) {
                Dictionary.removeFromCommandLine();
            }

            if (command.equals("InsertFromFile")) {
                Dictionary.insertFromFile();
            }

            if (command.equals("ExportToFile")) {
                Dictionary.writeToFile();
            }

            if(command.equals("Sort")) {
                Dictionary.insertionSortDictionary();
            }

            if(command.equals("Lookup")) {
                Dictionary.dictionaryLookup();
            }

            if(command.equals("Search")) {
                Dictionary.dictionarySearcher();
            }

            if(command.equals("Basic")) {
                Dictionary.dictionaryBasic();
            }

            if(command.equals("Advanced")) {
                Dictionary.dictionaryAdvanced();
            }

            if(command.equals("Quit")) {
                System.out.println("Good Bye!");
                isRunning = false;
            }
        }
    }
}