package com.torocommunication.torofull.service.serviceInterface;

import com.torocommunication.torofull.entities.Postuler;

public interface PostulerServiceInterface {

    Postuler createPostuler(String cv , String lm, Long appel,Long uea,Long chargement);
}
