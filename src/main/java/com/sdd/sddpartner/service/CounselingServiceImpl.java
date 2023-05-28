package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Counseling;
import com.sdd.sddpartner.dto.CounselingDto;
import com.sdd.sddpartner.repository.CounselingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
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
		return repository.getReferenceById(counId);
	}

	@Override
	public List<Counseling> findByTitle(String title) throws Exception {
		return repository.findByTitle(title);
	}

	@Override
	public List<CounselingDto> list() throws Exception {
		List<Counseling> coun = repository.findAllByOrderByCounIdDesc();

		return coun.stream()
				.map(CounselingDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional
	public void delete(Long counId) throws Exception {
		repository.deleteById(counId);
	}
}
