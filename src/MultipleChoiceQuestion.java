public class MultipleChoiceQuestion extends Question{

    public MultipleChoiceQuestion(String questionString){
        super(questionString);
        this.isMultipleChoice = true;
    }

    @Override
    public void addPossibleAnswer(String answerString, boolean isCorrect) {
        if  (!this.answerList.add(new Answer(answerString, isCorrect))){
            throw new IllegalArgumentException("That answer is already a possible answer.");
        }
    }
}