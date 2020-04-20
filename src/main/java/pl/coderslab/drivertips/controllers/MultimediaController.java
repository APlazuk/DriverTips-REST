package pl.coderslab.drivertips.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;
import pl.coderslab.drivertips.converters.MultimediaConverter;
import pl.coderslab.drivertips.dtos.MultimediaDTO;
import pl.coderslab.drivertips.dtos.TipDTO;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.services.MultimediaService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/app/multimedia")
class MultimediaController {

    private final MultimediaService multimediaService;
    private final MultimediaConverter multimediaConverter;

    MultimediaController(MultimediaService multimediaService, MultimediaConverter multimediaConverter) {
        this.multimediaService = multimediaService;
        this.multimediaConverter = multimediaConverter;
    }

    @GetMapping
    public List<MultimediaDTO> getAll() {
        List<Multimedia> allMedia = multimediaService.getAll();

        return allMedia.stream().map(multimediaConverter::toDTO).collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        Multimedia media = multimediaService.getMediaById(id);

        ByteArrayResource resource = new ByteArrayResource((media.getContent()));
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(media.getContentType()))
                .header("Content-Disposition", "filename =" + media.getName())
                .body(resource);

    }

    @PostMapping
    public ResponseEntity<?> add(@RequestParam("media") MultipartFile media, MultimediaDTO uploadedMediaDTO, UriComponentsBuilder uriComponentsBuilder) throws IOException {

        try {
            String filename = media.getOriginalFilename();
            String contentType = media.getContentType();
            byte[] bytes = media.getBytes();

            uploadedMediaDTO.setName(filename);
            uploadedMediaDTO.setContentType(contentType);
            uploadedMediaDTO.setContent(bytes);

            Multimedia saved = multimediaService.addMedia(multimediaConverter.fromDTO(uploadedMediaDTO));

            UriComponents uriComponents = uriComponentsBuilder.path("/app/multimedia/{id}").buildAndExpand(saved.getId());
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(uriComponents.toUri());

            return new ResponseEntity<>(headers, HttpStatus.CREATED);

        } catch (IOException ioe) {
            log.error(ioe.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        Multimedia mediaToDelete = multimediaService.getMediaById(id);

        multimediaService.delete(mediaToDelete);
    }
}
