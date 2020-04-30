package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.exceptions.TipNotFoundException;
import pl.coderslab.drivertips.repositories.TipRepository;
import pl.coderslab.drivertips.services.MultimediaService;
import pl.coderslab.drivertips.services.TipService;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DefaultTipService implements TipService {

    private final TipRepository tipRepository;
    private final MultimediaService multimediaService;

    public DefaultTipService(TipRepository tipRepository, MultimediaService multimediaService) {
        this.tipRepository = tipRepository;
        this.multimediaService = multimediaService;
    }

    @Override
    public List<Tip> getAll() {
        return tipRepository.findAll();
    }

    @Override
    public Tip findById(Long id) {
        Optional<Tip> requestedTip = tipRepository.findById(id);

        if (requestedTip.isEmpty()) {
            throw new TipNotFoundException(String.format("Porada o danym id: '%s' nie została znaleziona", id));
        }

        return requestedTip.get();
    }

    @Override
    public List<Tip> newestTips(Integer limit) {
        return tipRepository.queryGetNewestTips(limit);
    }

    @Override
    public List<Tip> searchTip(String name) {
        List<Tip> tipsByName = tipRepository.queryGetTipsByName(name);

        if (tipsByName.isEmpty()) {
            throw new TipNotFoundException(String.format("Porada o danym tytule: '%s' nie została znaleziona", name));
        }
        return tipsByName;
    }

    @Override
    public Tip createNewTip(Tip tip) {
        List<Multimedia> multimedia = tip.getMultimedia();
        List<Multimedia> media = multimediaService.getMultimedia(multimedia);

        tip.setMultimedia(media);

        return tipRepository.save(tip);
    }

    @Override
    public Tip updateTip(Long id, Tip tip) {
        Optional<Tip> tipFromDatabase = tipRepository.findById(id);

        if (tipFromDatabase.isEmpty()) {
            throw new TipNotFoundException(String.format("Porada o danym id: '%s' nie została znaleziona", id));
        }
        return tipFromDatabase.get();
    }

    @Override
    public void delete(Tip tip) {
        tipRepository.deleteById(tip.getId());
    }
}
