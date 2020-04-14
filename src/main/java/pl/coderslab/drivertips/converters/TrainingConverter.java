package pl.coderslab.drivertips.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.domain.Training;
import pl.coderslab.drivertips.dtos.TrainingDTO;

import java.util.stream.Collectors;

@Component
public class TrainingConverter {

    private TipConverter tipConverter;

    @Autowired
    public void setTipConverter(TipConverter tipConverter) {
        this.tipConverter = tipConverter;
    }

    private final QuestionsConverter questionsConverter;

    public TrainingConverter(QuestionsConverter questionsConverter) {
        this.questionsConverter = questionsConverter;
    }


    public TrainingDTO toDTO(Training training){
        TrainingDTO trainingDTO = new TrainingDTO();


        trainingDTO.setTitle(training.getTitle());
        trainingDTO.setQuestionDTOS(training.getQuestions().stream().map(question -> questionsConverter.toDTO(question)).collect(Collectors.toList()));
        trainingDTO.setTipDTO(tipConverter.toDTO(training.getTip()));

        return trainingDTO;
    }

    public Training fromDTO(TrainingDTO trainingDTO) {

        Training training = new Training();

        training.setTitle(trainingDTO.getTitle());
        training.setQuestions(trainingDTO.getQuestionDTOS().stream().map(questionDTO -> questionsConverter.fromDTO(questionDTO)).collect(Collectors.toList()));
        training.setTip(tipConverter.fromDTO(trainingDTO.getTipDTO()));

        return training;
    }
}
