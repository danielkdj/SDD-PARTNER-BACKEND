package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Completion;
import com.sdd.sddpartner.repository.ComRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ComServiceImpl implements ComService {

	private final ComRepository repository;

	@Override
	public void update(List<Long> coms) throws Exception {
		for(Long comNo : coms){
			Optional<Completion> opt = repository.findById(comNo);
			Completion entity = opt.orElseThrow(() -> new Exception("update No null"));
			if(entity.getCompletion()=='Y'){
				entity.setCompletion('N');
			}else{
				entity.setCompletion('Y');
			}
			repository.save(entity);
		}
	}

	@Override
	public List<Completion> searchList(Completion search) throws Exception {
		//메소드명 변경해야함
		return repository.findAllBySearch(Sort.by(Sort.Direction.DESC, "documentNo"), search.getEduId(), search.getYears(), search.getQuarters(), search.getCompletion());
	}
}
