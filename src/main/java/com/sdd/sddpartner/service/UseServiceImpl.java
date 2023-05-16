package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.domain.SearchCategoryDate;
import com.sdd.sddpartner.repository.UseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UseServiceImpl implements UseService {

	private final UseRepository repository;

	@Override
	public Ea select(Long documentNo) throws Exception {
		Optional<Ea> opt = repository.findById(documentNo);
		return opt.orElseThrow(() -> new Exception("update No null"));

	}

	@Override
	public void update(Ea ea) throws Exception {
		Optional<Ea> opt = repository.findById(ea.getDocumentNo());
		Ea entity = opt.orElseThrow(() -> new Exception("update No null"));
		entity.setApprovalStats(ea.getApprovalStats());

		repository.save(entity);
	}

	@Override
	public List<Ea> categoryList(long category) throws Exception {
		return repository.findAllByCategory(Sort.by(Direction.DESC, "documentNo"),category);
	}

	@Override
	public List<Ea> searchList(SearchCategoryDate search) throws Exception {
		return repository.findByDocumentNoAndEaStartGreaterThanEqualAndEaEndLessThanEqual(Sort.by(Direction.DESC, "documentNo"), search.getCategory(), search.getStartDateTime(), search.getEndDateTime());
	}

}
