package com.honeywell.trainmgmt.Train.Management.repository;

import com.honeywell.trainmgmt.Train.Management.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainRepository extends JpaRepository<Train,Long> {


}
