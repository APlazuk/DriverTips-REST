package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.repositories.MultimediaRepository;

import java.util.Optional;

@Repository
interface SqlMultimediaRepository extends MultimediaRepository, JpaRepository<Multimedia, Long> {

}
