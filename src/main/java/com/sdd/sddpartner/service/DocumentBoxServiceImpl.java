package com.sdd.sddpartner.service;

import com.sdd.sddpartner.domain.DocumentBox;
import com.sdd.sddpartner.domain.TeamCalendar;
import com.sdd.sddpartner.repository.DocumentBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DocumentBoxServiceImpl implements DocumentBoxService{

    private final DocumentBoxRepository documentBoxRepository;

    @Override
    public List<DocumentBox> list() throws Exception {
        return documentBoxRepository.findAll(Sort.by(Sort.Direction.DESC, "createAt"));
    }

    @Override
    public void remove(String documentId) throws Exception {
        documentBoxRepository.deleteById(documentId);
    }

    @Override
    public void write(DocumentBox documentBox) throws Exception {
        documentBox.setId(String.valueOf(UUID.randomUUID()));
        documentBoxRepository.save(documentBox);
    }

    @Override
    public void modify(DocumentBox documentBox) throws Exception {
        DocumentBox documentBoxEntity = documentBoxRepository.getReferenceById(documentBox.getId());
        documentBoxEntity.setTitle(documentBox.getTitle());
        documentBoxEntity.setContent(documentBox.getContent());
        documentBoxRepository.save(documentBoxEntity);
    }

    @Override
    public DocumentBox detail(String id) throws Exception {
        return documentBoxRepository.getReferenceById(id);
    }

}
