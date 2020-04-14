package pl.coderslab.drivertips.dtos;

import lombok.Data;
import java.util.List;

@Data
public class AnswerDTO {

    private String text;
    private Boolean correct;

    private List<MultimediaDTO> multimediaDTOS;
}
