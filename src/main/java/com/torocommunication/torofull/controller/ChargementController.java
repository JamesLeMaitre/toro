package com.torocommunication.torofull.controller;


import com.torocommunication.torofull.entities.Chargement;
import com.torocommunication.torofull.security.exceptions.RoleNotFoundException;
import com.torocommunication.torofull.security.request.RegisterRequest;
import com.torocommunication.torofull.service.serviceInterface.ChargementServiceInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
@AllArgsConstructor
@RequestMapping("api/chargeement/")
@CrossOrigin("*")
public class ChargementController  extends DataFormatter<Chargement> {


    @Autowired
    private ChargementServiceInterface chargementServiceInterface;



    @PostMapping("charger")
    public Object register( @RequestParam Long idUea,  @RequestParam Long idAppel) throws MessagingException, RoleNotFoundException {

        try {
            return  renderData(true,chargementServiceInterface.chargerOffre(idUea,idAppel),"Operation successfully ");



        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }


    }
}
