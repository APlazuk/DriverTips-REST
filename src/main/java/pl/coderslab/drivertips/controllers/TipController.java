package pl.coderslab.drivertips.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.MultimediaConverter;
import pl.coderslab.drivertips.converters.TipConverter;
import pl.coderslab.drivertips.converters.TrainingConverter;
import pl.coderslab.drivertips.dtos.MultimediaDTO;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.dtos.TrainingDTO;
import pl.coderslab.drivertips.services.MultimediaService;
import pl.coderslab.drivertips.services.TipService;
import pl.coderslab.drivertips.services.TrainingService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip")
public class TipController {

    private final MultimediaConverter multimediaConverter;
    private final MultimediaService multimediaService;
    private final TipService tipService;
    private final TipConverter tipConverter;
    private final TrainingConverter trainingConverter;
    private final TrainingService trainingService;

    public TipController(MultimediaConverter multimediaConverter, MultimediaService multimediaService, TipService tipService, TipConverter tipConverter, TrainingConverter trainingConverter, TrainingService trainingService) {
        this.multimediaConverter = multimediaConverter;
        this.multimediaService = multimediaService;
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

    @GetMapping("/{tipId}/training")
    public TrainingDTO getTraining(@PathVariable Long tipId){
        Training training = trainingService.getTrainingByTipId(tipId);

        return trainingConverter.toDTO(training);
    }

    //do sprawdzenia na konsultacji

    @PostMapping("")
    public ResponseEntity<TipDTO> createNewTip(@RequestParam("media") Long id, @RequestBody @Valid TipDTO tipDTO, UriComponentsBuilder uriComponentsBuilder) {

        Multimedia media = multimediaService.getMediaById(id);

        Tip tip = tipConverter.fromDTO(tipDTO);

        Tip saved = tipService.createNewTip(tip, media);

        tipConverter.toDTO(saved);

        UriComponents uriComponents = uriComponentsBuilder.path("/app/tip/{id}").buildAndExpand(saved.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<TipDTO>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public TipDTO edit(@PathVariable Long id, @RequestBody @Valid TipDTO tipDTO) {
        Tip tipToUpdate = tipService.findById(id);

        tipConverter.applyChanges(tipToUpdate, tipDTO);

        Tip updatedTip = tipService.updateTip(id, tipToUpdate);

        return tipConverter.toDTO(updatedTip);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Tip tipToDelete = tipService.findById(id);

        tipService.delete(tipToDelete);
    }

    /*
    TODO
    1.Najpopularniejsze Porady
    */


}
