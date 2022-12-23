package com.torocommunication.torofull.controller;

import com.torocommunication.torofull.entities.Abonnement;
import com.torocommunication.torofull.service.serviceInterface.AbonnementInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.*;

@RestController
@RequestMapping("api/abonnement/")
public class AbonnementController extends DataFormatter<Abonnement> {

    private final AbonnementInterface abonnementInterface;

    public AbonnementController(AbonnementInterface abonnementInterface) {
        this.abonnementInterface = abonnementInterface;
    }

    @PostMapping("create")
    public Object create(@RequestBody() Abonnement data){

            try {
                return  renderData(true, abonnementInterface.create(data),SUCCESS_MESSAGE);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
            }

    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody Abonnement  data) {
        try {
            if( abonnementInterface.getByIds(id)==null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            return  renderData(true, abonnementInterface.edit(id,data),UPDATE_SUCCESS);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("list")
    public Object List(){
        try {
            List<Abonnement> items = abonnementInterface.getAll();
            return  renderDataArray(true,items,"("+abonnementInterface.count()+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("by/id/{id}")
    public Object getById(@PathVariable("id") Long id){
        try {
            Abonnement item = abonnementInterface.getByIds(id);
            if(item == null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            return  renderData(true,item,DATA_FOUND);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @DeleteMapping("delete/{id}")
    public Object delete(@PathVariable("id") Long id){
        try {
            Abonnement item = abonnementInterface.getByIds(id);
            if(item == null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            abonnementInterface.delete(id);
            return  renderStringData(true,"",SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
