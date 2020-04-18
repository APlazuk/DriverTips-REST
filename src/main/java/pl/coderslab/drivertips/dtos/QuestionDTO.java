package pl.coderslab.drivertips.dtos;

import lombok.Data;
import pl.coderslab.drivertips.model.Training;

import java.util.List;

@Data
public class QuestionDTO {

    private Long id;
    private String text;
    private Integer points;

    private List<AnswerDTO> answerDTOS;

    private List<MultimediaDTO> multimediaDTOS;
}
