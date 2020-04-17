package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Multimedia;
import pl.coderslab.drivertips.repositories.MultimediaRepository;

@Repository
interface DefaultMultimediaRepository extends MultimediaRepository, JpaRepository<Multimedia, Long> {
}
