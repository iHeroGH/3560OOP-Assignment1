import java.util.Set;
import java.util.HashSet;

/**
 * The Student class implements various methods for unique ID storage, manipulation,
 * and validation. 
 * 
 * This class also implements a way to get an Answer from a Student
 * given a Question
 * 
 * @author George Matta
 * @version 1.0
 */
public class Student {
    
    /**
     * The unique ID of the Student
     */
    private String studentID;
    
    /**
     * A static Set of the IDs already in the system
     * 
     * We use a HashSet because of its constant-time access (since we're only
     * checking if an ID is contained in the set) and its unique elements
     */
    private static Set<String> usedIDs = new HashSet<String>();

    /**
     * A final static field of the length of an ID.
     * This implementation uses {@value #ID_LENGTH}
     */
    private static final int ID_LENGTH = 7;
    
    /**
     * The default constructor for a Student
     * 
     * Delegates to the Student(String) constructor using a random, valid, ID
     */
    public Student(){
        this(findValidID());
    }

    /**
     * Creates a Student given a Student ID. The ID is validated in the setID(String)
     * method
     * 
     * @param studentID The unique ID of the Student
     */
    public Student(String studentID){
        this.setID(studentID);
    }

    /**
     * Retrieves a set of indices for the answers of a question
     * 
     * We calculate a set because of the possibility of the student selecting
     * multiple answers
     * 
     * @param question The Question the Student is being asked
     * @return A set of indices for the answers of a question
     */
    public Set<Integer> getAnswerIndices(QuestionInterface question){
        Set<Integer> answerIndices = new HashSet<Integer>();
        Set<Answer> possibleAnswers = question.getPossibleAnswers();

         
        double randomNum = Math.random()*(possibleAnswers.size()-1) + 1;
        int answerIndex = (int) Math.floor(randomNum);
        
        int answerCount = 1;
        if (question.getIsMultipleChoice()){
            answerCount = answerIndex;
        }

        for(int i = 0; i < answerCount; i++){
            randomNum = Math.random()*(possibleAnswers.size()-1) + 1;
            answerIndex = (int) Math.floor(randomNum);

            answerIndices.add(answerIndex);
        }

        return answerIndices;
    }

    /**
     * Gets a set of answer indices (getAnswerIndices(question)) then returns an
     * equivalent set of Answers 
     * @param question The Question the Student is being asked
     * @return A set of Answers chosen by the Student
     */
    public Set<Answer> chooseAnswers(QuestionInterface question){
        
        Set<Integer> answerIndices = getAnswerIndices(question);

        return question.getAnswersAtPositions(answerIndices);
    }

    /**
     * A simple getter method for the student's unique ID
     * @return The Student's unique ID
     */
    public String getID(){
        return this.studentID;
    }

    /**
     * A simple setter method for the student's unique ID
     * 
     * This setter method makes sure the ID is not already in the usedIDs set
     * 
     * @param generatedID A randomly or manually selected ID to cross-reference
     * @throws IllegalArgumentException If the ID is already in use
     */
    public void setID(String generatedID){
        if (usedIDs.contains(studentID)){
            throw new IllegalArgumentException("The provided student ID is already in use.");
        }

        usedIDs.add(generatedID);
        this.studentID = generatedID;
    }

    /**
     * A static method to find a valid ID.
     * 
     * This method repeatedly, randomly, generates an ID until it finds one not
     * in use
     * 
     * @return The generated user ID (guaranteed to be unique)
     */
    private static String findValidID(){
        String generatedID = generateRandomID();

        while (usedIDs.contains(generatedID)){
            generatedID = generateRandomID();
        }
        
        return generatedID;
    }

    /**
     * A static method to generate a random ID
     * 
     * This method generates an alphanumeric, case-sensitive, `ID_LENGTH` character
     * long ID.
     * 
     * @return The generated user ID (not guaranteed to be unique)
     */
    private static String generateRandomID(){
        String possibleCharacters = "0123456789"
                                    +"ABCDEFGHIJKLMNOPQRSTUVWXYZ" 
                                    + "abcdefghijklmnopqrstuvwxyz";
        
        String generatedID = "";
        for (int i = 0; i < ID_LENGTH; i++){
            double randomNum = Math.random()*(possibleCharacters.length()-1) + 1;
            int characterIndex = (int) Math.floor(randomNum);
            
            generatedID += possibleCharacters.substring(characterIndex, characterIndex+1);
        }
        
        return generatedID;
    }

}
