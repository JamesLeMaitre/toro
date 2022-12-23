package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.DetailSA;


import java.util.List;

public interface DetailSAInterface {
    List<DetailSA> getAll();

    DetailSA create(DetailSA data);
    DetailSA edit(Long id,DetailSA data);
    DetailSA getByIds(Long id);

    void delete(Long id);

    int count();
}
