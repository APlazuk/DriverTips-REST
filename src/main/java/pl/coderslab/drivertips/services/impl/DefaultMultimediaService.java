package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.drivertips.exceptions.MultimediaNotFoundException;
import pl.coderslab.drivertips.exceptions.TipNotFoundException;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.repositories.MultimediaRepository;
import pl.coderslab.drivertips.services.MultimediaService;

import java.util.List;
import java.util.Optional;

@Service
public class DefaultMultimediaService implements MultimediaService {

    private final MultimediaRepository multimediaRepository;

    public DefaultMultimediaService(MultimediaRepository multimediaRepository) {
        this.multimediaRepository = multimediaRepository;
    }

    @Override
    public List<Multimedia> getAll() {
        return multimediaRepository.findAll();
    }

    @Override
    public Multimedia getMediaById(Long id) {
        Optional<Multimedia> multimedia = multimediaRepository.findMultimediaById(id);

        if (multimedia.isEmpty()) {
            throw new MultimediaNotFoundException(String.format("Multimedium o danym id: %s nie istnieje", id));
        }

        return multimedia.get();
    }

    @Override
    public Multimedia addMedia(Multimedia uploadedMultimedia) {
        return multimediaRepository.save(uploadedMultimedia);
    }

    @Override
    public void delete(Multimedia media) {
        multimediaRepository.deleteById(media.getId());
    }

}
