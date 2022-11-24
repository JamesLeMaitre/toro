package com.torocommunication.torofull.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class  Formatter<variant> {
    private boolean status;
    private variant data;
    private String message;
}