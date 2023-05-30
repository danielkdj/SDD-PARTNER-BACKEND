package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Counseling;
import com.sdd.sddpartner.dto.CounselingDto;
import com.sdd.sddpartner.repository.CounselingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class CounselingServiceImpl implements CounselingService {

	private final CounselingRepository repository;

	@Override
	@Transactional
	public Counseling save(Counseling coun) throws Exception {
		return repository.save(coun);
	}

	@Override
	public Counseling read(Long counId) throws Exception {
		return repository.getReferenceById(counId);
	}

	@Override
	@Transactional
	public Counseling update(Long counId, Counseling coun) throws Exception {
		Counseling existingCoun = repository.getReferenceById(counId);
		existingCoun.setCounAnswer(coun.getCounAnswer());
		existingCoun.setCounAt(LocalDate.now());
		return repository.save(existingCoun);
	}

	@Override
	public List<Counseling> findByTitle(String title) throws Exception {
		return repository.findByTitle(title);
	}

	@Override
	public List<CounselingDto> list() {
		List<Counseling> counselingList = repository.findAll();
		List<CounselingDto> counselingDtoList = new ArrayList<>();

		for (Counseling counseling : counselingList) {
			counselingDtoList.add(CounselingDto.fromEntity(counseling));
		}
		return counselingDtoList;
	}

	@Override
	@Transactional
	public void delete(Long counId) throws Exception {
		repository.deleteById(counId);
	}
}
