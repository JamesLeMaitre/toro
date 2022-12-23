package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.TypeUEA;


import java.util.List;

public interface TypeUEAInterface {
    List<TypeUEA> getAll();

    TypeUEA create(TypeUEA data);
    TypeUEA edit(Long id,TypeUEA data);
    TypeUEA getByIds(Long id);

    void delete(Long id);

    int count();
}
