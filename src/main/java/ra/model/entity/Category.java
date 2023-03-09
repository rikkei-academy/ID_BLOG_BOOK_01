package ra.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CatalogId")
    private int catalogId;
    @Column(name = "CatalogName")
    private String catalogName;
    @Column(name = "CatalogStatus",columnDefinition = "nvarchar(100)")
    private boolean catalogStatus;
}
