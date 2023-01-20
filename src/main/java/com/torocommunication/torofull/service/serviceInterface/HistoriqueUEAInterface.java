package com.torocommunication.torofull.service.serviceInterface;




import com.torocommunication.torofull.entities.HistoriqueUEA;

import java.util.List;

public interface HistoriqueUEAInterface {

    List<HistoriqueUEA> getAll();

    HistoriqueUEA create(HistoriqueUEA data);
    HistoriqueUEA getById(Long id);

    List<HistoriqueUEA> getByIdUEA(Long id);

}
