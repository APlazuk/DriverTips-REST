package pl.coderslab.drivertips.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.TagConverter;
import pl.coderslab.drivertips.dtos.TagDTO;
import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.services.TagService;
import pl.coderslab.drivertips.services.TipService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/app/tip/{tipId}/tag")
class TagController {

    private final TagService tagService;
    private final TagConverter tagConverter;
    private final TipService tipService;

    TagController(TagService tagService, TagConverter tagConverter, TipService tipService) {
        this.tagService = tagService;
        this.tagConverter = tagConverter;
        this.tipService = tipService;
    }

    @GetMapping("/all")
    public List<TagDTO> getAllTags() {
        List<Tag> allTags = tagService.getAllTags();

        return allTags.stream().map(tag -> tagConverter.toDTO(tag)).collect(Collectors.toList());
    }

    @PostMapping("")
    public ResponseEntity<TagDTO> createNewTag(@PathVariable Long tipId, @RequestBody TagDTO tagDTO, UriComponentsBuilder uriComponentsBuilder) {
        Tip tip = tipService.findById(tipId);

        Tag tag = tagConverter.fromDTO(tagDTO);

        Tag saved = tagService.createNewTag(tag, tip);

        tagConverter.toDTO(saved);

        UriComponents uriComponents = uriComponentsBuilder.path("/app/tip/{tipId}/tag/{id}").buildAndExpand(tipId, saved.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(uriComponents.toUri());

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
