package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.dtos.TagDTO;

import java.util.stream.Collectors;

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
//        tagDTO.setTipDTOS(tag.getTips().stream().map(tip -> tipConverter.toDTO(tip)).collect(Collectors.toSet()));

        return tagDTO;
    }

    public Tag fromDTO (TagDTO tagDTO){
        Tag tag = new Tag();

        tag.setId(tagDTO.getId());
        tag.setName(tagDTO.getName());
//        tag.setTips(tagDTO.getTipDTOS().stream().map(tipDTO -> tipConverter.fromDTO(tipDTO)).collect(Collectors.toSet()));

        return tag;
    }
}
