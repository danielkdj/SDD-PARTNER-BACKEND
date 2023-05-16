package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EaRepository extends JpaRepository<Ea, Long> {
    public List<Ea> findByUserId(String userId);
}
