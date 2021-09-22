import java.util.Scanner;

public class DictionaryManagement {
    public static void insertFromCommandLine(){
        Scanner sc = new Scanner(System.in);
        Word.word_target = sc.nextLine();
    }
}
