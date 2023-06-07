import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
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
 * @version 1.1
 */
public class VotingService {
    
    /**
     * A Map of Student to their selected answers matrix 
     * 
     * matrix[i] would be the selections set made for Question index i
     */
    private Map<Student, Set<Integer>[]> studentAnswersMap;

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
        this.questionSet = questionSet;
        
        initializeStudentAnswersMap(studentSet);
        initializeStatistics();
    }

    /**
     * Intializes the Student->Answers map
     * 
     * studentAnswersMap[student][i] would be the Set of Integer indexes of the answers
     * of Question index i
     * 
     * @param studentSet The set of all Students
     */
    @SuppressWarnings("unchecked")
    private void initializeStudentAnswersMap(Set<Student> studentSet){
        this.studentAnswersMap = new HashMap<Student, Set<Integer>[]>();

        Set<Integer>[] answersSets = new HashSet[questionSet.size()]; 

        // For each Student, set their answers set
        for(Student student : studentSet){
            
            // As many answer sets as there are questions
            for(int i = 0; i < questionSet.size(); i++){
                answersSets[i] = new HashSet<Integer>(); 
            }

            this.studentAnswersMap.put(student, answersSets);
            
            // Prepare for the next iteration
            answersSets = new HashSet[questionSet.size()];
        }
    }

    /**
     * Initializes the statistics int array depending on how many questions exist
     * and how many answers exist for each question
     */
    private void initializeStatistics(){
        // As many rows as there are questions
        this.statistics = createStatistics(this.questionSet);

        // To maintain validation
        this.hasVoted = false;
    }

    /**
     * A static method to create a viable matrix for the statistics
     * 
     * The return matrix has many rows as there are questions, and each column has
     * as many entries as there are possible answers for that question
     * 
     * matrix[i][j] = Answer index j at Question index i
     * 
     * @param questionSet The set of all the Questions
     * @return The created statistics matrix
     */
    private static int[][] createStatistics(Set<QuestionInterface> questionSet){
        int[][] statistics = new int[questionSet.size()][];

        // As many columns as there are answers to each question
        int i = 0;
        for (QuestionInterface question : questionSet){
            statistics[i++] = new int[question.getPossibleAnswers().size()];
        }

        return statistics;
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
        // The Student currently being looped through
        Student student = null;
        Set<Integer>[] studentAnswers = null;

        // Loop through each student
        for (Map.Entry<Student, Set<Integer>[]> studentAnswersEntry : this.studentAnswersMap.entrySet()){
            student = studentAnswersEntry.getKey();
            studentAnswers = studentAnswersEntry.getValue();
            questionIndex = 0; // We have a new Student, so start at the first question

            // Loop through each question and see what answers the Student responds with
            for (QuestionInterface question : questionSet){
                if(studentAnswers[questionIndex] != null && studentAnswers[questionIndex].size() > 0){
                    answerIndices = studentAnswers[questionIndex];
                    for(int answerIndex : answerIndices){
                        this.statistics[questionIndex][answerIndex]--;

                        if (question.getAnswerAtPosition(answerIndex).isCorrect()){
                            this.numCorrect--;
                        } else {
                            this.numWrong--;
                        }
                    }

                    studentAnswers[questionIndex] = null;
                }

                // Get the indices of chosen answers
                answerIndices = student.getAnswerIndices(question);
                studentAnswers[questionIndex] = answerIndices;
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
