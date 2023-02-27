package com.torocommunication.torofull.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PostuerInput {

    String cv ;
    String lm;
    Long appel;
    Long uea;
    Long chargement;
}
