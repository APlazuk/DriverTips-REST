package pl.coderslab.drivertips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Tag;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
}
