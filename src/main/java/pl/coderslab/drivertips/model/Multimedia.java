package pl.coderslab.drivertips.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class Multimedia extends BaseEntity{


    private String name;
    @Column(nullable = false)
    private String contentType;
    @Lob
    @Column(columnDefinition = "MEDIUMBLOB")
    private byte [] content;

    @CreatedDate
    private LocalDateTime createdOn;

    @CreatedBy
    private String createdBy;

    @LastModifiedDate
    private LocalDateTime updatedOn;

    @LastModifiedBy
    private String updatedBy;


    @PrePersist
    public void PrePersist(){
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void PreUpdate(){
        updatedOn = LocalDateTime.now();
    }
}
