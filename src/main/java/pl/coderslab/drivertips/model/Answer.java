package pl.coderslab.drivertips.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "answers")
public class Answer extends BaseEntity {

    @Column(nullable = false)
    private String text;
    @Column(nullable = false)
    private Boolean correct;

    @OneToMany
    @JoinColumn(name = "mutlimedia_id")
    private List<Multimedia> multimedia;
}
