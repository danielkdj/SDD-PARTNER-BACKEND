package com.sdd.sddpartner.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

//affair 검색용 dto
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SearchCategoryDate {

    private String category;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate start;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate end;

    //시작일에 00:00:00.00000000 추가해 LocalDateTime 반환
    public LocalDateTime getStartDateTime() {
        return this.start.atStartOfDay();
    }
    //종료일에 23:59:59.999999을 추가해 LocalDateTime 반환
    public LocalDateTime getEndDateTime() {
        return this.end.atTime(LocalTime.MAX);
    }


}
