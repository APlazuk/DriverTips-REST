package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Tag;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TagRepository {
    List<Tag> findAll();

    Optional<Tag> findTagById(Long id);

    Optional<Tag> findTagByName(String name);

    Tag save(Tag tag);
}
