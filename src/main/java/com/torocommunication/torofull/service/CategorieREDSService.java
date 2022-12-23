package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.CategorieREDS;
import com.torocommunication.torofull.repo.CategorieREDSRepository;
import com.torocommunication.torofull.service.serviceInterface.CategorieREDSInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategorieREDSService implements CategorieREDSInterface {

    private final CategorieREDSRepository categorieREDSRepository;

    public CategorieREDSService(CategorieREDSRepository categorieREDSRepository) {
        this.categorieREDSRepository = categorieREDSRepository;
    }

    @Override
    public List<CategorieREDS> getAll() {
        return categorieREDSRepository.findAll();
    }

    @Override
    public CategorieREDS create(CategorieREDS data) {
        return categorieREDSRepository.save(data);
    }

    @Override
    public CategorieREDS edit(Long id, CategorieREDS data) {
        CategorieREDS categorieREDS = new CategorieREDS();
        data = getByIds(id);
        categorieREDS.setId(data.getId());
        categorieREDS.setLibelle(data.getLibelle());
        return categorieREDSRepository.save(categorieREDS);
    }

    @Override
    public CategorieREDS getByIds(Long id) {
        return categorieREDSRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        categorieREDSRepository.deleteById(id);
    }

    @Override
    public int count() {
        return categorieREDSRepository.countBy();
    }
}
