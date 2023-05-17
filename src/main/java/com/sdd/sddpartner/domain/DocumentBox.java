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
@EqualsAndHashCode(of="documentId")
public class DocumentBox {

    @Id
    @Column(name = "DOCUMENT_ID")
    private Long documentId;
//    @Column
//    private String backgroundcolor;
//    @Lob
//    private String content;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @CreationTimestamp
//    private LocalDateTime createAt;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    @UpdateTimestamp
//    private LocalDateTime modifiedAt;
//    @Column
//    private String description;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime start;
//    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//    private LocalDateTime end;
    @Column
    private String title;
//    @Column
//    private String url;

//    private Number deptNo;

}
