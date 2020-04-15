package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.domain.Tip;
import pl.coderslab.drivertips.exceptions.TipNotFoundException;
import pl.coderslab.drivertips.repositories.TipRepository;
import pl.coderslab.drivertips.services.TipService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultTipService implements TipService {

    private final TipRepository tipRepository;

    public DefaultTipService(TipRepository tipRepository) {
        this.tipRepository = tipRepository;
    }

    @Override
    public List<Tip> newestTips(Integer limit) {
        return tipRepository.queryGetNewestTips(limit);
    }

    @Override
    public Tip findById(Long id) {
        Optional<Tip> requestedTip = tipRepository.findById(id);

        if (requestedTip.isEmpty()){
            throw new TipNotFoundException(String.format("Porada o danym id: '%s' nie została znaleziona", id));
        }

        return requestedTip.get();
    }

    @Override
    public Tip save(Tip tip) {
        return null;
    }

    @Override
    public Tip createNewTip(Tip tip) {

        return tipRepository.save(tip);
    }


}
