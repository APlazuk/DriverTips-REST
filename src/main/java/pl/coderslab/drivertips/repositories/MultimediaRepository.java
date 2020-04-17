package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Multimedia;

import java.util.Optional;

public interface MultimediaRepository {
    Optional<Multimedia> findMultimediaById(Long id);

    Multimedia save(Multimedia multimedia);
}
