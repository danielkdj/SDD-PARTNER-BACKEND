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

	@Override
	public List<Ea> scheduleList(List<Long> categoryId) throws Exception {
		Long approve = 2L;
		List<Ea> uses = repository.findAllByCategoryItem_CategoryIdInAndApprovalStatus(Sort.by(Direction.DESC, "documentNo"), categoryId, approve);
		return uses;
	}

	@Override
	public List<Ea> fourList() throws Exception {
		List<Long> categoryId = new ArrayList<>();
		categoryId.add(12L);
		categoryId.add(13L);
		categoryId.add(14L);
		categoryId.add(15L);

		List<Ea> uses = repository.findTop4ByCategoryItem_CategoryIdIn(Sort.by(Direction.DESC, "documentNo"), categoryId);
		return uses;
	}
	@Override
	public List<Ea> categoryList(List<Long> categoryId) throws Exception {
		return repository.findAllByCategoryItem_CategoryIdIn(Sort.by(Direction.DESC, "documentNo"),categoryId);
	}

	@Override
	public List<Ea> searchList(List<Long> categoryId, List<Long> approve) throws Exception {
		return repository.findByCategoryItem_CategoryIdInAndApprovalStatusIn(Sort.by(Direction.DESC, "documentNo"), categoryId, approve);
	}
//	public List<Ea> searchListTitle(List<Long> categoryId, List<Long> approve, String title) throws Exception {
//		return repository.findByCategoryItem_CategoryIdInAndApprovalStatusInAndTitleContaining(Sort.by(Direction.DESC, "documentNo"), categoryId, approve, title);
//	}


}
