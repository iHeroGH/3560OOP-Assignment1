import java.util.Set;
import java.util.HashSet;

/**
 * The SimulationDriver class demos basic functionality of the Question, Answer, 
 * Student, and VotingService classes
 * 
 * Implementing functionality for creating a random set of Students, some questions, 
 * and demoing the VotingService process for choosing answers and printing statistics.
 * 
 * @author George Matta
 * @version 1.0
 */
public class SimulationDriver {

    /**
     * Demos typical usage of the VotingService.
     * 
     * Generates a random set of Students, generates some Questions, and lets
     * all the Students answer each of the Questions, printing helpful statistics
     * at the end.
     * 
     * @param args The basic String[] args for any main method in Java
     */
    public static void main(String[] args){
        // Generate Students and Questions
        Set<Student> studentSet = generateStudents(10);
        Set<QuestionInterface> questionSet = generateQuestions();

        // Run the Voting Service and prints statistics
        VotingService vs = new VotingService(studentSet, questionSet);
        vs.chooseAnswers();
        vs.printStatistics();

        System.out.println("\n----------------- Re-Voting -----------------\n");
        vs.chooseAnswers();
        vs.printStatistics();

        System.out.println("\n----------------- Re-Voting -----------------\n");
        vs.chooseAnswers();
        vs.printStatistics();

        System.out.println("\n----------------- Re-Voting -----------------\n");
        vs.chooseAnswers();
        vs.printStatistics();
    }

    /**
     * A statis method to generate a given number of Students with random IDs
     * 
     * @param numStudents The number of Students to generate
     * @return The Set of Students
     * @throws IllegalArgumentException If the number of Students is 0 or less
     */
    private static Set<Student> generateStudents(int numStudents){
        
        // If numStudents is not positive, we can't generate any students
        if (numStudents <= 0){
            throw new IllegalArgumentException("numStudents must be a positive integer");
        }

        Set<Student> studentSet = new HashSet<Student>();
        
        // Add a random student to the student set
        for(int i = 0; i < numStudents; i++){
            studentSet.add(new Student());
        }
        
        // Return the student set
        return studentSet;
    }

    /**
     * A static method to generate manually created example Questions 
     * (two single and one multi-choice)
     * 
     * @return The Set of Questions
     */
    private static Set<QuestionInterface> generateQuestions(){
        // Demos the addCorrectAnswer function
        QuestionInterface q1 = new Question("What is 1 + 1?");
        q1.addCorrectAnswer("2");
        q1.addPossibleAnswer("1", false);
        q1.addPossibleAnswer("3", false);

        // Demos having many options, none use addCorrectAnswer but use
        // addPossibleAnswer
        QuestionInterface q2 = new Question("What is 2 + 2?");
        q2.addPossibleAnswer("5");
        q2.addPossibleAnswer("4", true);
        q2.addPossibleAnswer("3", false);
        q2.addPossibleAnswer("55", false);
        q2.addPossibleAnswer("15", false);
        
        // Demos the MultipleChoiceQuestion constructor
        QuestionInterface q3 = new MultipleChoiceQuestion("What is a multiple of 5?");
        q3.addCorrectAnswer("10");
        q3.addCorrectAnswer("20");
        q3.addCorrectAnswer("30");
        q3.addPossibleAnswer("24");
        q3.addPossibleAnswer("43", false);

        QuestionInterface q4 = new MultipleChoiceQuestion("Which are CalState Campuses?");
        q4.addCorrectAnswer("CalPoly Pomona");
        q4.addCorrectAnswer("CalPoly Slo");
        q4.addCorrectAnswer("CalState Fullterton");
        q4.addPossibleAnswer("USC");
        q4.addPossibleAnswer("CalState Chicago");
        q4.addPossibleAnswer("CP Pomona");
        
        // Adds the created questions to a set and returns it
        Set<QuestionInterface> questionSet = new HashSet<QuestionInterface>();
        questionSet.add(q1);
        questionSet.add(q2);
        questionSet.add(q3);
        questionSet.add(q4);
        return questionSet;
    }

}