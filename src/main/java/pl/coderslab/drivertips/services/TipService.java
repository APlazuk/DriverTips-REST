package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.domain.Tip;

import java.util.List;

public interface TipService {

    List<Tip> newestTips(Integer limit);

    List<Tip> searchTip(String name);

    Tip findById(Long id);

    Tip createNewTip(Tip tip);

    Tip updateTip(Long id, Tip tip);


}
