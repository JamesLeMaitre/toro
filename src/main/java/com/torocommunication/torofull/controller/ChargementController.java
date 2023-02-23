package com.torocommunication.torofull.controller;


import com.torocommunication.torofull.entities.Chargement;
import com.torocommunication.torofull.service.serviceInterface.ChargementServiceInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.ERROR_PROCESSING_MESSAGE;
import static com.torocommunication.torofull.constants.MessageController.LIST_OF_ELEMENT;

@RestController
@RequestMapping("api/chargement/")
@CrossOrigin("*")
public class ChargementController  extends DataFormatter<Chargement> {


    @Autowired
    private ChargementServiceInterface chargementServiceInterface;



    @GetMapping("charger/{idUea}/{idAppel}")
    public Object register( @PathVariable("idUea") Long idUea,  @PathVariable("idAppel") Long idAppel) {

        Chargement exist=  chargementServiceInterface.getByIdUeaAndAppel(idUea,idAppel);

        try {
            if(exist!=null){
                return  renderData(false,exist,"offre deja chargé ");
            }
            Chargement chargement=chargementServiceInterface.chargerOffre(idUea,idAppel);
            return  renderData(true,chargement,"Operation successfully ");

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }


    }

    @GetMapping("listByidUea/{idUea}")
    public Object ListByIdUea( @PathVariable("idUea") Long idUea){
        try {
            List<Chargement> items = chargementServiceInterface.findByIdUea(idUea);
            return  renderDataArray(true,items,LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
