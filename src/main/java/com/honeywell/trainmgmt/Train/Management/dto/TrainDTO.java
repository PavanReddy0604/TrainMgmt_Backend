package com.honeywell.trainmgmt.Train.Management.dto;

import com.honeywell.trainmgmt.Train.Management.utility.Status;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TrainDTO {

    private long trainNumber;
    private String trainName;
    private LocalDateTime trainArrivalTime;
    private LocalDateTime trainDepartureTime;
    private Status status;
}
