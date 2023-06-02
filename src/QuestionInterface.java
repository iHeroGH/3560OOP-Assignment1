import java.util.Set;

public interface QuestionInterface {
    
    public String getQuestionString();
    public void setQuestionString(String questionString);

    public Set<Answer> getPossibleAnswers();

    public void addPossibleAnswer(String answerString, boolean isCorrect);
    public void addPossibleAnswer(Answer answer);
    public void addCorrectAnswer(String answerString);

    public void removePossibleAnswer(String answerString);
    public void removePossibleAnswer(Answer answer);

    public boolean hasCorrectAnswer();
    public boolean getIsMultipleChoice();
}
