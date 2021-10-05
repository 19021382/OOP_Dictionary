import java.util.Locale;

public class Word {
    private String word_target;
    private String word_explain;
    public Word() {
        this.word_target = "";
        this.word_explain = "";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target.toLowerCase();
        this.word_explain = word_explain.toLowerCase();
    }

    public Word(Word word) {
        this.word_target = word.getWordTarget();
        this.word_explain = word.getWordTarget();
    }

    public void setWordTarget(String word_target) {
        this.word_target = word_target;
    }
    public void setWordExplain(String word_explain) {
        this.word_explain = word_explain;
    }
    public String getWordTarget() {
        return word_target;
    }
    public String getWordExplain() {
        return word_explain;
    }

    public void showWord() {
        System.out.printf("%-15s %-15s \n", word_target, word_explain);
    }

    public boolean equals(Word ortherWord) {
        if (word_target.equals(ortherWord.getWordTarget()) && word_explain.equals(ortherWord.getWordExplain())) {
            return true;
        }
        return false;
    }

    public String toString() {
        int lengthTarget = word_target.length();
        int lengthExplain = word_explain.length();
        String res = "      ";
        res += this.word_target;
        for(int i = 0; i < 20 - lengthTarget; i++)  res += " ";
        res += this.word_explain;
        for(int i = 0; i< 20 - lengthExplain; i++)  res += " ";
        return res;
    }

    public void swap (Word ortherWord) {
        Word temp = new Word(ortherWord);
        ortherWord.setWordExplain(word_explain);
        ortherWord.setWordTarget(word_target);

        word_target = temp.getWordTarget();
        word_explain = temp.getWordExplain();
    }

    /**
     *
     * @param ortherWord
     * @return int > 0 if word > ortherWord
     */
    public int compareTo(Word ortherWord) {
        if (word_target.compareTo(ortherWord.getWordTarget()) > 0) {
            return 1;
        } else {
            // if same word target
            if (word_target.compareTo(ortherWord.getWordTarget()) == 0) {
                if (word_explain.compareTo(ortherWord.getWordExplain()) > 0) {
                    return 1;
                } else {
                    if (word_explain.compareTo(ortherWord.getWordExplain()) == 0) {
                        return 0;
                    } else {
                        return -1;
                    }
                }
            } else {
                return -1;
            }
        }
    }
}
