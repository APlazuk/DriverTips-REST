package pl.coderslab.drivertips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pl.coderslab.drivertips.domain.Tip;

import java.util.List;

@Repository
public interface TipRepository extends JpaRepository<Tip, Long> {

    @Query(value = "SELECT*FROM tip ORDER BY date DESC LIMIT 3;", nativeQuery = true)
    List<Tip> queryGetNewestTips();
}
