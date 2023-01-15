package com.torocommunication.torofull.controller;

import com.torocommunication.torofull.entities.CategorieREDS;
import com.torocommunication.torofull.entities.Formation;
import com.torocommunication.torofull.service.serviceInterface.FormationInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.*;

@RestController
@RequestMapping("api/formation/")
@CrossOrigin("*")
public class FormationController extends DataFormatter<Formation> {

    private final FormationInterface formationInterface;

    public FormationController(FormationInterface formationInterface) {
        this.formationInterface = formationInterface;
    }


    @PostMapping("create")
    public Object create(@RequestBody() Formation data){

            try {
                return  renderData(true, formationInterface.create(data),SUCCESS_MESSAGE);
            } catch (Exception e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
                String exceptionAsString = sw.toString();
                return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
            }

    }

    @PutMapping(value = "edit/{id}")
    public Object update(@PathVariable Long id, @RequestBody Formation  data) {
        try {
            if( formationInterface.getByIds(id)==null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            return  renderData(true, formationInterface.edit(id,data),UPDATE_SUCCESS);
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
            List<Formation> items = formationInterface.getAll();
            return  renderDataArray(true,items,"("+formationInterface.count()+") "+LIST_OF_ELEMENT);
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
            Formation item = formationInterface.getByIds(id);
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
            Formation item = formationInterface.getByIds(id);
            if(item == null){
                return  renderStringData(false,"" ,DATA_NOT_FOUND);
            }
            formationInterface.delete(id);
            return  renderStringData(true,"",SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
