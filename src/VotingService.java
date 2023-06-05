import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

/**
 * The VotingService class brings together the pieces developed in the project to 
 * allow for a Student(Question->Answer) system.
 * 
 * With a Set of Students and a Set of Questions (which have Sets of answers),
 * we can keep track of various statistics such as which answers are chosen, how many
 * correct and incorrect answers are chosen, etc.
 * 
 * This class provides implementations for those features, as well as a way to view said
 * statistics.
 * 
 * @author George Matta
 * @version 1.0
 */
public class VotingService {
    
    /**
     * The Set of Students
     */
    private Set<Student> studentSet;

    /**
     * The Set of Questions
     */
    private Set<QuestionInterface> questionSet;
    
    /**
     * The number of correct answers chosen
     */
    private int numCorrect;

    /**
     * The number of wrong answers chosen
     */
    private int numWrong;

    /**
     * The statistics int matrix
     * 
     * statistics[i][j] is the number of Students who chose Answer index j of 
     * Question index i
     */
    private int[][] statistics;

    /**
     * A boolean denoting whether or not the Answers have been chosen by the Students
     * This ensures that we do not attempt to display statistics that have not
     * been calculated
     */
    private boolean hasVoted;

    /**
     * A simple constructor for the VotingService object
     * 
     * Delegates to the VotingService(Set, Set) constructor with sets created
     * from the provided arrays
     * 
     * @param studentArray The Array of Students
     * @param questionArray The Array of Questions
     */
    public VotingService(Student[] studentArray, QuestionInterface[] questionArray){
        // Generate HashSets of the given Java Arrays and call the main constructor
        this(
            new HashSet<Student>(Arrays.asList(studentArray)),
            new HashSet<QuestionInterface>(Arrays.asList(questionArray))
        );
    }

    /**
     * The main constructor for the VotingService object
     * 
     * Sets the studentSet and questionSet and initializes the statistics matrix
     * 
     * @param studentSet The Set of Students
     * @param questionSet The Set of Questions
     */
    public VotingService(Set<Student> studentSet, Set<QuestionInterface> questionSet){
        this.studentSet = studentSet;
        this.questionSet = questionSet;

        initializeStatistics();
    }

    /**
     * Initializes the satistics int array depending on how many questions exist
     * and how many answers exist for each question
     */
    private void initializeStatistics(){
        // As many rows as there are questions
        this.statistics = new int[this.questionSet.size()][];

        // As many columns as there are answers to each question
        int i = 0;
        for (QuestionInterface question : this.questionSet){
            this.statistics[i++] = new int[question.getPossibleAnswers().size()];
        }

        // To maintain validation
        this.hasVoted = false;
    }

    /**
     * Runs the entire simulation by choosing Answers for every Question for every
     * Student.
     * 
     * Records the statistics to be printed later.
     */
    public void chooseAnswers(){
        // Initialize the Set of answer indices so we don't recreate it each iteration
        Set<Integer> answerIndices = null;
        // The index of the question we're currently on
        int questionIndex = 0;

        // Loop through each student
        for (Student student : studentSet){
            questionIndex = 0; // We have a new Student, so start at the first question

            // Loop through each question and see what answers the Student responds with
            for (QuestionInterface question : questionSet){
                
                // Get the indices of chosen answers
                answerIndices = student.getAnswerIndices(question);
                // Loop  through each index
                for(int answerIndex : answerIndices) {
                    // Update the main statistics table
                    this.statistics[questionIndex][answerIndex]++;
                    
                    // Update the score tracker
                    if (question.getAnswerAtPosition(answerIndex).isCorrect()){
                        this.numCorrect++;
                    } else {
                        this.numWrong++;
                    }
                }
                // Go to the next question
                questionIndex++;
            }   
        }

        // The voting has been completed
        this.hasVoted = true;
    }

    /**
     * Prints the statistics calculated
     * @throws IllegalStateException If the voting has not been done yet
     *                               (chooseAnswers() wasn't called) 
     */
    public void printStatistics(){

        // If the user tries printing statistics before running the simulation
        if (!hasVoted){
            throw new IllegalStateException(
                "The statistics for this service have not been calculated." +
                " Make sure to run `chooseAnswers() before printing the statistics."
            );
        }

        // Initialize indices for the question and answer so they aren't remade later
        int questionIndex = 0;
        int answerIndex = 0;

        // Loop through each Question in the set
        for (QuestionInterface question : questionSet){
            // Print the question
            System.out.println(question.getQuestionString());
            
            // Loop through each Answer in the Question's Answer set
            answerIndex = 0;
            for (Answer answer : question.getPossibleAnswers()){
                // Send the answer string and how many students chose it
                System.out.print(answer.getAnswerString() + " : ");
                System.out.print(this.statistics[questionIndex][answerIndex]);
                
                // If the answer is correct, mark it accordingly
                if (answer.isCorrect()){
                    System.out.println("**");
                } else {
                    System.out.println();
                }
                
                // Go to the next answer
                answerIndex++;
            }
            
            // Separate the questions and go to the next question
            System.out.println();
            questionIndex++;
        }
        
        // Final statistics of the total number of correct and incorrect answers chosen
        System.out.println("Total Correct: " + this.numCorrect);
        System.out.println("Total Incorrect: " + this.numWrong);
    }
}
