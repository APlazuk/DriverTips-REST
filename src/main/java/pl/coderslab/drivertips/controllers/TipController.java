package pl.coderslab.drivertips.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.TipConverter;
import pl.coderslab.drivertips.converters.TrainingConverter;
import pl.coderslab.drivertips.domain.Training;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.domain.Tip;
import pl.coderslab.drivertips.dtos.TrainingDTO;
import pl.coderslab.drivertips.services.TipService;
import pl.coderslab.drivertips.services.TrainingService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip")
public class TipController {

    private final TipService tipService;
    private final TipConverter tipConverter;
    private final TrainingConverter trainingConverter;
    private final TrainingService trainingService;

    public TipController(TipService tipService, TipConverter tipConverter, TrainingConverter trainingConverter, TrainingService trainingService) {
        this.tipService = tipService;
        this.tipConverter = tipConverter;
        this.trainingConverter = trainingConverter;
        this.trainingService = trainingService;
    }

    @GetMapping("/latest")
    public List<TipDTO> getTheNewest(@RequestParam("limit") Integer limit) {
        List<Tip> tips = tipService.newestTips(limit);

        return tips.stream().map(tipConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TipDTO getById(@PathVariable Long id) {
        Tip tip = tipService.findById(id);

        return tipConverter.toDTO(tip);
    }

    @GetMapping("/search")
    public List<TipDTO> searchTipsByName(@RequestParam String name){
        List<Tip> tips = tipService.searchTip(name);

        return tips.stream().map(tip -> tipConverter.toDTO(tip)).collect(Collectors.toList());
    }

    @GetMapping("/training/{tipId}")
    public TrainingDTO getTraining(@PathVariable Long tipId){
        Training training = trainingService.getTrainingByTipId(tipId);

        return trainingConverter.toDTO(training);
    }

    @PostMapping("")
    public ResponseEntity<TipDTO> createNewTip(@RequestBody @Valid TipDTO tipDTO, UriComponentsBuilder uriComponentsBuilder) {

        Tip tip = tipConverter.fromDTO(tipDTO);

        Tip saved = tipService.createNewTip(tip);

        tipConverter.toDTO(saved);

        UriComponents uriComponents = uriComponentsBuilder.path("/app/tip/{id}").buildAndExpand(saved.getId());
        //przy mulimediach do sklejenia stringa

        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping("/{id}")
    public TipDTO edit(@PathVariable Long id, @RequestBody @Valid TipDTO tipDTO) {
        Tip tipToUpdate = tipService.findById(id);

        tipConverter.applyChanges(tipToUpdate, tipDTO);

        Tip updatedTip = tipService.updateTip(id, tipToUpdate);

        return tipConverter.toDTO(updatedTip);
    }



    /*
     TO DO
        3.delete

    */
}
