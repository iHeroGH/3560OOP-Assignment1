public class Answer {
    
    private String answerString;
    private boolean isCorrect;

    public Answer(String answerString, boolean isCorrect){
        this.answerString = answerString;
        this.isCorrect = isCorrect;
    }

    public void setCorrect(boolean isCorrect){
        this.isCorrect = isCorrect;
    }

    public boolean isCorrect(){
        return this.isCorrect;
    }

    public boolean equals(Answer other){
        return this.answerString == other.answerString;
    }

    public boolean equals(String answerString){
        return this.answerString == answerString;
    }

}