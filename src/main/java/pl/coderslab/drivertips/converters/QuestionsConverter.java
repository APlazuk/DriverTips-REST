package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.domain.Question;
import pl.coderslab.drivertips.dtos.QuestionDTO;

import java.util.stream.Collectors;

@Component
public class QuestionsConverter {

    private final MultimediaConverter multimediaConverter;
    private final AnswerConverter answerConverter;

    public QuestionsConverter(MultimediaConverter multimediaConverter, AnswerConverter answerConverter) {
        this.multimediaConverter = multimediaConverter;
        this.answerConverter = answerConverter;
    }

    public QuestionDTO toDTO(Question question){
        QuestionDTO questionDTO = new QuestionDTO();

        questionDTO.setText(question.getText());
        questionDTO.setPoints(question.getPoints());
        questionDTO.setAnswerDTOS(question.getAnswers().stream().map(answer ->answerConverter.toDTO(answer)).collect(Collectors.toList()));
        questionDTO.setMultimediaDTOS(question.getMultimedia().stream().map(multimedia -> multimediaConverter.toDTO(multimedia)).collect(Collectors.toList()));

        return questionDTO;
    }

    public Question fromDTO(QuestionDTO questionDTO){
        Question question = new Question();

        question.setText(questionDTO.getText());
        question.setPoints(questionDTO.getPoints());
        question.setAnswers(questionDTO.getAnswerDTOS().stream().map(answerDTO -> answerConverter.fromDTO(answerDTO)).collect(Collectors.toList()));
        question.setMultimedia(questionDTO.getMultimediaDTOS().stream().map(multimediaDTO -> multimediaConverter.fromDTO(multimediaDTO)).collect(Collectors.toList()));

        return question;
    }
}