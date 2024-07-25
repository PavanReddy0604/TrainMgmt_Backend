package com.honeywell.trainmgmt.Train.Management.entity;

import com.honeywell.trainmgmt.Train.Management.utility.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "train")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int trainId;
    private long trainNumber;
    private String trainName;
    private LocalDateTime trainArrivalTime;
    private LocalDateTime trainDepartureTime;
    private Status status;

}
