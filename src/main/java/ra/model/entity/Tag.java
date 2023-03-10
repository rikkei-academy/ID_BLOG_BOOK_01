package ra.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "TagId")
    private int tagId;
    @Column (name = "TagName")
    private String tagName;
    @Column(name= "TagStatus")
    private boolean tagStatus;
}
