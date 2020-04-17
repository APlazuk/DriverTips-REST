package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Multimedia;

public interface MultimediaService {
    Multimedia getMediaById(Long id);

    Multimedia addMedia(Multimedia uploadedMultimedia);
}
