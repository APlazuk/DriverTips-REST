package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.repositories.TagRepository;

import java.util.Set;


@Repository
interface SqlTagRepository extends TagRepository, JpaRepository<Tag, Long> {




}
