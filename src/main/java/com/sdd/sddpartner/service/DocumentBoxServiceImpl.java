package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.DocumentBox;
import com.sdd.sddpartner.repository.DocumentBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DocumentBoxServiceImpl implements DocumentBoxService{

    private final DocumentBoxRepository documentBoxRepository;

    @Override
    public List<DocumentBox> list() throws Exception {
        return documentBoxRepository.findAll(/*Sort.by(Sort.Direction.DESC, "createAt")*/);
    }

    @Override
    public void remove(Long documentId) throws Exception {
        documentBoxRepository.deleteById(documentId);
    }
}
