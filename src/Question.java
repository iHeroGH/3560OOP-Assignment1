import java.util.Set;
import java.util.HashSet;

public class Question {
    
    private String questionString;

    private Set<Answer> answersList;

    public Question(String questionString){
        this.questionString = questionString;

        answersList = new HashSet<Answer>();
    }

    public String getQuestionString(){
        return this.questionString;
    }
    public void setQuestionString(String questionString){
        this.questionString = questionString;
    }

    public void addPossibleAnswer(String answerString, boolean isCorrect){
        if  (!answersList.add(new Answer(answerString, isCorrect))){
            throw new IllegalArgumentException("That answer is already a possible answer.");
        }
    }

    public void addCorrectAnswer(String possibleAnswer){
        for(Answer ans : answersList){
            if (ans.equals(possibleAnswer)){
                ans.setCorrect(true);
                return;
            }
        }

        answersList.add(new Answer(possibleAnswer, true));
    }

}

