package pl.coderslab.drivertips.controllers;

import org.springframework.web.bind.annotation.*;
import pl.coderslab.drivertips.converters.TipConverter;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.domain.Tip;
import pl.coderslab.drivertips.services.TipService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tip")
public class TipController {

    private final TipService tipService;
    private final TipConverter tipConverter;

    public TipController(TipService tipService, TipConverter tipConverter) {
        this.tipService = tipService;
        this.tipConverter = tipConverter;
    }

    @GetMapping("/latest") //requestparam z iloscią ostatnich tipów które chce pobrać
    public List<TipDTO> get(@RequestParam String limit){
        List<Tip> tips = tipService.newestTips();

        return tips.stream().map(tipConverter::toDTO).collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public TipDTO get(@PathVariable Long id){
        Tip tip = tipService.findById(id);

        return tipConverter.toDTO(tip);
    }

    @PostMapping("")
    public TipDTO create(@RequestBody TipDTO tipDTO){
        Tip tip = tipConverter.fromDTO(tipDTO);

        Tip saved = tipService.save(tip);

        return tipConverter.toDTO(saved);
    }

    @PutMapping("/{id}")
    public TipDTO edit(@PathVariable Long id, @RequestBody TipDTO tipDTO){
        Tip tip = tipService.findById(id);

        tipConverter.applyChanges(tip,tipDTO);
        Tip save = tipService.save(tip);

        return tipConverter.toDTO(save);
    }
}
