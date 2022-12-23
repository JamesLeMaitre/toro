package com.torocommunication.torofull.controller;

import com.torocommunication.torofull.entities.SecteurDactivite;
import com.torocommunication.torofull.service.serviceInterface.SecteurDactiviteInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.*;

@RestController
@RequestMapping("api/secteurdactivite/")
public class SecteurDactiviteController extends DataFormatter<SecteurDactivite> {

    private final SecteurDactiviteInterface secteurDactiviteInterface;

    public SecteurDactiviteController(SecteurDactiviteInterface secteurDactiviteInterface) {
        this.secteurDactiviteInterface = secteurDactiviteInterface;
    }


    @PostMapping("create")
    public Object create(@RequestBody() SecteurDactivite data){

            try {
                return  renderData(true, secteurDactiviteInterface.create(data),SUCCESS_MESSAGE);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
            }

    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody SecteurDactivite  data) {
        try {
            if( secteurDactiviteInterface.getByIds(id)==null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            return  renderData(true, secteurDactiviteInterface.edit(id,data),UPDATE_SUCCESS);
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
            List<SecteurDactivite> items = secteurDactiviteInterface.getAll();
            return  renderDataArray(true,items,"("+secteurDactiviteInterface.count()+") "+LIST_OF_ELEMENT);
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
            SecteurDactivite item = secteurDactiviteInterface.getByIds(id);
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
            SecteurDactivite item = secteurDactiviteInterface.getByIds(id);
            if(item == null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            secteurDactiviteInterface.delete(id);
            return  renderStringData(true,"",SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
