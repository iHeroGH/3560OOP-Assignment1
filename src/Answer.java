/**
 * The Answer class holds parameters and methods for a possible answer to a question
 * 
 * An Answer object is not bound to its question, but exists as its own entity. 
 * With a String for the answer and a boolean denoting whether or not it's correct,
 * we can implement a very basic set of getter-setter commands, as well as some overrides
 * to maintain equality and hashing (for use with a Set or a HashMap or HashSet, allowing for
 * a variety of implementations).
 * 
 * For this implementation, an Answer object is equal to (or has the same hashcode as) another
 * if their answer strings are equal (or have the same hashcode).
 * 
 * @author George Matta
 * @version 1.0
 */
public class Answer {
    
    /**
     * A String denoting the answer's text
     */
    private String answerString;

    /**
     * A Boolean denoting whether or not the answer is correct
     */
    private boolean isCorrect;

    /**
     * A basic constructor for an Answer object.
     * 
     * Creates an Answer based on its text and correctness
     * 
     * @param answerString A String denoting the answer's text
     * @param isCorrect A Boolean denoting whether or not the answer is correct
     */
    public Answer(String answerString, boolean isCorrect){
        this.answerString = answerString;
        this.isCorrect = isCorrect;
    }

    /**
     * A simple getter for the answerString attribute of the Answer object
     * @return A String denoting the answer's text
     */
    public String getAnswerString(){
        return this.answerString;
    }

    /**
     * A simple setter for the answerString attribute of the Answer object
     * @param answerString A String denoting the answer's text
     */
    public void setAnswerString(String answerString){
        this.answerString = answerString;
    }

    /**
     * A simple getter for the isCorrect attribute of the Answer object
     * @return A Boolean denoting whether or not the answer is correct
     */
    public boolean isCorrect(){
        return this.isCorrect;
    }

    /**
     * A simple setter for the isCorrect attribute of the Answer object
     * @param isCorrect A Boolean denoting whether or not the answer is correct
     */
    public void setCorrect(boolean isCorrect){
        this.isCorrect = isCorrect;
    }

    /**
     * Retrieves the answerString's hashcode
     * 
     * @return The answerString's hashcode (retrieved by calling hashcode() on it).
     */
    @Override
    public int hashCode(){
        return this.answerString.hashCode();
    }

    /**
     * Checks if this Answer object is equal to another object
     * 
     * This method checks if the other object is an Answer object, then delegates
     * to the equals(Answer) method
     * 
     * @param other The other object to check equality for
     * @return Whether or not the objects are equal
     */
    @Override
    public boolean equals(Object other){
        if (!(other instanceof Answer)){
            return false;
        }

        return this.equals((Answer) other);
    }

    /**
     * Checks if this Answer object is equal to another Answer object
     * 
     * This method delegates to the equals(String) method to check if the Answer
     * object equals the other's answerString
     * 
     * @param other The other Answer object to check equality for
     * @return Whether or not the objects are equal
     */
    public boolean equals(Answer other){
        return this.equals(other.answerString);
    }

    /**
     * Checks if this Answer object is equal to a String. They are equal if
     * this answerString is equal to the given string
     * 
     * Delegates to the String.equals(String) method
     * 
     * @param answerString The String to compare answerStrings with
     * @return Whether or not the answerStrings are equal
     */
    public boolean equals(String answerString){
        return this.answerString.equals(answerString);
    }

    /**
     * A String representation of the Answer object
     * 
     * @return The Answer object as a String `Answer("answerString", isCorrect)`
     */
    @Override
    public String toString(){
        return "Answer(" + this.answerString + ", " + this.isCorrect + ")";
    }

}
