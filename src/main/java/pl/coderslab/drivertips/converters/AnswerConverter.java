package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.domain.Answer;
import pl.coderslab.drivertips.dtos.AnswerDTO;

import java.util.stream.Collectors;

@Component
public class AnswerConverter {

    private final MultimediaConverter multimediaConverter;

    public AnswerConverter(MultimediaConverter multimediaConverter) {
        this.multimediaConverter = multimediaConverter;
    }

    public AnswerDTO toDTO(Answer answer){
        AnswerDTO answerDTO = new AnswerDTO();

        answerDTO.setText(answer.getText());
        answerDTO.setCorrect(answer.getCorrect());
        answerDTO.setMultimediaDTOS(answer.getMultimedia().stream().map(multimedia -> multimediaConverter.toDTO(multimedia)).collect(Collectors.toList()));

        return answerDTO;
    }

    public Answer fromDTO(AnswerDTO answerDTO){
        Answer answer = new Answer();

        answer.setText(answerDTO.getText());
        answer.setCorrect(answerDTO.getCorrect());
        answer.setMultimedia(answerDTO.getMultimediaDTOS().stream().map(multimediaDTO -> multimediaConverter.fromDTO(multimediaDTO)).collect(Collectors.toList()));

        return answer;

    }
}
