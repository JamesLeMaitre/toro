package com.torocommunication.torofull.repo;

import com.torocommunication.torofull.entities.JobType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public  interface TypeJobRepo extends JpaRepository<JobType,Long> {

}