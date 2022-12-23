package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.TypeUEA;
import com.torocommunication.torofull.repo.TypeUEARepository;
import com.torocommunication.torofull.service.serviceInterface.TypeUEAInterface;

import java.util.List;

public class TypeUEAService implements TypeUEAInterface {

    private final TypeUEARepository typeUEARepository;

    public TypeUEAService(TypeUEARepository typeUEARepository) {
        this.typeUEARepository = typeUEARepository;
    }

    @Override
    public List<TypeUEA> getAll() {
        return typeUEARepository.findAll();
    }

    @Override
    public TypeUEA create(TypeUEA data) {
        return typeUEARepository.save(data);
    }

    @Override
    public TypeUEA edit(Long id, TypeUEA data) {
        TypeUEA typeUEA = new TypeUEA();
        data = getByIds(id);
        typeUEA.setId(data.getId());
        typeUEA.setLibelle(data.getLibelle());
        return typeUEARepository.save(typeUEA);
    }

    @Override
    public TypeUEA getByIds(Long id) {
        return typeUEARepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        typeUEARepository.deleteById(id);
    }

    @Override
    public int count() {
        return typeUEARepository.countBy();
    }
}
