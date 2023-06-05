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
        this.statistics = new int[this.questionSet.size()][];

        int i = 0;
        for (QuestionInterface question : this.questionSet){
            this.statistics[i++] = new int[question.getPossibleAnswers().size()];
        }

        this.hasVoted = false;
    }

    /**
     * Runs the entire simulation by choosing Answers for every Question for every
     * Student.
     * 
     * Records the statistics to be printed later.
     */
    public void chooseAnswers(){
        int questionIndex = 0;
        Set<Integer> answerIndices = null;

        for (Student student : studentSet){
            questionIndex = 0;
            for (QuestionInterface question : questionSet){
                answerIndices = student.getAnswerIndices(question);
                for(int answerIndex : answerIndices) {
                    this.statistics[questionIndex][answerIndex]++;
                    
                    if (question.getAnswerAtPosition(answerIndex).isCorrect()){
                        this.numCorrect++;
                    } else {
                        this.numWrong++;
                    }
                }

                questionIndex++;
            }   
        }

        this.hasVoted = true;
    }

    /**
     * Prints the statistics calculated
     * @throws IllegalStateException If the voting has not been done yet
     *                               (chooseAnswers() wasn't called) 
     */
    public void printStatistics(){

        if (!hasVoted){
            throw new IllegalStateException(
                "The statistics for this service have not been calculated." +
                "Make sure to run `chooseAnswers() before printing the statistics."
            );
        }

        int questionIndex = 0;
        int answerIndex = 0;

        for (QuestionInterface question : questionSet){
            System.out.println(question.getQuestionString());
            answerIndex = 0;
            
            for (Answer answer : question.getPossibleAnswers()){
                if (answer.isCorrect()){
                    System.out.print(answer.getAnswerString() + " : ");
                    System.out.print(this.statistics[questionIndex][answerIndex]);
                    System.out.println("**");
                } else {
                    System.out.print(answer.getAnswerString() + " : ");
                    System.out.println(this.statistics[questionIndex][answerIndex]);
                }

                answerIndex++;
            }
            
            System.out.println();
            questionIndex++;
        }
        
        System.out.println("Total Correct: " + this.numCorrect);
        System.out.println("Total Incorrect: " + this.numWrong);
    }
}
