package com.sdd.sddpartner.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of="categoryId")
@ToString
@Entity
@Table(name="category_item")
public class CategoryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotBlank
    @Column(name = "CATEGORY_ID", nullable = false)
    private Long categoryId;

    @NotBlank
    @Column(length = 50, name="CATEGORY", nullable = false)
    private String category;

    @NotBlank
    @Column(length = 50, name="SUB_CATEGORY", nullable = false)
    private String subCategory;
}
