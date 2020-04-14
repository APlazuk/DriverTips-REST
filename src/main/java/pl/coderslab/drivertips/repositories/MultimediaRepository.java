package pl.coderslab.drivertips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Multimedia;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Long> {
}
