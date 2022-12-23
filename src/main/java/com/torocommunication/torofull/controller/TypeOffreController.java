package com.torocommunication.torofull.controller;

import com.torocommunication.torofull.entities.TypeOffre;
import com.torocommunication.torofull.service.serviceInterface.TypeOffreInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.*;

@RestController
@RequestMapping("api/typeoffre/")
public class TypeOffreController extends DataFormatter<TypeOffre> {

    private final TypeOffreInterface typeOffreInterface;

    public TypeOffreController(TypeOffreInterface typeOffreInterface) {
        this.typeOffreInterface = typeOffreInterface;
    }


    @PostMapping("create")
    public Object create(@RequestBody() TypeOffre data){

            try {
                return  renderData(true, typeOffreInterface.create(data),SUCCESS_MESSAGE);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
            }

    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody TypeOffre  data) {
        try {
            if( typeOffreInterface.getByIds(id)==null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            return  renderData(true, typeOffreInterface.edit(id,data),UPDATE_SUCCESS);
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
            List<TypeOffre> items = typeOffreInterface.getAll();
            return  renderDataArray(true,items,"("+typeOffreInterface.count()+") "+LIST_OF_ELEMENT);
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
            TypeOffre item = typeOffreInterface.getByIds(id);
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
            TypeOffre item = typeOffreInterface.getByIds(id);
            if(item == null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            typeOffreInterface.delete(id);
            return  renderStringData(true,"",SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
