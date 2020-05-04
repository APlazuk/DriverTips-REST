package pl.coderslab.drivertips.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
@Data
public class Tag extends BaseEntity{

    private String name;

    @ManyToMany(mappedBy = "tag")
    private Set<Tip> tips;
}
