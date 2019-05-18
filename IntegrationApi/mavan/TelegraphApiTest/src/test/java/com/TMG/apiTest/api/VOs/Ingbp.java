/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ingbp {

    @JsonProperty("currencyCode")
    private String currencycode;
    private double value;
    public void setCurrencycode(String currencycode) {
         this.currencycode = currencycode;
     }
     public String getCurrencycode() {
         return currencycode;
     }

    public void setValue(double value) {
         this.value = value;
     }
     public double getValue() {
         return value;
     }

}