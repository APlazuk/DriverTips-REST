package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.coderslab.drivertips.model.Multimedia;
import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.exceptions.TipNotFoundException;
import pl.coderslab.drivertips.repositories.TagRepository;
import pl.coderslab.drivertips.repositories.TipRepository;
import pl.coderslab.drivertips.services.MultimediaService;
import pl.coderslab.drivertips.services.TagService;
import pl.coderslab.drivertips.services.TipService;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class DefaultTipService implements TipService {

    private final TipRepository tipRepository;
    private final MultimediaService multimediaService;
    private final TagService tagService;

    public DefaultTipService(TipRepository tipRepository, MultimediaService multimediaService, TagService tagService) {
        this.tipRepository = tipRepository;
        this.multimediaService = multimediaService;
        this.tagService = tagService;
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

        Set<Tag> tags = tip.getTags();
        Set<Tag> tagSet = tags.stream().map(tag -> tagService.createNewTag(tag,tip)).collect(Collectors.toSet());

        tip.setTags(tagSet);

        return tipRepository.save(tip);
    }

    @Override
    public Tip updateTip(Tip tip) {
        return tipRepository.save(tip);
    }

    @Override
    public void delete(Tip tip) {
        tipRepository.deleteById(tip.getId());
    }
}
