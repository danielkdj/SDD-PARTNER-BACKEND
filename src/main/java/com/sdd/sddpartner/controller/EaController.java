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
    public ResponseEntity<Ea> read(@PathVariable Long documentNo) throws Exception {
        Ea ea = service.read(documentNo);

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

        return new ResponseEntity<>(service.eaApprovalList(), HttpStatus.OK);
    }

    @GetMapping("/eaAttendanceList")
    public ResponseEntity<List<EaDto>> eaAttendanceList() throws Exception {
        log.info("eaAttendanceList");

        return new ResponseEntity<>(service.eaAttendanceList(), HttpStatus.OK);
    }
    @GetMapping("/eaVacationList")
    public ResponseEntity<List<EaDto>> eaVacationList() throws Exception {
        log.info("eaVacationList");

        return new ResponseEntity<>(service.eaVacationList(), HttpStatus.OK);
    }

    @GetMapping("/eaAffairList")
    public ResponseEntity<List<EaDto>> eaAffairList() throws Exception {
        log.info("eaAffairList");

        return new ResponseEntity<>(service.eaAffairList(), HttpStatus.OK);
    }

    @GetMapping("/eaHRList")
    public ResponseEntity<List<EaDto>> eaHRList() throws Exception {
        log.info("eaHRList");

        return new ResponseEntity<>(service.eaHRList(), HttpStatus.OK);
    }

    @PostMapping(value = "/createEa")
    public ResponseEntity<Ea> createEA(@Validated @RequestBody Ea ea
                                       ) throws Exception {
        service.register(ea);

        log.info("createEA");

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

//    @PutMapping("/{documentNo}")
//    public ResponseEntity<Ea> modify(@PathVariable("documentNo") Long documentNo,
//                                     @Validated @RequestBody Ea ea) throws Exception {
//        ea.setDocumentNo(documentNo);
//        service.modify(ea);
//
//        return new ResponseEntity<>(ea, HttpStatus.OK);
//    }

    @PutMapping("/eaApprovalStatus/{documentNo}")
    public ResponseEntity<Ea> modifyApprovalStatus(@PathVariable("documentNo") Long documentNo,
                                                  @Validated @RequestBody Ea ea) throws Exception {
        ea.setDocumentNo(documentNo);
        service.modifyApproval(ea);

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }

    @PutMapping("/eaRejectStatus/{documentNo}")
    public ResponseEntity<Ea> modifyApprovalReject(@PathVariable("documentNo") Long documentNo,
                                                   @Validated @RequestBody Ea ea) throws Exception {
        ea.setDocumentNo(documentNo);
        service.modifyReject(ea);

        return new ResponseEntity<>(ea, HttpStatus.OK);
    }



}
