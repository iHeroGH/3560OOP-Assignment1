import java.util.Set;
import java.util.HashSet;

public class Question implements QuestionInterface {

    protected String questionString;
    
    protected Set<Answer> answerList;

    public Question(String questionString){
        this.questionString = questionString;
        answerList = new HashSet<Answer>();
    }
    
    @Override
    public String getQuestionString() {
        return this.questionString;
    }

    @Override
    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    @Override
    public void addPossibleAnswer(String answerString, boolean isCorrect) {
        if (isCorrect && this.hasCorrectAnswer()){
            throw new UnsupportedOperationException("Can't have multiple correct answers for this question.");
        }
        
        if  (!answerList.add(new Answer(answerString, isCorrect))){
            throw new IllegalArgumentException("That answer is already a possible answer.");
        }
    }

    @Override
    public void addPossibleAnswer(Answer answer) {
        addPossibleAnswer(answer.getAnswerString(), answer.isCorrect());
    }

    @Override
    public void addCorrectAnswer(String answerString) {
        addPossibleAnswer(answerString, true);
    }

    @Override
    public void removePossibleAnswer(String answerString) {
        removePossibleAnswer(new Answer(answerString, false));
    }

    @Override
    public void removePossibleAnswer(Answer answer) {
        this.answerList.remove(answer);
    }

    @Override
    public boolean hasCorrectAnswer(){
        for(Answer ans : this.answerList){
            if (ans.isCorrect()){
                return true;
            }
        }

        return false;
    }

}