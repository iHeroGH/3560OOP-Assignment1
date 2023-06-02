import java.util.Set;
import java.util.HashSet;

public class Student {
    
    private String studentID;
    
    private static Set<String> usedIDs = new HashSet<String>();
    private static final int ID_LENGTH = 7;
    
    public Student(){
        this(findValidID());
    }

    public Student(String studentID){
        this.setID(studentID);
    }

    public int getAnswerIndex(Set<Answer> possibleAnswers){
        double randomNum = Math.random()*(possibleAnswers.size()-1) + 1;
        int answerIndex = (int) Math.floor(randomNum);

        return answerIndex;
    }

    public int getAnswerIndex(Question question){
        return getAnswerIndex(question.getPossibleAnswers());
    }

    public Answer chooseAnswer(Question question){

        Set<Answer> possibleAnswers = question.getPossibleAnswers();
        
        int answerIndex = getAnswerIndex(possibleAnswers);

        int i = 0;
        for (Answer ans : possibleAnswers){
            if (i == answerIndex){
                return ans;
            }
            i += 1;
        }

        return new Answer("", false);
    }

    public String getID(){
        return this.studentID;
    }

    public void setID(String generatedID){
        if (usedIDs.contains(studentID)){
            throw new IllegalArgumentException("The provided student ID is already in use.");
        }

        usedIDs.add(generatedID);
        this.studentID = generatedID;
    }

    private static String findValidID(){
        String generatedID = generateRandomID();

        while (usedIDs.contains(generatedID)){
            generatedID = generateRandomID();
        }
        
        return generatedID;
    }

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
