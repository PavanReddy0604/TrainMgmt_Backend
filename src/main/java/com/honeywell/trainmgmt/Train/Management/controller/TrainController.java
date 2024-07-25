package com.honeywell.trainmgmt.Train.Management.controller;


import com.honeywell.trainmgmt.Train.Management.dto.TrainDTO;
import com.honeywell.trainmgmt.Train.Management.exception.TrainNotFoundException;
import com.honeywell.trainmgmt.Train.Management.service.TrainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "train")
@Tag(name = "Train Management", description = "api doc for Train management project")
@CrossOrigin(origins = "http://localhost:8080")
public class TrainController {

    private final TrainService trainService;
    Logger log = LoggerFactory.getLogger(TrainController.class);

    public TrainController(TrainService trainService) {
        this.trainService = trainService;
    }


    @Operation(summary = "Get Train by id", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<TrainDTO> fetchTrain(@PathVariable("id") int id) throws TrainNotFoundException {
        log.info("Request received to get Train having id {} ", id);
        return new ResponseEntity<>(trainService.getTrainById(id), HttpStatus.OK);
    }

    @Operation(summary = "Save Train", responses = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping(value = "/", consumes = "application/json")
    public ResponseEntity<TrainDTO> saveTrain(@RequestBody TrainDTO trainDTO) {
        log.info("Request received to save Train");
        return new ResponseEntity<>(trainService.saveTrain(trainDTO), HttpStatus.CREATED);
    }

    @Operation(summary = "Get all Trains", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<TrainDTO>> fetchAllTrains() {
        log.info("Request received to fetch all trains");
        return new ResponseEntity<>(trainService.getAllTrains(), HttpStatus.OK);
    }

    @Operation(summary = "Update Train", responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping(value = "/", consumes = "application/json")
    public ResponseEntity<TrainDTO> updateTrain(@RequestBody TrainDTO trainDTO,
                                                @RequestParam(name = "id", required = false) long id) throws TrainNotFoundException {
        log.info("Request received to update Train with id {}", trainDTO.getTrainNumber());
        return new ResponseEntity<>(trainService.updateTrain(trainDTO, id), HttpStatus.OK);
    }

    @Operation(summary = "Delete Train by id", responses = {
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable("id") long id) throws TrainNotFoundException {
        log.info("Request received to delete Train with id {} ", id);
        trainService.deleteTrain(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
