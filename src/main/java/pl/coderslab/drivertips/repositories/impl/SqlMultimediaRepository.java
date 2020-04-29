package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.repositories.MultimediaRepository;

@Repository
interface SqlMultimediaRepository extends MultimediaRepository, JpaRepository<Multimedia, Long> {

    @Override
    @Query(value = "SELECT IF((tip_id or question_id or answer_id) is null, 'true', 'false') FROM multimedia WHERE id =?", nativeQuery = true)
    boolean isMediaAlreadyInUse(Long id);
}
