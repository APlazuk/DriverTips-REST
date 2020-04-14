package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.domain.Multimedia;
import pl.coderslab.drivertips.dtos.MultimediaDTO;

@Component
public class MultimediaConverter {

    public MultimediaDTO toDTO(Multimedia multimedia){
        MultimediaDTO multimediaDTO = new MultimediaDTO();

        multimediaDTO.setName(multimedia.getName());
        multimediaDTO.setContent(multimedia.getContent());
        multimediaDTO.setContentType(multimedia.getContentType());
        multimediaDTO.setCreatedOn(multimedia.getCreatedOn());
        multimediaDTO.setCreatedBy(multimedia.getCreatedBy());
        multimediaDTO.setUpdatedOn(multimedia.getUpdatedOn());
        multimediaDTO.setUpdatedBy(multimedia.getUpdatedBy());

        return multimediaDTO;
    }

    public Multimedia fromDTO(MultimediaDTO multimediaDTO){
        Multimedia multimedia = new Multimedia();

        multimedia.setName(multimediaDTO.getName());
        multimedia.setContent(multimediaDTO.getContent());
        multimedia.setContentType(multimediaDTO.getContentType());
        multimedia.setCreatedOn(multimediaDTO.getCreatedOn());
        multimedia.setCreatedBy(multimediaDTO.getCreatedBy());
        multimedia.setUpdatedOn(multimediaDTO.getUpdatedOn());
        multimedia.setUpdatedBy(multimediaDTO.getUpdatedBy());

        return multimedia;
    }
}
