package pl.coderslab.drivertips.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.domain.Training;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.domain.Tip;
import pl.coderslab.drivertips.dtos.TrainingDTO;

import java.util.stream.Collectors;

@Component
public class TipConverter {

    private TrainingConverter trainingConverter;

    @Autowired
    public void setTrainingConverter(TrainingConverter trainingConverter){
        this.trainingConverter = trainingConverter;
    }

    private final TagConverter tagConverter;
    private final MultimediaConverter multimediaConverter;

    public TipConverter(TagConverter tagConverter, MultimediaConverter multimediaConverter) {
        this.tagConverter = tagConverter;
        this.multimediaConverter = multimediaConverter;
    }

    public TipDTO toDTO(Tip tip){
        TipDTO tipDTO = new TipDTO();

        tipDTO.setId(tip.getId());
        tipDTO.setTitle(tip.getTitle());
        tipDTO.setDescription(tip.getDescription());
        tipDTO.setDate(tip.getDate());

        Training training = tip.getTraining();
        TrainingDTO trainingDTO = trainingConverter.toDTO(training);


        tipDTO.setTrainingDTO(trainingDTO);
        tipDTO.setMultimediaDTOS(tip.getMultimedia().stream().map(multimedia -> multimediaConverter.toDTO(multimedia)).collect(Collectors.toList()));
        tipDTO.setTagDTOS(tip.getTags().stream().map(tag -> tagConverter.toDTO(tag)).collect(Collectors.toList()));

        return tipDTO;
    }

    public Tip fromDTO(TipDTO tipDTO) {
        Tip tip = new Tip();

        tip.setId(tipDTO.getId());
        tip.setTitle(tipDTO.getTitle());
        tip.setDescription(tipDTO.getDescription());
        tip.setDate(tipDTO.getDate());

        TrainingDTO trainingDTO = tipDTO.getTrainingDTO();
        Training training = trainingConverter.fromDTO(trainingDTO);

        tip.setTraining(training);
        tip.setMultimedia(tipDTO.getMultimediaDTOS().stream().map(multimediaDTO -> multimediaConverter.fromDTO(multimediaDTO)).collect(Collectors.toList()));
        tip.setTags(tipDTO.getTagDTOS().stream().map(tagDTO -> tagConverter.fromDTO(tagDTO)).collect(Collectors.toList()));
        return tip;
    }

    public void applyChanges(Tip tip, TipDTO tipDTO) {

        tip.setDescription(tipDTO.getDescription());
        tip.setDate(tipDTO.getDate());
        tip.setTitle(tipDTO.getTitle());
        tip.setId(tipDTO.getId());

        TrainingDTO trainingDTO = tipDTO.getTrainingDTO();
        Training training = trainingConverter.fromDTO(trainingDTO);

        tip.setTraining(training);
        tip.setMultimedia(tipDTO.getMultimediaDTOS().stream().map(multimediaDTO -> multimediaConverter.fromDTO(multimediaDTO)).collect(Collectors.toList()));
        tip.setTags(tipDTO.getTagDTOS().stream().map(tagDTO -> tagConverter.fromDTO(tagDTO)).collect(Collectors.toList()));
    }
}
