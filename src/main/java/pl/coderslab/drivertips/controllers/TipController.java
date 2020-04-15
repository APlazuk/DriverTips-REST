package pl.coderslab.drivertips.controllers;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.drivertips.converters.TipConverter;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.domain.Tip;
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

    @GetMapping("/latest")
    public List<TipDTO> getTheNewest(@RequestParam("limit") Integer limit) {
        List<Tip> tips = tipService.newestTips(limit);

        return tips.stream().map(tipConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public TipDTO getById(@PathVariable Long id) {
        Tip tip = tipService.findById(id);

        return tipConverter.toDTO(tip);
    }

    @PostMapping("")
    public TipDTO createNewTip(@Valid @RequestBody TipDTO tipDTO) {

        Tip tip = tipConverter.fromDTO(tipDTO);

        Tip saved = tipService.createNewTip(tip);

        return tipConverter.toDTO(saved);
    }

    @PutMapping("/{id}")
    public TipDTO edit(@PathVariable Long id, @Valid @RequestBody TipDTO tipDTO) {
        Tip tipToUpdate = tipService.findById(id);

        tipConverter.applyChanges(tipToUpdate,tipDTO);

        Tip updatedTip = tipService.updateTip(id, tipToUpdate);

        return tipConverter.toDTO(updatedTip);
    }
}
