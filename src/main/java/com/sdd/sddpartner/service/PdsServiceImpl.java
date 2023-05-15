package com.sdd.sddpartner.service;

import java.util.ArrayList;
import java.util.List;

import com.sdd.sddpartner.domain.Pds;
import com.sdd.sddpartner.domain.PdsFile;
import com.sdd.sddpartner.repository.PdsFileRepository;
import com.sdd.sddpartner.repository.PdsRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class PdsServiceImpl implements PdsService {

	private final PdsRepository repository;
	
	private final PdsFileRepository pdsFileRepository;

	@Override
	public void register(Pds pds) throws Exception {
		Pds pdsEntity = new Pds();
		
		pdsEntity.setItemName(pds.getItemName());
		pdsEntity.setDescription(pds.getDescription());
		
		String[] files = pds.getFiles();

		if (files == null) {
			return;
		}

		for (String fileName : files) {
			PdsFile pdsFile = new PdsFile();
			pdsFile.setFullName(fileName);
			
			pdsEntity.addItemFile(pdsFile);
		}
	
		repository.save(pdsEntity);
		
		pds.setItemId(pdsEntity.getItemId());
	}

	@Override
	public Pds read(Long itemId) throws Exception {
		Pds pdsEntity = repository.getOne(itemId);
		Integer viewCnt = pdsEntity.getViewCnt();
		
		if(viewCnt == null) {
			viewCnt = 0;
		}
		
		pdsEntity.setViewCnt(viewCnt + 1);
		
		repository.save(pdsEntity);
		
		return repository.getOne(itemId);
	}

	@Override
	public void modify(Pds pds) throws Exception {
		Pds pdsEntity = repository.getOne(pds.getItemId());
		
		pdsEntity.setItemName(pds.getItemName());
		pdsEntity.setDescription(pds.getDescription());
		
		String[] files = pds.getFiles();
		
		if (files != null) {
			pdsEntity.clearItemFile();
			
			for (String fileName : files) {
				PdsFile pdsFile = new PdsFile();
				pdsFile.setFullName(fileName);
				
				pdsEntity.addItemFile(pdsFile);
			}
		}
		repository.save(pdsEntity);
	}

	@Override
	public void remove(Long itemId) throws Exception {
		repository.deleteById(itemId);
	}

	@Override
	public List<Pds> list() throws Exception {
		return repository.findAll(Sort.by(Direction.DESC, "itemId"));
	}

	@Override
	public List<String> getAttach(Long itemId) throws Exception {
		Pds pdsEntity = repository.getOne(itemId);
		
		List<PdsFile> pdsFiles = pdsEntity.getPdsFiles();
		
		List<String> attachList = new ArrayList<String>();
		for(PdsFile pdsFile : pdsFiles) {
			attachList.add(pdsFile.getFullName());
		}
		
		return attachList;
	}

	@Override
	public void updateAttachDownCnt(String fullName) throws Exception {
		List<PdsFile> pdsFileList = pdsFileRepository.findByFullName(fullName);
		
		if(pdsFileList.size() > 0) {
			PdsFile pdsFileEntity = pdsFileList.get(0);
			
			Integer downCnt = pdsFileEntity.getDownCnt();
			if(downCnt == null) {
				downCnt = 0;
			}
			pdsFileEntity.setDownCnt(downCnt + 1);
			
			pdsFileRepository.save(pdsFileEntity);
		}
	}
	
}
