package com.torocommunication.torofull.controller;



import com.torocommunication.torofull.entities.Postuler;
import com.torocommunication.torofull.models.PostuerInput;
import com.torocommunication.torofull.service.serviceInterface.PostulerServiceInterface;
import com.torocommunication.torofull.utiles.DataFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;


import static com.torocommunication.torofull.constants.MessageController.*;

@RestController
@RequestMapping("api/postuler/")
@CrossOrigin("*")
public class PostulerController extends DataFormatter<Postuler> {


    @Autowired
    private PostulerServiceInterface postulerServiceInterface;



    @PostMapping("save")
    public Object createPostuler(@RequestBody PostuerInput postuerInput){
        try {
          Postuler   items = postulerServiceInterface.createPostuler(postuerInput.getCv(),postuerInput.getLm(),
                  postuerInput.getAppel(),postuerInput.getUea(),postuerInput.getChargement());
            return  renderData(true,items,SUCCESS_MESSAGE);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            return  renderStringData(false,ERROR_PROCESSING_MESSAGE ,exceptionAsString);
        }
    }
}
