import java.util.Set;
import java.util.HashSet;

public class Question {
    
    private String questionString;

    private boolean isMultipleChoice;
    
    private Set<Answer> answersList;

    public Question(String questionString, boolean isMultipleChoice){
        this.questionString = questionString;
        this.isMultipleChoice = isMultipleChoice;
        answersList = new HashSet<Answer>();
    }

    public String getQuestionString(){
        return this.questionString;
    }

    public void setQuestionString(String questionString){
        this.questionString = questionString;
    }

    public void addPossibleAnswer(String answerString, boolean isCorrect){
        if (isCorrect && !this.isMultipleChoice && this.hasCorrectAnswer()){
            throw new UnsupportedOperationException("Can't have multiple correct answers for this question.");
        }

        if  (!answersList.add(new Answer(answerString, isCorrect))){
            throw new IllegalArgumentException("That answer is already a possible answer.");
        }
    }

    public void removePossibleAnswer(String answerString){
        for(Answer ans : answersList){
            if (ans.equals(answerString)){
                this.answersList.remove(ans);
                return;
            }
        }
    }

    public void removePossibleAnswer(Answer possibleAnswer){
        this.answersList.remove(possibleAnswer);
    }

    public void addCorrectAnswer(String possibleAnswer){
        if (!this.isMultipleChoice && this.hasCorrectAnswer()){
            throw new UnsupportedOperationException("Can't have multiple correct answers for this question.");
        }

        for(Answer ans : answersList){
            if (ans.equals(possibleAnswer)){
                ans.setCorrect(true);
                return;
            }
        }

        answersList.add(new Answer(possibleAnswer, true));
    }

    public boolean hasCorrectAnswer(){
        for(Answer ans : answersList){
            if (ans.isCorrect()){
                return true;
            }
        }

        return false;
    }

}

