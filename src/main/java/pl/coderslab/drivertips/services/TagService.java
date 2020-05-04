package pl.coderslab.drivertips.services;

import pl.coderslab.drivertips.model.Tag;
import pl.coderslab.drivertips.model.Tip;

import java.util.List;

public interface TagService {
    List<Tag> getAllTags();

    Tag createNewTag(Tag tag, Tip tip);
}
