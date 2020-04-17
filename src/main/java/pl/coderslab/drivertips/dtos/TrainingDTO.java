package pl.coderslab.drivertips.dtos;

import lombok.Data;
import java.util.List;


@Data
public class TrainingDTO {

    private Long id;
    private String title;

    private List<QuestionDTO> questionDTOS;
}
