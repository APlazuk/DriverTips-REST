package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Multimedia;

import java.util.List;
import java.util.Optional;

public interface MultimediaRepository {
    Multimedia save(Multimedia multimedia);

    Optional<Multimedia> findMultimediaById(Long id);

    void delete(Long id);

    List<Multimedia> findAll();
}
