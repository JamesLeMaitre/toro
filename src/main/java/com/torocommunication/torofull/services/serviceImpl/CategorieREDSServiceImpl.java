package com.torocommunication.torofull.services.serviceImpl;

import com.torocommunication.torofull.entities.CategorieREDS;
import com.torocommunication.torofull.repositories.CategorieREDSRepository;
import com.torocommunication.torofull.services.serviceInterface.CategorieREDSInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategorieREDSServiceImpl implements CategorieREDSInterface {
    private final CategorieREDSRepository redsRepository;

    public CategorieREDSServiceImpl(CategorieREDSRepository redsRepository) {
        this.redsRepository = redsRepository;
    }

    @Override
    public List<CategorieREDS> getAll() {
        return redsRepository.findAll();
    }

    @Override
    public CategorieREDS getById(Long id) {
        return redsRepository.findById(id).orElse(null);
    }

    @Override
    public CategorieREDS update(CategorieREDS data, Long id) {
        CategorieREDS categorieREDS = redsRepository.findById(id).orElse(null);
        categorieREDS.setId(data.getId());
        categorieREDS.setLibelle(data.getLibelle());
        return redsRepository.save(categorieREDS);
    }

    @Override
    public void delete(Long id) {
        redsRepository.deleteById(id);
    }

    @Override
    public CategorieREDS save(CategorieREDS data) {
        return redsRepository.save(data);
    }
}
