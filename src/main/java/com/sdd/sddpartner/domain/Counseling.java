package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "COUNSELING")  // DB 테이블 이름
@Entity
public class Counseling {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long counId;

    @Column(name = "coun_title")
    private String counTitle;

    @Column(name = "coun_content")
    private String counContent;

    @Column(name = "coun_answer")
    private String counAnswer;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "request_at")
    private LocalDate requestAt;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "requested_at")
    private LocalDate requestedAt;

    @JsonFormat(pattern="yyyy-MM-dd")
    @Column(name = "coun_at")
    private LocalDate counAt;

    public void updateFrom(Counseling other) {
        if (other == null) {
            throw new IllegalArgumentException("상담 수정 내용이 없습니다.");
        }
        if (other.getCounTitle() != null) {
            setCounTitle(other.getCounTitle());
        }
        if (other.getCounContent() != null) {
            setCounContent(other.getCounContent());
        }
        if (other.getCounAnswer() != null) {
            setCounAnswer(other.getCounAnswer());
        }
        if (other.getRequestAt() != null) {
            setRequestAt(other.getRequestAt());
        }
        if (other.getRequestedAt() != null) {
            setRequestedAt(other.getRequestedAt());
        }
        if (other.getCounAt() != null) {
            setCounAt(other.getCounAt());
        }
    }
}
