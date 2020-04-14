package pl.coderslab.drivertips.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TipDTO {

    private Long id;
    private String title;
    private String description;
    private LocalDate date;

    private List<MultimediaDTO> multimediaDTOS;

    private List<TagDTO> tagDTOS;

}
