import java.util.Set;
import java.util.HashSet;

public class SimulationDriver {

    public static void main(String[] args){
        Set<Student> studentSet = new HashSet<Student>();
        studentSet.add(new Student());
        // studentSet.add(new Student());
        // studentSet.add(new Student());
        // studentSet.add(new Student());
        // studentSet.add(new Student());
        // studentSet.add(new Student());
        // studentSet.add(new Student());

        QuestionInterface q1 = new Question("What is 1 + 1?");
        q1.addCorrectAnswer("2");
        q1.addPossibleAnswer("1", false);
        q1.addPossibleAnswer("3", false);

        QuestionInterface q2 = new Question("What is 2 + 2?");
        q2.addPossibleAnswer("5", false);
        q2.addPossibleAnswer("4", true);
        q2.addPossibleAnswer("3", false);
        q2.addPossibleAnswer("55", false);
        q2.addPossibleAnswer("15", false);

        QuestionInterface q3 = new MultipleChoiceQuestion("What is a multiple of 5?");
        q3.addCorrectAnswer("10");
        q3.addCorrectAnswer("20");
        q3.addCorrectAnswer("30");
        q3.addCorrectAnswer("40");
        q3.addPossibleAnswer("43", false);
        
        Set<QuestionInterface> questionSet = new HashSet<QuestionInterface>();
        questionSet.add(q1);
        questionSet.add(q2);
        questionSet.add(q3);

        VotingService vs = new VotingService(studentSet, questionSet);
        vs.chooseAnswers();
        vs.printStatistics();
    }

}