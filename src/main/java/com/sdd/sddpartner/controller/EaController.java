package com.sdd.sddpartner.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.sdd.sddpartner.domain.Ea;
import com.sdd.sddpartner.dto.EaDto;
import com.sdd.sddpartner.service.EaService;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/ea")
public class EaController {

    private final EaService service;
    @GetMapping("/eaDetail/{documentNo}")
    public ResponseEntity<EaDto> read(@PathVariable Long documentNo) throws Exception {
        EaDto ea = service.read(documentNo);

        log.info("list---------------------------------------------------------");

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

    @GetMapping("/eaList")
    public ResponseEntity<List<Ea>> listEa() throws Exception {
        log.info("list");

        List<Ea> eaList = service.list();

        return new ResponseEntity<>(eaList, HttpStatus.OK);
    }

    @GetMapping("/eaApprovalList")
    public ResponseEntity<List<EaDto>> eaApprovalList() throws Exception {
        log.info("eaApprovalList");

        return new ResponseEntity<>(service.eaApprovallist(), HttpStatus.OK);
    }

    @PostMapping(value = "/createEa")
    public ResponseEntity<Ea> createEA(@Validated @RequestBody Ea ea
                                       ) throws Exception {
        service.register(ea);

        log.info("createEA");

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

    @PutMapping("/{documentNo}")
    public ResponseEntity<Ea> modify(@PathVariable("documentNo") Long documentNo,
                                     @Validated @RequestBody Ea ea) throws Exception {
        ea.setDocumentNo(documentNo);
        service.modify(ea);

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }


    @PutMapping("/eaApprovalStatus/{documentNo}")
    public ResponseEntity<Ea> modifyApprovalStage(@PathVariable("documentNo") Long documentNo,
                                                  @Validated @RequestBody Ea ea) throws Exception {
        ea.setApprovalStatus(ea.getApprovalStatus()  + 1);
        service.modify(ea);

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

//    @PutMapping("/eaApprovalStatus/{documentNo}")
//    public ResponseEntity<Ea> rejectApprovalStage(@PathVariable("documentNo") Long documentNo,
//                                                  @Validated @RequestBody Ea ea) throws Exception {
//        ea.setApprovalStatus(ea.getApprovalStatus()  + 2);
//        service.modify(ea);
//
//        return new ResponseEntity<>(ea, HttpStatus.OK);
//    }
}
