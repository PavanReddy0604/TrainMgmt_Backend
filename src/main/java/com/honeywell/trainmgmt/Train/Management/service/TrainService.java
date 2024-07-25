package com.honeywell.trainmgmt.Train.Management.service;

import com.honeywell.trainmgmt.Train.Management.dto.TrainDTO;

import java.util.List;

public interface TrainService {

    TrainDTO getTrainById(long id);
    List<TrainDTO> getAllTrains();
    TrainDTO saveTrain(TrainDTO trainDTO);
    TrainDTO updateTrain(TrainDTO trainDTO,long id);
    void deleteTrain(long id);

}
