package com.torocommunication.torofull.service;


import com.torocommunication.torofull.entities.JobType;
import com.torocommunication.torofull.repo.TypeJobRepo;
import com.torocommunication.torofull.service.serviceInterface.TypeJobServiceInterface;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TypeJobServiceImpl implements TypeJobServiceInterface {


    private  final TypeJobRepo typeJobRepo;

    public TypeJobServiceImpl(TypeJobRepo typeJobRepo) {
        this.typeJobRepo = typeJobRepo;
    }


    @Override
    public JobType create(JobType jobType) {
        return typeJobRepo.save(jobType);
    }

    @Override
    public List<JobType> getAll() {
        return typeJobRepo.findAll();
    }


}
