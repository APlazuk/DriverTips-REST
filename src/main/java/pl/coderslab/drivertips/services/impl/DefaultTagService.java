package pl.coderslab.drivertips.services.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.drivertips.exceptions.TipNotFoundException;
import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.model.Tip;
import pl.coderslab.drivertips.repositories.TagRepository;
import pl.coderslab.drivertips.repositories.TipRepository;
import pl.coderslab.drivertips.services.TagService;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class DefaultTagService implements TagService {

    private final TagRepository tagRepository;
    private final TipRepository tipRepository;

    public DefaultTagService(TagRepository tagRepository, TipRepository tipRepository) {
        this.tagRepository = tagRepository;
        this.tipRepository = tipRepository;
    }

    @Override
    public List<Tag> getAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag createNewTag(Tag tag, Tip tip) {

        Optional<Tip> tipFromDB = tipRepository.getOne(tip);

        if (tipFromDB.isEmpty()){
            throw new TipNotFoundException(String.format("Porada o danym id: '%s' nie została znaleziona", tip.getId()));
        }

        Set<Tip> tips = new HashSet<>();
        tips.add(tipFromDB.get());

        Optional<Tag> tagFromDB = tagRepository.findTagByName(tag.getName());

        if (tagFromDB.isEmpty()) {

            Tag newTag = new Tag();

            newTag.setName(tag.getName());
            newTag.setTips(tips);

            return tagRepository.save(newTag);
        }

        return tagFromDB.get();
    }


}
