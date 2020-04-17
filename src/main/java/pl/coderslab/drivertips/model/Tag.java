package pl.coderslab.drivertips.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
public class Tag extends BaseEntity{

    private String name;

    @ManyToOne
    private Tip tip;
}