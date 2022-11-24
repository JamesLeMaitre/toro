package com.torocommunication.torofull.controllers;

import com.torocommunication.torofull.entities.Abonnement;
import com.torocommunication.torofull.services.serviceInterface.AbonnementInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/abonnement/")
public class AbonnementController extends DataFormatter<Abonnement> {
    private final AbonnementInterface anInterface;

    @PostMapping("add")
    public Object create(@RequestBody() Abonnement data){
        try {
            return  renderData(true, anInterface.create(data),"Create ");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody Abonnement data) {
        try {
            if( anInterface.getAbonnement(id)==null){
                return  renderStringData(false,"ID null" ,"item not found");
            }
            return  renderData(true, anInterface.update(data,id),"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("list")
    public Object List(){
        try {
            List<Abonnement> items = anInterface.getAllAbonnement();
            return  renderDataArray(true,items,"list of element");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @GetMapping("by/id/{id}")
    public Object getById(@PathVariable("id") Long id){
        try {
            Abonnement item = anInterface.getAbonnement(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            return  renderData(true,item,"Element found");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while procssing" ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable("id") Long id){
        try {
            Abonnement item = anInterface.getAbonnement(id);
            if(item == null){
                return  renderStringData(false,"Error while processing" ,"item not found");
            }
            anInterface.deleteAbonnement(id);
            return  renderStringData(true,"Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

}
