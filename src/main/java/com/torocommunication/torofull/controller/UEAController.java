package com.torocommunication.torofull.controller;



import com.torocommunication.torofull.entities.UtilisateurUEA;
import com.torocommunication.torofull.service.serviceInterface.UtilisateurUEAInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.ERROR_PROCESSING_MESSAGE;
import static com.torocommunication.torofull.constants.MessageController.LIST_OF_ELEMENT;

@RestController
@AllArgsConstructor
@RequestMapping("/api/uea/")
@CrossOrigin("*")
public class UEAController extends DataFormatter<UtilisateurUEA> {



    private final UtilisateurUEAInterface utilisateurUEAInterface;



    @GetMapping("list")
    public Object List(){
        try {
            List<UtilisateurUEA> items = utilisateurUEAInterface.getUsers();
            return  renderDataArray(true,items,"("+utilisateurUEAInterface.count()+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("count")
    public Object count(){
        try {
       int items = utilisateurUEAInterface.count();
            return  items;

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
    @GetMapping("countA")
    public Object countAdmin(){
        try {
            int items = utilisateurUEAInterface.countAdmin();
            return  items;

        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }

    @GetMapping("getById/{id}")
    public Object getById(@PathVariable("id") Long id){
        try {
            UtilisateurUEA items = utilisateurUEAInterface.getById(id);
            return  renderData(true,items,"("+utilisateurUEAInterface.count()+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
    @GetMapping("list/by/{idType}/{idSA}")
    public Object getById(@PathVariable("idType") Long idType,@PathVariable("idSA") Long idSA){
        try {
            List<UtilisateurUEA> items = utilisateurUEAInterface.listUeaByIdTypeUeaAndIdDetailSA(idType,idSA);
            return  renderDataArray(true,items,"("+utilisateurUEAInterface.count()+") "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
