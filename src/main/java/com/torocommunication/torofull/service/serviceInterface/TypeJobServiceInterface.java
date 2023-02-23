package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.JobType;

import java.util.List;


public interface TypeJobServiceInterface {

    JobType create(JobType jobType);

    List<JobType> getAll();
}
