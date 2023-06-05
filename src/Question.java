import java.util.Set;
import java.util.HashSet;

public class Question implements QuestionInterface {

    protected String questionString;
    
    protected boolean isMultipleChoice;

    protected Set<Answer> answerList;

    public Question(String questionString){
        this.questionString = questionString;
        this.isMultipleChoice = false;
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
    public Set<Answer> getPossibleAnswers(){
        return this.answerList;
    }

    @Override
    public void addPossibleAnswer(String answerString, boolean isCorrect) {
        if (isCorrect && this.hasCorrectAnswer()){
            throw new UnsupportedOperationException("Question already has a correct answer.");
        }
        
        if  (!answerList.add(new Answer(answerString, isCorrect))){
            throw new IllegalArgumentException("Answer is already a possible answer.");
        }
    }

    @Override
    public void addPossibleAnswer(String answerString) {
        addPossibleAnswer(answerString, false);
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
        for (Answer ans : this.answerList){
            if (ans.isCorrect()){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean getIsMultipleChoice(){
        return this.isMultipleChoice;
    }

    public Answer getAnswerAtPosition(int answerIndex){

        int i = 0;
        for (Answer ans : this.answerList){
            if (i == answerIndex){
                return ans;
            }
            i += 1;
        }

        return new Answer("", false);
    }

    public Set<Answer> getAnswersAtPositions(Set<Integer> answerIndices){
        Set<Answer> answersList = new HashSet<Answer>();
        
        for(int answerIndex : answerIndices){
            Answer ansAtPos = getAnswerAtPosition(answerIndex);
            if (!ansAtPos.equals(""))
                answersList.add(ansAtPos);
        }

        return answersList;

    }

}