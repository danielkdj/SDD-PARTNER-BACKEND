package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.repository.UseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
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
	public List<Ea> fourList() throws Exception {
		List<Long> categoryId = Arrays.asList(12L,13L,14L,15L);
		List<Ea> uses = repository.findTop4ByCategoryItem_CategoryIdIn(
				Sort.by(Direction.DESC, "documentNo"), categoryId);
		return uses;
	}

	@Override
	public Long mainCount(Long holdApprove, List<Long> categoryIdList) {
		return repository.countByApprovalStatusAndCategoryItem_CategoryIdIn(
				holdApprove, categoryIdList);
	}
	@Override
	public Ea mainDate(List<Long> categoryIdList, Long approve) {
		return repository.findTopByCategoryItem_CategoryIdInAndApprovalStatus(
				Sort.by(Direction.ASC, "createdAt"), categoryIdList, approve);
	}

	@Override
	public List<Ea> categoryList(List<Long> categoryId) throws Exception {
		return repository.findAllByCategoryItem_CategoryIdIn(Sort.by(Direction.DESC, "documentNo"),categoryId);
	}

	@Override
	public List<Ea> scheduleList(List<Long> categoryId) throws Exception {
		Long approve = 2L;
		List<Ea> uses = repository.findAllByCategoryItem_CategoryIdInAndApprovalStatus(Sort.by(Direction.DESC, "documentNo"), categoryId, approve);
		return uses;
	}

	@Override
	public List<Ea> searchList(List<Long> categoryId, List<Long> approve) throws Exception {
		return repository.findByCategoryItem_CategoryIdInAndApprovalStatusIn(Sort.by(Direction.DESC, "documentNo"), categoryId, approve);
	}

	@Override
	public void modify(Long documentNo, Long approve) throws Exception {
		repository.findById(documentNo)
				.map(ea -> {
					ea.setApprovalStatus(approve);
					return repository.save(ea);
				})
				.orElseThrow();
	}
	@Override
	public void modifyAndCreate(Long documentNo, Long approve) throws Exception {
		repository.findById(documentNo)
				.map(ea -> {
					ea.setApprovalStatus(approve);
					return repository.save(ea);
				})
				.orElseThrow();
	}
}
