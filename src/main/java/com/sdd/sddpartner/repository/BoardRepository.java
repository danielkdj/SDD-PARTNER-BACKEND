package com.sdd.sddpartner.repository;

import com.sdd.sddpartner.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
	
}
