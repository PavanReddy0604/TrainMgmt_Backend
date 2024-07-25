package com.honeywell.trainmgmt.Train.Management.exception;

import com.honeywell.trainmgmt.Train.Management.entity.Train;

public class TrainNotFoundException extends RuntimeException{

    public TrainNotFoundException(String message){
        super(message);
    }

}
