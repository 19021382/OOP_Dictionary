import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> words;

    /**
     * construction no param
     */
    Dictionary() {
        words = new ArrayList<Word>(0);
    }

    public int getLengthDictionary() {
        return words.size();
    }

    public Word getWordIndex(int index) {
        return words.get(index);
    }
    public void getInfoWord(int index) {
        words.get(index).showWord();
    }

    public void addWordToDictionary(Word word) {
        boolean check;
        if (indexOf(word) == -1) {
            check = false;
        } else {
            check = true;
        }
        if (!check) {
            this.words.add(word);
            System.out.println("Dictonary add " + word.getWordTarget());
        } else {
            System.out.println("Dictionary have word " + word.getWordTarget());
        }
    }

    public boolean containsWordInDictionary(Word word) {
        if (indexOf(word) == -1)    return false;
        return true;
    }

    public void printWordIndex(int index) {
        Word word = this.words.get(index);
        word.showWord();
    }

    public void removeWordInDictionary(Word word) {
        //int index = this.words.indexOf(word);
        int index = indexOf(word);
        this.words.remove(index);
    }

    public void printDictionary() {
        System.out.println("                        English               Vietnamese");
        for (int i = 0; i < this.words.size(); i++) {
            System.out.printf("%-5d %s \n", i, this.words.get(i));
        }
    }

    public void sortDictionary() {
        for(int i = 0; i < words.size() - 1; i++) {
            for(int j = i +1; j < words.size(); j++) {
                if (words.get(i).compareTo(words.get(j)) > 0) {
                    words.get(i).swap(words.get(j));
                }
            }
        }
    }

    /**
     * use the binaryFinding and it is reused for many method
     * @param word
     * @return indexWord
     */
    public int indexOf(Word word) {
        int begin = 0;
        int end = words.size()-1;
        int mid = (begin + end)/2;
        while (begin <= end) {
            if (word.equals(words.get(mid)))    return mid;
            if (word.compareTo(words.get(mid)) < 0) {
                end = mid -1;
            }
            if (word.compareTo(words.get(mid)) > 0) {
                begin = mid + 1;
            }
            mid = (begin + end)/2;
        }
        return -1;
    }
}
