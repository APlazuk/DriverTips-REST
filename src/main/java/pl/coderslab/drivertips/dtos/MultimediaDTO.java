package pl.coderslab.drivertips.dtos;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data
@ToString(exclude = "content")
public class MultimediaDTO {


    private String name;
    private String contentType;
    private byte[] content;
    private LocalDateTime createdOn;
    private String createdBy;
    private LocalDateTime updatedOn;
    private String updatedBy;
}
