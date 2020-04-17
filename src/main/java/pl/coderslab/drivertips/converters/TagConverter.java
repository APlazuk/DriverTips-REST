package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.dtos.TagDTO;

@Component

public class TagConverter {

    private final TipConverter tipConverter;

    public TagConverter(TipConverter tipConverter) {
        this.tipConverter = tipConverter;
    }

    public TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();

        tagDTO.setName(tag.getName());
        tagDTO.setTipDTO(tipConverter.toDTO(tag.getTip()));

        return tagDTO;
    }

    public Tag fromDTO (TagDTO tagDTO){
        Tag tag = new Tag();

        tag.setName(tagDTO.getName());
        tag.setTip(tipConverter.fromDTO(tagDTO.getTipDTO()));

        return tag;
    }
}
