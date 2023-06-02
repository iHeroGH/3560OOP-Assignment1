import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

public class VotingService {
    
    protected Set<Student> studentList;
    protected Set<Question> questionList;
    protected int[][] statistics;

    public VotingService(Student[] studentList, Question[] questionList){
        this.studentList = new HashSet<Student>(Arrays.asList(studentList));
        this.questionList = new HashSet<Question>(Arrays.asList(questionList));

        initializeStatistics(this.questionList.size());
    }

    public VotingService(Set<Student> studentList, Set<Question> questionList){
        this.studentList = studentList;
        this.questionList = questionList;

        initializeStatistics(this.questionList.size());
    }

    private void initializeStatistics(int numQuestions){
        this.statistics = new int[numQuestions][];

        int i = 0;
        for (Question question : this.questionList){
            this.statistics[i++] = new int[question.getPossibleAnswers().size()];
        }
    }

    public void chooseAnswers(){
        int questionIndex = 0;
        int answerIndex = 0;

        for (Student student : studentList){
            questionIndex = 0;
            for (Question question : questionList){
                answerIndex = student.getAnswerIndex(question);
                this.statistics[questionIndex][answerIndex]++;

                questionIndex++;
            }   
        }
    }

    public void printStatistics(){
        int questionIndex = 0;
        int answerIndex = 0;
        String correctAnswersString = "Correct Answers: ";

        for (Question question : questionList){
            System.out.println(question.getQuestionString());
            answerIndex = 0;
            
            for (Answer answer : question.getPossibleAnswers()){
                System.out.print(answer.getAnswerString() + " : ");
                System.out.println(this.statistics[questionIndex][answerIndex]);
                
                if (answer.isCorrect()){
                    correctAnswersString += "\"" + answer.getAnswerString() + "\"";
                }

                answerIndex++;
            }

            System.out.println(correctAnswersString);

            questionIndex++;
        }

    }
}
