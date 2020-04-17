package pl.coderslab.drivertips.converters;

import org.springframework.stereotype.Component;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.dtos.TrainingDTO;

import java.util.stream.Collectors;

@Component
public class TrainingConverter {

    private final QuestionsConverter questionsConverter;

    public TrainingConverter(QuestionsConverter questionsConverter) {
        this.questionsConverter = questionsConverter;
    }


    public TrainingDTO toDTO(Training training){
        TrainingDTO trainingDTO = new TrainingDTO();

        trainingDTO.setId(training.getId());
        trainingDTO.setTitle(training.getTitle());
        trainingDTO.setQuestionDTOS(training.getQuestions().stream().map(question -> questionsConverter.toDTO(question)).collect(Collectors.toList()));

        return trainingDTO;
    }

    public Training fromDTO(TrainingDTO trainingDTO) {

        Training training = new Training();

        training.setId(trainingDTO.getId());
        training.setTitle(trainingDTO.getTitle());
        training.setQuestions(trainingDTO.getQuestionDTOS().stream().map(questionDTO -> questionsConverter.fromDTO(questionDTO)).collect(Collectors.toList()));

        return training;
    }
}
