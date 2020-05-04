package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.dtos.TagDTO;


@Component

public class TagConverter {

    private TipConverter tipConverter;

    public void setTipConverter(TipConverter tipConverter) {
        this.tipConverter = tipConverter;
    }

    public TagDTO toDTO(Tag tag) {
        TagDTO tagDTO = new TagDTO();

        tagDTO.setId(tag.getId());
        tagDTO.setName(tag.getName());

        return tagDTO;
    }

    public Tag fromDTO (TagDTO tagDTO){
        Tag tag = new Tag();

        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());

        return tag;
    }

    public void applyChanges(Tag tag, TagDTO tagDTO) {

        tag.setName(tagDTO.getName());
    }
}
