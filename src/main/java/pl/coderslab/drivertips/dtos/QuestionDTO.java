package pl.coderslab.drivertips.dtos;

import lombok.Data;

import java.util.List;

@Data
public class QuestionDTO {

    private String text;
    private Integer points;

    private List<AnswerDTO> answerDTOS;

    private List<MultimediaDTO> multimediaDTOS;
}
