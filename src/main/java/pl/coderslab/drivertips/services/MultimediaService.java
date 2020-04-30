package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Multimedia;

import java.util.List;

public interface MultimediaService {
    Multimedia getMediaById(Long id);

    Multimedia addMedia(Multimedia uploadedMultimedia);

    void delete(Multimedia media);

    List<Multimedia> getAll();

    List<Multimedia> getMultimedia(List<Multimedia> multimedia);
}
