import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class VotingService {
    
    protected Set<Student> studentList;
    protected Set<QuestionInterface> questionList;
    protected int numCorrect;
    protected int numWrong;
    protected int[][] statistics;

    public VotingService(Student[] studentList, QuestionInterface[] questionList){
        this(
            new HashSet<Student>(Arrays.asList(studentList)),
            new HashSet<QuestionInterface>(Arrays.asList(questionList))
        );
    }

    public VotingService(Set<Student> studentList, Set<QuestionInterface> questionList){
        this.studentList = studentList;
        this.questionList = questionList;

        initializeStatistics(this.questionList.size());
    }

    private void initializeStatistics(int numQuestions){
        this.statistics = new int[numQuestions][];

        int i = 0;
        for (QuestionInterface question : this.questionList){
            this.statistics[i++] = new int[question.getPossibleAnswers().size()];
        }
    }

    public void chooseAnswers(){
        int questionIndex = 0;
        Set<Integer> answerIndices = null;

        for (Student student : studentList){
            questionIndex = 0;
            for (QuestionInterface question : questionList){
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
    }

    public void printStatistics(){
        int questionIndex = 0;
        int answerIndex = 0;

        for (QuestionInterface question : questionList){
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
