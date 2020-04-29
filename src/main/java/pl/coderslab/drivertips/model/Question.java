package pl.coderslab.drivertips.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "questions")
@ToString(exclude = {"training"})
public class Question extends BaseEntity {

    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private Integer points;

    @ManyToOne
    @JoinColumn(name = "training_id")
    private Training training;

    @OneToMany(mappedBy = "question",cascade = CascadeType.PERSIST)
    private List<Answer> answers;

    @OneToMany
    @JoinColumn(name = "mutlimedia_id")
    private List<Multimedia> multimedia;

}
