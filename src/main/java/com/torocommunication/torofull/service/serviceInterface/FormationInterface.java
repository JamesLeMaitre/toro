package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.Formation;

import java.util.List;

public interface FormationInterface {
    List<Formation> getAll();

    Formation create(Formation data);
    Formation edit(Long id,Formation data);
    Formation getByIds(Long id);

    void delete(Long id);

    int count();
}
