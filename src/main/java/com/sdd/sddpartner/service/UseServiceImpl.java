package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.repository.UseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class UseServiceImpl implements UseService {

	private final UseRepository repository;

	@Override
	public Ea read(Long documentNo) throws Exception {
		return repository.getOne(documentNo);
	}

	@Override
	public void modify(Ea newEa) throws Exception {
		repository.findById(newEa.getDocumentNo())
				.map(ea -> {
					ea.setApprovalStatus(newEa.getApprovalStatus());
					return repository.save(ea);
				})
				.orElseGet(() -> repository.save(newEa));
	}

//	@Override
//	public List<Ea> uselist() throws Exception {
//		List<Ea> uses = repository.findAllByApprovalStageAndSecondApprovalAndCategoryId(Sort.by(Direction.DESC, "documentNo"), Long.valueOf(2),"E103", Long.valueOf(1));
//		List<Ea> cars  = repository.findAllByApprovalStageAndSecondApprovalAndCategoryId(Sort.by(Direction.DESC, "documentNo"), Long.valueOf(2),"E103", Long.valueOf(2));
//
//		for(Ea car : cars){
//			uses.add(car);
//		}
//
//		return uses;
//	}
	@Override
	public List<Ea> schedulelist(Long categoryId) throws Exception {
		List<Ea> uses = repository.findAllByCategoryIdAndCompletedAtNotNull(Sort.by(Direction.DESC, "documentNo"), categoryId);
		return uses;
	}
	@Override
	public List<Ea> fourlist() throws Exception {
		List<Long> categoryId = new ArrayList<>();
		categoryId.add(Long.valueOf(2));
		categoryId.add(Long.valueOf(1));

		List<Ea> uses = repository.findTop4ByCategoryIdIn(Sort.by(Direction.DESC, "documentNo"), categoryId);
		return uses;
	}
	@Override
	public List<Ea> categorylist(Long categoryId) throws Exception {
		return repository.findAllByCategoryId(Sort.by(Direction.DESC, "documentNo"),categoryId);
	}

	@Override
	public List<Ea> searchlist(String title) throws Exception {
		return repository.findByTitleContaining(Sort.by(Direction.DESC, "documentNo"),title);
	}

}
