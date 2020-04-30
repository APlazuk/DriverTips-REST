package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.drivertips.exceptions.MultimediaAlreadyInUseException;
import pl.coderslab.drivertips.exceptions.MultimediaNotFoundException;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.repositories.MultimediaRepository;
import pl.coderslab.drivertips.services.MultimediaService;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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

    @Override
    public List<Multimedia> getMultimedia(List<Multimedia> multimedia) {
        ListIterator<Multimedia> multimediaListIterator = multimedia.listIterator();

        List<Multimedia> media = new ArrayList<>();
        if (multimediaListIterator.hasNext()) {
            Multimedia next = multimediaListIterator.next();
            Optional<Multimedia> mediaFromDB = multimediaRepository.findMultimediaById(next.getId());

            if (mediaFromDB.isPresent()) {
                media.add(mediaFromDB.get());
            }else {
                throw new MultimediaNotFoundException(String.format("Medium o danym id: '%s' nie zostało znalezione", next.getId()));
            }
            if (!multimediaRepository.isMediaAlreadyInUse(mediaFromDB.get().getId())) {
                throw new MultimediaAlreadyInUseException(String.format("Medium o danym id: '%s' jest już używane przez inny obiekt", mediaFromDB.get().getId()));
            }
        }
        return media;
    }
}
