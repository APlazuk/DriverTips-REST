package pl.coderslab.drivertips.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.time.LocalDateTime;

@Getter @Setter
@ToString(exclude = "content")
public class MultimediaDTO {

    private Long id;
    private String name;
    private String contentType;
    @JsonIgnore
    private byte [] content;

}
