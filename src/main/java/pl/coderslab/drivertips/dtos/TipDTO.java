package pl.coderslab.drivertips.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
public class TipDTO {

    private Long id;

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    private LocalDate date;

    private List<MultimediaDTO> multimediaDTO;
    private Set<TagDTO> tagDTOS;

}
