package pl.coderslab.drivertips.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
public class Question extends BaseEntity {

    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private Integer points;

    @OneToMany
    @JoinColumn(name = "answer_id")
    private List<Answer> answers;

    @OneToMany
    @JoinColumn(name = "mutlimedia_id")
    private List<Multimedia> multimedia;

}
