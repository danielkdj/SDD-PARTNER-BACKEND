package com.sdd.sddpartner.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name="ea")
public class Ea {

    @Id
    @Column(name = "document_no")
    private long documentNo;

    @Column(name = "approval_stats")
    private long approvalStats;
}
