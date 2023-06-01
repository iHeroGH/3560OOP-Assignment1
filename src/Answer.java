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

    public boolean equals(Answer other){
        return this.answerString == other.answerString;
    }

    public boolean equals(String answerString){
        return this.answerString == answerString;
    }

}
