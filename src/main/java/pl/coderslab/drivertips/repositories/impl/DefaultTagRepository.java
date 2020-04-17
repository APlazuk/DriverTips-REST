package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Tag;
import pl.coderslab.drivertips.repositories.TagRepository;


@Repository
interface DefaultTagRepository extends TagRepository, JpaRepository<Tag, Long> {
}
