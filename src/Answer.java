public class Answer {
    
    private String answerString;
    private boolean isCorrect;

    public Answer(String answerString, boolean isCorrect){
        this.answerString = answerString;
        this.isCorrect = isCorrect;
    }

    public String getAnswerString(){
        return this.answerString;
    }

    public void setAnswerString(String answerString){
        this.answerString = answerString;
    }

    public boolean isCorrect(){
        return this.isCorrect;
    }

    public void setCorrect(boolean isCorrect){
        this.isCorrect = isCorrect;
    }

    @Override
    public int hashCode(){
        return this.answerString.hashCode();
    }

    @Override
    public boolean equals(Object other){
        if (!(other instanceof Answer)){
            return false;
        }

        return this.equals((Answer) other);
    }

    public boolean equals(Answer other){
        return this.equals(other.answerString);
    }

    public boolean equals(String answerString){
        return this.answerString.equals(answerString);
    }

    public String toString(){
        return "Answer(" + this.answerString + ", " + this.isCorrect + ")";
    }

}
