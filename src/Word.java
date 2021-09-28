public class Word {
    private String word_target;
    private String word_explain;
    public Word() {
        this.word_target = "";
        this.word_explain = "";
    }

    public Word(String word_target, String word_explain) {
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public Word(Word word) {
        this.word_target = word.getWord_target();
        this.word_explain = word.getWord_explain();
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }
    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }
    public String getWord_target() {
        return word_target;
    }
    public String getWord_explain() {
        return word_explain;
    }

    public void showWord() {
        System.out.printf("%s %s \n", word_target, word_explain);
    }

    public boolean equals(Word ortherWord) {
        if (word_target.equals(ortherWord.getWord_target()) && word_explain.equals(ortherWord.getWord_explain())) {
            return true;
        }
        return false;
    }

    public String toString() {
        int lengthTarget = word_target.length();
        int lengthExplain = word_explain.length();
        String res = "";
        for(int i = 0; i < 25 - lengthTarget; i++)  res += " ";
        res += this.word_target;
        for(int i = 0; i< 25 - lengthExplain; i++)  res += " ";
        res += this.word_explain;
        return res;
    }

    public void swap (Word ortherWord) {
        Word temp = new Word(ortherWord);
        ortherWord.setWord_explain(word_explain);
        ortherWord.setWord_target(word_target);

        word_target = temp.getWord_target();
        word_explain = temp.getWord_explain();
    }

    /**
     *
     * @param ortherWord
     * @return int > 0 if word > ortherWord
     */
    public int compareTo(Word ortherWord) {
        if (word_target.compareTo(ortherWord.getWord_target()) > 0) {
            return 1;
        } else {
            // if same word target
            if (word_target.compareTo(ortherWord.getWord_target()) == 0) {
                if (word_explain.compareTo(ortherWord.getWord_explain()) > 0) {
                    return 1;
                } else {
                    if (word_explain.compareTo(ortherWord.getWord_explain()) == 0) {
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
