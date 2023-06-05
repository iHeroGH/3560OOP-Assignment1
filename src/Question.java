import java.util.Set;
import java.util.HashSet;

/**
 * The simple Question object implements the QuestionInterface (and justly has
 * Override methods for all the necessary methods)
 * 
 * Featuring a questionString, an isMultipleChoice attribute, and an answersSet,
 * we are able to create a modifiable Question object (that can later be inherited
 * for multiple-choice questions)
 * 
 * @author George Matta
 * @version 1.0
 */
public class Question implements QuestionInterface {

    /**
     * The String of the question text
     */
    protected String questionString;
    
    /**
     * Whether or not the question accepts multiple choices
     */
    protected boolean isMultipleChoice;

    /**
     * The Set of possible answers for the question
     */
    protected Set<Answer> answerSet;

    /**
     * A simple constructor for a Question object, only takes a questionString
     * 
     * Sets isMultipleChoice to false since multiple choice questions have their
     * own class
     * 
     * Sets the answerSet Set to an empty HashSet
     * 
     * @param questionString The String of the question text
     */
    public Question(String questionString){
        this.questionString = questionString;
        this.isMultipleChoice = false;
        answerSet = new HashSet<Answer>();
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public String getQuestionString() {
        return this.questionString;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void setQuestionString(String questionString) {
        this.questionString = questionString;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Set<Answer> getPossibleAnswers(){
        return this.answerSet;
    }

    /**
     * {@inheritDoc}}
     */
    @Override    
    public Answer getAnswerAtPosition(int answerIndex){

        // Loop through the answers until we find a matching index
        int i = 0;
        for (Answer ans : this.answerSet){
            if (i == answerIndex){
                return ans;
            }
            i += 1;
        }

        // If we don't find an answer, return an empty, false, answer
        return new Answer("", false);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public Set<Answer> getAnswersAtPositions(Set<Integer> answerIndices){
        Set<Answer> answersSet = new HashSet<Answer>();
        
        // Call getAnswerAtPosition on every position given
        for(int answerIndex : answerIndices){
            Answer ansAtPos = getAnswerAtPosition(answerIndex);
            // If an answer was found, add it to the set
            if (!ansAtPos.equals(""))
                answersSet.add(ansAtPos);
        }
        
        // Return the set
        return answersSet;
    }

    /**
     * {@inheritDoc}}
     * 
     * @throws UnsupportedOperationException If the Question already has a correct answer
     * @throws IllegalArgumentException If the answer provided is already an answer
     */
    @Override
    public void addPossibleAnswer(String answerString, boolean isCorrect) {
        if (isCorrect && this.hasCorrectAnswer()){
            throw new UnsupportedOperationException("Question already has a correct answer.");
        }
        
        // Set.add returns false if the item was already in the set
        if  (!answerSet.add(new Answer(answerString, isCorrect))){
            throw new IllegalArgumentException("Answer is already a possible answer.");
        }
    }

    /**
     * {@inheritDoc}}
     * The default value for correctness is false
     */
    @Override
    public void addPossibleAnswer(String answerString) {
        addPossibleAnswer(answerString, false);
    }
    
    /**
     * {@inheritDoc}}
     */
    @Override
    public void addPossibleAnswer(Answer answer) {
        addPossibleAnswer(answer.getAnswerString(), answer.isCorrect());
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void addCorrectAnswer(String answerString) {
        addPossibleAnswer(answerString, true);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void removePossibleAnswer(String answerString) {
        removePossibleAnswer(new Answer(answerString, false));
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public void removePossibleAnswer(Answer answer) {
        this.answerSet.remove(answer);
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean hasCorrectAnswer(){
        // Loop through all the answers until we find any that are correct
        for (Answer ans : this.answerSet){
            if (ans.isCorrect()){
                return true;
            }
        }

        // If none are found, there are no correct answers
        return false;
    }

    /**
     * {@inheritDoc}}
     */
    @Override
    public boolean getIsMultipleChoice(){
        return this.isMultipleChoice;
    }
}