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
}
