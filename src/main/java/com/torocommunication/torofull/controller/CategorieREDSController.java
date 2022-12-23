package com.torocommunication.torofull.controller;

import com.torocommunication.torofull.entities.Abonnement;
import com.torocommunication.torofull.entities.CategorieREDS;
import com.torocommunication.torofull.service.serviceInterface.CategorieREDSInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.*;

@RestController
@RequestMapping("api/categoriereds/")
public class CategorieREDSController extends DataFormatter<CategorieREDS> {

    private final CategorieREDSInterface categorieREDSInterface;

    public CategorieREDSController(CategorieREDSInterface categorieREDSInterface) {
        this.categorieREDSInterface = categorieREDSInterface;
    }


    @PostMapping("create")
    public Object create(@RequestBody() CategorieREDS data){

            try {
                return  renderData(true, categorieREDSInterface.create(data),SUCCESS_MESSAGE);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
            }

    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody CategorieREDS  data) {
        try {
            if( categorieREDSInterface.getByIds(id)==null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            return  renderData(true, categorieREDSInterface.edit(id,data),UPDATE_SUCCESS);
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
            List<CategorieREDS> items = categorieREDSInterface.getAll();
            return  renderDataArray(true,items,"("+categorieREDSInterface.count()+") "+LIST_OF_ELEMENT);
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
            CategorieREDS item = categorieREDSInterface.getByIds(id);
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
            CategorieREDS item = categorieREDSInterface.getByIds(id);
            if(item == null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            categorieREDSInterface.delete(id);
            return  renderStringData(true,"",SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
