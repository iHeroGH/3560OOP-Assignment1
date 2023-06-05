/**
 * The MultipleChoiceQuestion extends the Question class and simply removes the
 * check for if multiple answers are being added to a question
 * 
 * Featuring a questionString, an isMultipleChoice attribute, and an answersSet,
 * we are able to create a modifiable Question object (that can later be inherited
 * for multiple-choice questions)
 * 
 * @author George Matta
 * @version 1.0
 */
public class MultipleChoiceQuestion extends Question{

    /**
     * A simple constructor for a MultipleChoieQuestion object, only takes a questionString
     * 
     * Sets isMultipleChoice to true
     * 
     * Simply calls the super constructor
     * 
     * @param questionString The String of the question text
     */
    public MultipleChoiceQuestion(String questionString){
        super(questionString);
        this.isMultipleChoice = true;
    }

    /**
     * Adds a possible answer to the possible answer set
     * 
     * @param answerString The String of the answer
     * @param isCorrect Whether or not the answer is correct
     * @throws IllegalArgumentException If the answer is already a possible answer
     */
    @Override
    public void addPossibleAnswer(String answerString, boolean isCorrect) {
        if  (!this.answerSet.add(new Answer(answerString, isCorrect))){
            throw new IllegalArgumentException("That answer is already a possible answer.");
        }
    }
}