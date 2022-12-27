package com.hannah.kafka.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CcsVehicleMessage {

    private String ccspId;

    private String vin;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime ccsStartdate;

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyyMMddHHmmss")
    private LocalDateTime ccsEnddate;

    private String mdlCd;

    private String mdlNm;

    private String mdlyCd;

    private String mdlyNm;

    private String fsc;

    private String plateNo;

    private String extColCd;

    private String extColNm;

    private String intColCd;

    private String intColNm;

    private String fuelType;

    private String brand;

    private String bodytype;

    private String provStatus;

    private String sharingStatus;

    private String status;
}
