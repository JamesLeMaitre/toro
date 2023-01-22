package com.torocommunication.torofull.controller;



import com.torocommunication.torofull.entities.JobType;
import com.torocommunication.torofull.entities.SecteurDactivite;
import com.torocommunication.torofull.service.serviceInterface.TypeJobServiceInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

import static com.torocommunication.torofull.constants.MessageController.ERROR_PROCESSING_MESSAGE;
import static com.torocommunication.torofull.constants.MessageController.LIST_OF_ELEMENT;

@RestController
@RequestMapping("api/jobtype/")
@CrossOrigin("*")
public class JobTypeController extends DataFormatter<JobType>  {

    private  final TypeJobServiceInterface typeJobServiceInterface;

    public JobTypeController(TypeJobServiceInterface typeJobServiceInterface) {
        this.typeJobServiceInterface = typeJobServiceInterface;
    }


    @GetMapping("list")
    public Object List(){
        try {
            List<JobType> items = typeJobServiceInterface.getAll();
            return  renderDataArray(true,items," "+LIST_OF_ELEMENT);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
