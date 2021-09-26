import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words;
    Dictionary() {
        words = new ArrayList<Word>(0);
    }


    public void addWordToDictionary(Word word) {
        boolean check = this.words.contains(word);
        if (!check) {
            this.words.add(word);
            System.out.println("Dictonary add " + word.getWord_target());
        } else {
            System.out.println("Dictionary have word " + word.getWord_target());
        }

    }

    public boolean containsWordInDictionary(Word word) {
        boolean result = this.words.contains(word);
        return result;
    }

    public void printWordIndex(int index) {
        Word word = this.words.get(index);
        word.showWord();
    }

    public void removeWordInDictionary(Word word) {
        int index = this.words.indexOf(word);
        this.words.remove(index);
    }

    public void printDictionary() {
        System.out.println("                        English               Vietnamese");
        for (int i = 0; i < this.words.size(); i++) {
            System.out.printf("%-5d %s \n", i, this.words.get(i));
        }
    }

}
