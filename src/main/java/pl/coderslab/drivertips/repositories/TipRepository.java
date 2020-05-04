package pl.coderslab.drivertips.repositories;

import pl.coderslab.drivertips.model.Tip;

import java.util.List;
import java.util.Optional;

public interface TipRepository {

    List<Tip> findAll();

    List<Tip> queryGetNewestTips(Integer limit);

    List<Tip> queryGetTipsByName(String name);

    Optional<Tip> findById(Long id);

    Optional<Tip> getOne(Tip tip);

    Tip save(Tip tip);

    void deleteById(Long id);
}
