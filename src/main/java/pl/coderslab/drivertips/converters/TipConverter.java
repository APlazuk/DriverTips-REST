package pl.coderslab.drivertips.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.model.Tip;

import java.util.stream.Collectors;

@Component
public class TipConverter {

    private TagConverter tagConverter;
    private final MultimediaConverter multimediaConverter;

    public TipConverter(final MultimediaConverter multimediaConverter) {
        this.multimediaConverter = multimediaConverter;
    }

    @Autowired
    public void setTagConverter(TagConverter tagConverter) {
        this.tagConverter = tagConverter;
    }


    public TipDTO toDTO(Tip tip){
        TipDTO tipDTO = new TipDTO();

        tipDTO.setId(tip.getId());
        tipDTO.setTitle(tip.getTitle());
        tipDTO.setDescription(tip.getDescription());
        tipDTO.setDate(tip.getDate());

        tipDTO.setMultimediaDTO(tip.getMultimedia().stream().map(multimedia -> multimediaConverter.toDTO(multimedia)).collect(Collectors.toList()));
        tipDTO.setTagDTOS(tip.getTags().stream().map(tag -> tagConverter.toDTO(tag)).collect(Collectors.toList()));

        return tipDTO;
    }

    public Tip fromDTO(TipDTO tipDTO) {
        Tip tip = new Tip();

        tip.setId(tipDTO.getId());
        tip.setTitle(tipDTO.getTitle());
        tip.setDescription(tipDTO.getDescription());
        tip.setDate(tipDTO.getDate());

        tip.setMultimedia(tipDTO.getMultimediaDTO().stream().map(multimediaDTO -> multimediaConverter.fromDTO(multimediaDTO)).collect(Collectors.toList()));
        tip.setTags(tipDTO.getTagDTOS().stream().map(tagDTO -> tagConverter.fromDTO(tagDTO)).collect(Collectors.toList()));
        return tip;
    }

    public void applyChanges(Tip tip, TipDTO tipDTO) {

        tip.setDescription(tipDTO.getDescription());
        tip.setDate(tipDTO.getDate());
        tip.setTitle(tipDTO.getTitle());

        tip.setMultimedia(tipDTO.getMultimediaDTO().stream().map(multimediaDTO -> multimediaConverter.fromDTO(multimediaDTO)).collect(Collectors.toList()));
        tip.setTags(tipDTO.getTagDTOS().stream().map(tagDTO -> tagConverter.fromDTO(tagDTO)).collect(Collectors.toList()));
    }
}
