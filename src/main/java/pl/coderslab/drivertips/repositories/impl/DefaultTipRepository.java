package pl.coderslab.drivertips.repositories.impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Tip;
import pl.coderslab.drivertips.repositories.TipRepository;

import java.util.List;


@Repository
interface DefaultTipRepository extends TipRepository, JpaRepository<Tip, Long> {

    @Override
    @Query(value = "SELECT*FROM tip ORDER BY date DESC LIMIT ?;", nativeQuery = true)
    List<Tip> queryGetNewestTips(Integer limit);


    @Override
    @Query(value = "SELECT * FROM tip WHERE title LIKE %?1%", nativeQuery = true)
    List<Tip> queryGetTipsByName(String name);
}
