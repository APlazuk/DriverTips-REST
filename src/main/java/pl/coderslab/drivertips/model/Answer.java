package pl.coderslab.drivertips.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "answers")
@ToString(exclude = {"multimedia"})
public class Answer extends BaseEntity {

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private Boolean correct;

    @OneToMany
    @JoinColumn(name = "answer_id")
    private List<Multimedia> multimedia;

    @ManyToOne
    private Question question;
}
