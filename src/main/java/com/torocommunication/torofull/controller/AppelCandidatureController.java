package com.torocommunication.torofull.controller;




import com.torocommunication.torofull.entities.AppelCandidature;
import com.torocommunication.torofull.service.serviceInterface.AppelCandidatureInterface;
import com.torocommunication.torofull.utiles.DataFormatter;


import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.ERROR_PROCESSING_MESSAGE;
import static com.torocommunication.torofull.constants.MessageController.LIST_OF_ELEMENT;

@RestController
@RequestMapping("api/appelCandidature/")
@CrossOrigin("*")
public class AppelCandidatureController  extends DataFormatter<AppelCandidature> {
    private final AppelCandidatureInterface appelCandidatureInterface;

    public AppelCandidatureController(AppelCandidatureInterface appelCandidatureInterface) {
        this.appelCandidatureInterface = appelCandidatureInterface;
    }


    @PostMapping("register")
    public Object register( @RequestBody AppelCandidature appelCandidature)  {



        try {

                return  renderData(true,appelCandidatureInterface.create(appelCandidature),"Operation successfully ");


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
            List<AppelCandidature> items = appelCandidatureInterface.getAll();
            return  renderDataArray(true,items,"("+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }






    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable("id") Long id, @RequestBody AppelCandidature appelCandidature) {
        try {

            AppelCandidature response = appelCandidatureInterface.edit(id, appelCandidature);

            return  renderData(true, response,"update done successfully");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }




    @GetMapping("disable/{id}")
    public Object disable(@PathVariable("id") Long id){
        try {
            appelCandidatureInterface.disable(id);


            return  renderStringData(true, "Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }

    @GetMapping("enable/{id}")
    public Object delete(@PathVariable("id") Long id){
        try {
            appelCandidatureInterface.enable(id);


            return  renderStringData(true, "Delete successfully","Done");
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,"Error while processing" ,exceptionAsString);
        }
    }


    @GetMapping("byIdUea/{id}")
    public Object ByIdUEA(@PathVariable("id") Long id){
        try {
            List<AppelCandidature> items = appelCandidatureInterface.findByIdUea(id);
            return  renderDataArray(true,items,"("+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }


    @GetMapping("getBYid/{id}")
    public Object getById(@PathVariable("id") Long id){
        try {
            AppelCandidature items = appelCandidatureInterface.getById(id);
            return  renderData(true,items,"("+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("typeOffre")
    public Object getByTypeOofre(){
        try {
            List<AppelCandidature> items = appelCandidatureInterface.listAppelCandidatureByTypeOffre();
            return  renderDataArray(true,items,"("+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }


}

