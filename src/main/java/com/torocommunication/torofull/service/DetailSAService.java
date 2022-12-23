package com.torocommunication.torofull.service;

import com.torocommunication.torofull.entities.DetailSA;
import com.torocommunication.torofull.repo.DetailSARepository;
import com.torocommunication.torofull.service.serviceInterface.DetailSAInterface;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class DetailSAService implements DetailSAInterface {
    private final DetailSARepository detailSARepository;

    public DetailSAService(DetailSARepository detailSARepository) {
        this.detailSARepository = detailSARepository;
    }

    @Override
    public List<DetailSA> getAll() {
        return detailSARepository.findAll();
    }

    @Override
    public DetailSA create(DetailSA data) {
        return detailSARepository.save(data);
    }

    @Override
    public DetailSA edit(Long id, DetailSA data) {
        DetailSA detailSA = new DetailSA();
        data = getByIds(id);
        detailSA.setId(data.getId());
        detailSA.setLibelle(data.getLibelle());
        return detailSARepository.save(detailSA);
    }

    @Override
    public DetailSA getByIds(Long id) {
        return detailSARepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        detailSARepository.deleteById(id);
    }

    @Override
    public int count() {
        return detailSARepository.countBy();
    }
}
