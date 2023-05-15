package com.sdd.sddpartner.repository;

import java.util.List;

import com.sdd.sddpartner.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	public List<Member> findByUserId(String userId);
	
	@Query("SELECT m.userNo, m.userId, m.userPw, m.userName, m.regDate "
			+ "FROM Member m "
			+ "ORDER BY m.regDate DESC")
	public List<Object[]> listAllMember();
	
}
