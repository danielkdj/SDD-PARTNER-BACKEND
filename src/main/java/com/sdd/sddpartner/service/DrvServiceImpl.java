package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.Drv;
import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.repository.DrvRepository;
import com.sdd.sddpartner.repository.UseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class DrvServiceImpl implements DrvService {

	private final DrvRepository repository;
	private final UseRepository useRepository;

	@Override
	public Drv register(Long documentNo) throws Exception {
		Ea ea = useRepository.getOne(documentNo);
		log.info("유저정보 출력"+ ea);
		Drv drv = new Drv(ea);
		return repository.save(drv);
	}

	@Override
	public Drv read(Long documentNo) throws Exception {
		return repository.getOne(documentNo);
	}

	@Override
	public List<Drv> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "drvStart"));
	}

	@Override
	public List<Drv> searchList(String car) throws Exception {
		return repository.findByCar(Sort.by(Direction.DESC, "drvStart"),car);
	}

	@Override
	public void modify(Drv newDrv) throws Exception {
		repository.findById(newDrv.getDrvNo())
				.map(drv -> {
					drv.setDrvReturn(newDrv.getDrvReturn());
					drv.setBeforeMileage(newDrv.getBeforeMileage());
					drv.setAfterMileage(newDrv.getAfterMileage());
					return repository.save(drv);
				})
				.orElseGet(() -> repository.save(newDrv));
	}

	@Override
	public void remove(Long drvNo) throws Exception {
		repository.deleteById(drvNo);
	}

}
