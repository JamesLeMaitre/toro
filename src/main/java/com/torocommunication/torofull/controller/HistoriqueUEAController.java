package com.torocommunication.torofull.controller;



import com.torocommunication.torofull.entities.HistoriqueUEA;
import com.torocommunication.torofull.service.serviceInterface.HistoriqueUEAInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.ERROR_PROCESSING_MESSAGE;
import static com.torocommunication.torofull.constants.MessageController.LIST_OF_ELEMENT;

@RestController
@RequestMapping("api/histo/")
@CrossOrigin("*")
public class HistoriqueUEAController extends DataFormatter<HistoriqueUEA> {


    private  final HistoriqueUEAInterface ueaInterface;

    public HistoriqueUEAController(HistoriqueUEAInterface ueaInterface) {
        this.ueaInterface = ueaInterface;
    }


    @PostMapping("save")
    public Object register( @RequestBody HistoriqueUEA uea)  {




        try {

            return  renderData(true,ueaInterface.create(uea),"Operation successfully ");


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
            List<HistoriqueUEA> items = ueaInterface.getAll();
            return  renderDataArray(true,items,"("+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }














    @GetMapping("getByid/{id}")
    public Object getByid(@PathVariable("id") Long id){
        try {
            HistoriqueUEA items =ueaInterface.getById(id);


            return  renderData(true,items,"("+") "+LIST_OF_ELEMENT);
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
            List<HistoriqueUEA> items = ueaInterface.getByIdUEA(id);
            return  renderDataArray(true,items,"("+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

}
