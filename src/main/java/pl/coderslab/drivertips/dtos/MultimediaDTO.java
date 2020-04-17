package pl.coderslab.drivertips.dtos;

import lombok.Data;
import lombok.ToString;
import java.time.LocalDateTime;

@Data @ToString(exclude = "content")
public class MultimediaDTO {

    private Long id;
    private String name;
    private String contentType;
    private byte [] content;

}
