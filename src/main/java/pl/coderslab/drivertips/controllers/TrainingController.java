package pl.coderslab.drivertips.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.QuestionsConverter;
import pl.coderslab.drivertips.converters.TrainingConverter;
import pl.coderslab.drivertips.dtos.TrainingDTO;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.services.TipService;
import pl.coderslab.drivertips.services.TrainingService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip/{tipId}/training")
class TrainingController {

    /*
    TODO
    4.sumowanie punktów na dany trening
    */

    private final TrainingService trainingService;
    private final TrainingConverter trainingConverter;
    private final TipService tipService;

    TrainingController(TrainingService trainingService, TrainingConverter trainingConverter, TipService tipService) {
        this.trainingService = trainingService;
        this.trainingConverter = trainingConverter;
        this.tipService = tipService;
    }

    @GetMapping("/all")
    public List<TrainingDTO> getAll() {
        List<Training> allTrainings = trainingService.getAll();

        return allTrainings.stream().map(trainingConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping("")
    public TrainingDTO getTraining(@PathVariable Long tipId) {
        Training training = trainingService.getTrainingByTipId(tipId);

        return trainingConverter.toDTO(training);
    }

    @PostMapping("")
    public ResponseEntity<TrainingDTO> createNew(@PathVariable Long tipId, @RequestBody TrainingDTO trainingDTO, UriComponentsBuilder uriComponentsBuilder) {
        Tip tip = tipService.findById(tipId);

        Training training = trainingConverter.fromDTO(trainingDTO);

        Training saved = trainingService.createNewTraining(training, tip);

        trainingConverter.toDTO(saved);

        UriComponents uriComponents = uriComponentsBuilder.path("/app/tip/{tipId}/training/{id}").buildAndExpand(tipId, saved.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<TrainingDTO>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public TrainingDTO edit(@PathVariable Long id, @RequestBody TrainingDTO trainingDTO) {
        Training trainingToUpdate = trainingService.findTrainingById(id);

        trainingConverter.applyChanges(trainingToUpdate, trainingDTO);

        Training updatedTraining = trainingService.updateTraining(trainingToUpdate);

        return trainingConverter.toDTO(updatedTraining);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Training trainingToDelete = trainingService.findTrainingById(id);

        trainingService.delete(trainingToDelete);
    }
}
