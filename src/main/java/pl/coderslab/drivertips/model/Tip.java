package pl.coderslab.drivertips.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter @Setter
@ToString(exclude = {"multimedia","tags"})
public class Tip  extends BaseEntity{


    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate date;

    @OneToOne(mappedBy = "tip", cascade = CascadeType.ALL)
    private Training training;

    @OneToMany
    @JoinColumn(name = "tip_id")
    private List<Multimedia> multimedia;

    @ManyToMany(mappedBy = "tips")
    private Set<Tag> tags;

    @PrePersist
    public void PrePersist(){
        date = LocalDate.now();
    }

}
