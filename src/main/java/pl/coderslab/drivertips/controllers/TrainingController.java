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

@RestController
@RequestMapping("/app/tip/{tipId}/training")
class TrainingController {

    /*
    TODO
    1. sprawdzenie poprawności endpointów
    2.CRUD treningu
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

    @GetMapping("")
    public TrainingDTO getTraining(@PathVariable Long tipId){
        Training training = trainingService.getTrainingByTipId(tipId);

        return trainingConverter.toDTO(training);
    }

    @PostMapping("")
    public ResponseEntity<TrainingDTO> createNew(@PathVariable Long tipId, @RequestBody TrainingDTO trainingDTO, UriComponentsBuilder uriComponentsBuilder){
        Tip tip = tipService.findById(tipId);

        Training training = trainingConverter.fromDTO(trainingDTO);

        Training saved = trainingService.createNewTraining(training, tip);

        trainingConverter.toDTO(saved);

        UriComponents uriComponents = uriComponentsBuilder.path("/app/tip/{tipId}/training/{id}").buildAndExpand(saved.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<TrainingDTO>(headers, HttpStatus.CREATED);
    }
}
