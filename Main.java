public class Main {

    public static void main(String[] args) {
	// write your code here
        Word word1 = new Word("hello", "xin chao");
        Word word2 = new Word("bye", "tam biet");
        Word word3 = new Word("school", "truong hoc");
        DictionaryCommandLine dictionary = new DictionaryCommandLine();
        dictionary.insertFromCommandLine(word1);
        dictionary.insertFromCommandLine(word1);
        dictionary.insertFromCommandLine(word2);
        dictionary.insertFromCommandLine(word3);
        dictionary.showAllWord();

    }
}
