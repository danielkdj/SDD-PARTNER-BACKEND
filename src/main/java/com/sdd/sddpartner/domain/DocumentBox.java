package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Entity
@Table(name="document_box")
public class DocumentBox {
    @Id
    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @Column(name = "TITLE", length = 50)
    private String title;

    @Lob
    @Column(name = "CONTENT")
    private String content;


    @CreationTimestamp
    @Column(name = "CREATE_AT", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "MODIFIED_AT", nullable = false)
    private LocalDateTime modifiedAt;

    @Column(name = "EMP_ID", length = 50, nullable = false)
    private String employeeId;


}
