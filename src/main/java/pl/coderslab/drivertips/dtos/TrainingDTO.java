package pl.coderslab.drivertips.dtos;

import lombok.Data;
import java.util.List;


@Data
public class TrainingDTO {

    private String title;

    private TipDTO tipDTO;

    private List<QuestionDTO> questionDTOS;
}
