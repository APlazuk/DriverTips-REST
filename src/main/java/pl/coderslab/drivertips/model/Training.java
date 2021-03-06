package pl.coderslab.drivertips.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter @Setter
@ToString(exclude = {"questions"})
public class Training extends BaseEntity {

    private String title;

    @OneToOne
    @JoinColumn(name = "tip_id")
    private Tip tip;

    @OneToMany(mappedBy = "training",cascade = CascadeType.ALL)
    private List<Question> questions;
}
