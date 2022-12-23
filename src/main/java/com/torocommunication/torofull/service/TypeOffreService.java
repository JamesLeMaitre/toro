package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.TypeOffre;
import com.torocommunication.torofull.repo.TypeOffreRepository;
import com.torocommunication.torofull.service.serviceInterface.TypeOffreInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class TypeOffreService implements TypeOffreInterface {

    private final TypeOffreRepository typeOffreRepository;

    public TypeOffreService(TypeOffreRepository typeOffreRepository) {
        this.typeOffreRepository = typeOffreRepository;
    }

    @Override
    public List<TypeOffre> getAll() {
        return typeOffreRepository.findAll();
    }

    @Override
    public TypeOffre create(TypeOffre data) {
        return typeOffreRepository.save(data);
    }

    @Override
    public TypeOffre edit(Long id, TypeOffre data) {
        TypeOffre typeOffre = new TypeOffre();
        data = getByIds(id);
        typeOffre.setId(data.getId());
        typeOffre.setLibelle(data.getLibelle());
        typeOffre.setEtat(data.getEtat());
        return typeOffreRepository.save(typeOffre);
    }

    @Override
    public TypeOffre getByIds(Long id) {
        return typeOffreRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        typeOffreRepository.deleteById(id);
    }

    @Override
    public int count() {
        return typeOffreRepository.countBy();
    }
}
