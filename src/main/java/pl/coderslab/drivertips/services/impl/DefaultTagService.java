package pl.coderslab.drivertips.services.impl;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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

        Set<Tip> tips = new HashSet<>();
        tips.add(tip);

        Optional<Tag> tagFromDB = tagRepository.findTagByName(tag.getName());

        if (tagFromDB.isEmpty()) {

            Tag newTag = new Tag();

            newTag.setName(tag.getName());
            newTag.setTips(tips);

            return tagRepository.save(newTag);
        }

        return tagFromDB.get();
    }

    @Override
    public Tag findTagById(Long id) {
        Optional<Tag> tagFromDB = tagRepository.findTagById(id);

        if (tagFromDB.isEmpty()){
            throw new ResourceNotFoundException(String.format("Tag o danym id: '%s' nie został znaleziony", id));
        }

        return tagFromDB.get();
    }

    @Override
    public Tag updateTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public void delete(Long id) {
        Tag tag = findTagById(id);

        tagRepository.delete(tag);
    }
}
