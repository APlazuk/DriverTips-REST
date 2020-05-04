package pl.coderslab.drivertips.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class TagDTO {


    private Long id;
    private String name;
    private Set<TipDTO> tipDTOS;
}
