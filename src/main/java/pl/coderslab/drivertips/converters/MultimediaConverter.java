package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.dtos.MultimediaDTO;

@Component
public class MultimediaConverter {

    public MultimediaDTO toDTO(Multimedia multimedia){
        MultimediaDTO multimediaDTO = new MultimediaDTO();

        multimediaDTO.setId(multimedia.getId());
        multimediaDTO.setName(multimedia.getName());
        multimediaDTO.setContent(multimedia.getContent());
        multimediaDTO.setContentType(multimedia.getContentType());

        return multimediaDTO;
    }

    public Multimedia fromDTO(MultimediaDTO multimediaDTO){
        Multimedia multimedia = new Multimedia();


        multimedia.setId(multimediaDTO.getId());
        multimedia.setName(multimediaDTO.getName());
        multimedia.setContentType(multimediaDTO.getContentType());
        multimedia.setContent(multimediaDTO.getContent());

        return multimedia;
    }
}
