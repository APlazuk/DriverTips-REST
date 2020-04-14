package pl.coderslab.drivertips.domain;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@ToString(exclude = {"multimedia","tags"})
public class Tip  extends BaseEntity{


    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private LocalDate date;

    @OneToOne(mappedBy = "tip")
    private Training training;

    @OneToMany
    @JoinColumn(name = "mutlimedia_id")
    private List<Multimedia> multimedia;

    @ManyToMany
    private List<Tag> tags;

}
