package pl.coderslab.drivertips.dtos;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
public class MultimediaDTO {


    private String name;
    private String contentType;
    private String url;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
