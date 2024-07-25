package com.honeywell.trainmgmt.Train.Management.service.impl;

import com.honeywell.trainmgmt.Train.Management.dto.TrainDTO;
import com.honeywell.trainmgmt.Train.Management.entity.Train;
import com.honeywell.trainmgmt.Train.Management.exception.TrainNotFoundException;
import com.honeywell.trainmgmt.Train.Management.repository.TrainRepository;
import com.honeywell.trainmgmt.Train.Management.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TrainServiceImpl implements TrainService {

    private final TrainRepository trainRepository;
    Logger log = LoggerFactory.getLogger(TrainServiceImpl.class);

    public TrainServiceImpl(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
    }


    @Override
    public TrainDTO getTrainById(long id) {
        TrainDTO trainDTO = null;
        try {
            Train train = trainRepository.findById(id).orElseThrow(() -> new TrainNotFoundException("employee with id " + id + " not found"));
            BeanUtils.copyProperties(train, trainDTO);
        } catch (TrainNotFoundException e) {
            log.error("Train not found with id {} ", id);
            throw e;
        } catch (Exception exception) {
            log.error("Exception occurred while fetching the Train with id {}", id);
        }
        return trainDTO;
    }

    @Override
    public List<TrainDTO> getAllTrains() {
        List<Train> trains = trainRepository.findAll();
        List<TrainDTO> trainDTOList = new ArrayList<>();
        for (Train train : trains) {
            TrainDTO trainDTO = new TrainDTO();
            BeanUtils.copyProperties(train, trainDTO);
            trainDTOList.add(trainDTO);
        }
        return trainDTOList;
    }

    @Override
    public TrainDTO saveTrain(TrainDTO trainDTO) {
        try {
            Train train = new Train();
            BeanUtils.copyProperties(trainDTO, train);
            trainRepository.save(train);
        } catch (Exception e) {
            log.error("Exception occurred while fetching the Train with number {}", trainDTO.getTrainName());
            throw new TrainNotFoundException("Train not found");
        }
        return trainDTO;
    }

    @Override
    public TrainDTO updateTrain(TrainDTO trainDTO, long id) {
        TrainDTO trainDTO1 = new TrainDTO();
        try {
            Optional<Train> trainOptional = trainRepository.findById(id);
            if (trainOptional.isPresent()) {
                Train savedTrain = trainOptional.get();
                savedTrain.setTrainNumber(trainDTO.getTrainNumber());
                savedTrain.setTrainName(trainDTO.getTrainName());
                savedTrain.setTrainArrivalTime(trainDTO.getTrainArrivalTime());
                savedTrain.setTrainDepartureTime(trainDTO.getTrainDepartureTime());
                savedTrain.setStatus(trainDTO.getStatus());
                BeanUtils.copyProperties(savedTrain, trainDTO1);
            } else {
                throw new TrainNotFoundException("Train with name " + trainDTO.getTrainName() + " not found");
            }
        } catch (Exception e) {
            log.error("Exception occurred while updating the train");
            throw e;
        }
        return trainDTO1;
    }

    @Override
    public void deleteTrain(long id) {
        try {
            Optional<Train> trainOptional = trainRepository.findById(id);
            if (trainOptional.isPresent()) {
                trainRepository.deleteById(id);
            } else {
                throw new TrainNotFoundException("Train with id " + id + " not found");
            }
        } catch (Exception e) {
            throw e;
        }
    }
}

