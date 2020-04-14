package pl.coderslab.drivertips.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
@Data
public class Training extends BaseEntity {

    private String title;

    @OneToOne
    @JoinColumn(name = "tip_id")
    private Tip tip;

    @OneToMany
    @JoinColumn(name = "question_id")
    private List<Question> questions;
}
