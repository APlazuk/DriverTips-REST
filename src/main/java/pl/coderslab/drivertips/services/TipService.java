package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.domain.Tip;

import java.util.List;

public interface TipService {
    List<Tip> newestTips(Integer limit);

    Tip findById(Long id);

    Tip save(Tip tip);

    Tip createNewTip(Tip tip);
}
