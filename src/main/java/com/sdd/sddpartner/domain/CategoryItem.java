package com.sdd.sddpartner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@JsonIgnoreProperties(value="hibernateLazyInitializer")
@Getter
@Setter
@EqualsAndHashCode(of="categoryId")
@ToString
@Entity
@Table(name="category_item")
public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @Column(length = 50, name="CATEGORY", nullable = false)
    private String category;
    @Column(length = 50, name="SUB_CATEGORY", nullable = false)
    private String subCategory;
}
