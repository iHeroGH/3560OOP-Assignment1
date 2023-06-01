public interface QuestionInterface {
    
    public String getQuestionString();
    public void setQuestionString(String questionString);

    public void addPossibleAnswer(String answerString, boolean isCorrect);
    public void addPossibleAnswer(Answer answer);
    public void addCorrectAnswer(String answerString);

    public void removePossibleAnswer(String answerString);
    public void removePossibleAnswer(Answer answer);

    public boolean hasCorrectAnswer();
}
