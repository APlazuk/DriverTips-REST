package pl.coderslab.drivertips.controllers;

import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.TipConverter;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.services.TipService;

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


    @GetMapping("/all")
    @ApiOperation("Show all listed Tips")
    public List<TipDTO> getAll(){
        List<Tip> allTips = tipService.getAll();

        return allTips.stream().map(tipConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/latest")
    @ApiOperation("Show latest Tips")
    public List<TipDTO> getTheNewest(@RequestParam("limit") Integer limit) {
       List<Tip> tips = tipService.newestTips(limit);

        return tips.stream().map(tipConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ApiOperation("Show Tip")
    public TipDTO getById(@PathVariable Long id) {
        Tip tip = tipService.findById(id);

        return tipConverter.toDTO(tip);
    }

    @GetMapping("/search")
    @ApiOperation("Search for Tip")
    public List<TipDTO> searchTipsByName(@RequestParam String name) {
        List<Tip> tips = tipService.searchTip(name);

        return tips.stream().map(tip -> tipConverter.toDTO(tip)).collect(Collectors.toList());
    }

    @PostMapping("")
    @ApiOperation("Create new Tip")
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
    @ApiOperation("Update existing Tip")
    public TipDTO edit(@PathVariable Long id, @RequestBody @Valid TipDTO tipDTO) {
        Tip tipToUpdate = tipService.findById(id);

        tipConverter.applyChanges(tipToUpdate, tipDTO);

        Tip updatedTip = tipService.updateTip(tipToUpdate);

        return tipConverter.toDTO(updatedTip);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete existing Tip")
    public void delete(@PathVariable Long id) {
        Tip tipToDelete = tipService.findById(id);

        tipService.delete(tipToDelete);
    }
}
