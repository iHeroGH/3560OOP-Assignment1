import java.util.Set;

/**
 * The QuestionInterface is used to implement Question objects.
 * 
 * Having it as an interface allows for easy polymorphism with different question types
 * (single choice/multiple choice are implemented in this project, but free-response questions
 * could probably be implemented as well).
 * 
 * There are a pleothera of getters and setters to allow for easy access and manipulation of 
 * the Answers set (since sets don't have random access but have duplicate entry protection)
 * 
 * @author George Matta
 * @version 1.0
 */
public interface QuestionInterface {
    
    /**
     * A simple getter method for the question string of the Question
     * @return The questionString of the Question object
     */
    public String getQuestionString();
    /**
     * A simple setter method for the questrionString attribute
     * @param questionString The questionString of the Question object
     */
    public void setQuestionString(String questionString);

    /**
     * A simple getter method for the possible answers of the Question
     * @return The possibleAnswers of the Question object
     */
    public Set<Answer> getPossibleAnswers();
    /**
     * Gets a single Answer object given an index
     * 
     * Since sets don't have random access, we just loop through it's array and
     * increment a temporary index
     * 
     * @param answerIndex The index to get the answer for
     * @return The Answer object found
     */
    public Answer getAnswerAtPosition(int answerIndex);
    /**
     * Calls the getAnswerAtPosition for all the indices provided and returns
     * a set of the Answers found
     * 
     * @param answerIndices A set of Integers of the indices to find
     * @return A set of Answers found
     */
    public Set<Answer> getAnswersAtPositions(Set<Integer> answerIndices);

    /**
     * Adds a possible answer to the possible answer set
     * 
     * @param answerString The String of the answer
     * @param isCorrect Whether or not the answer is correct
     */
    public void addPossibleAnswer(String answerString, boolean isCorrect);
    /**
     * Delegates to addPossibleAnswer(String, boolean)
     * 
     * Implements a default value for whether or not an answer is correct
     * 
     * @param answerString The String of the answer
     */
    public void addPossibleAnswer(String answerString);
    /**
     * Adds a possible answer given a straight Answer object (rather than its attributes)
     * @param answer The Answer object to add
     */
    public void addPossibleAnswer(Answer answer);
    /**
     * Delegates to addPossibleAnswer(String, true) 
     * 
     * Allows us to add a correct answer without passing a boolean value to addPossibleAnswer()
     * 
     * @param answerString The String of the answer
     */
    public void addCorrectAnswer(String answerString);

    /**
     * Removes an answer from the possibleAnswers set given its answer string
     * @param answerString The String of the answer
     */
    public void removePossibleAnswer(String answerString);
    /**
     * Removes an answer from the possibleAnswers set given an Answer object
     * @param answer The Answer object itself
     */
    public void removePossibleAnswer(Answer answer);

    /**
     * A simple getter for whether or not the question has a correct answer
     * @return Whether or not a correct answer has been added
     */
    public boolean hasCorrectAnswer();
    /**
     * A simple getter for whether or not the question is multiple choice
     * @return Whether or not the question allows for multiple choices
     */
    public boolean getIsMultipleChoice();
}
