package pl.coderslab.drivertips.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Data
public class TipDTO {

    private Long id;

    @NotBlank
    private String title;

    private String description;

    @NotBlank
    private LocalDate date;

    private List<TagDTO> tagDTOS;

}
