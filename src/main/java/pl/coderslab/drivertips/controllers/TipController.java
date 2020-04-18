package pl.coderslab.drivertips.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.MultimediaConverter;
import pl.coderslab.drivertips.converters.TipConverter;
import pl.coderslab.drivertips.converters.TrainingConverter;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Training;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.dtos.TrainingDTO;
import pl.coderslab.drivertips.services.MultimediaService;
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

    public TipController(TipService tipService, TipConverter tipConverter) {
        this.tipService = tipService;
        this.tipConverter = tipConverter;
    }

     /*
    TODO
    1.Najpopularniejsze Porady
    */

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

    @PostMapping("")
    public ResponseEntity<TipDTO> createNewTip(@RequestBody @Valid TipDTO tipDTO, UriComponentsBuilder uriComponentsBuilder) {
        Tip tip = tipConverter.fromDTO(tipDTO);

        Tip saved = tipService.createNewTip(tip);

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
}
