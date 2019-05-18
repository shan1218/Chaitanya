/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;

import com.fasterxml.jackson.annotation.JsonProperty;

/* Time: 2019-05-18 17:6:12 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
public class Price {

    private Original original;
    @JsonProperty("inGBP")
    private Ingbp ingbp;
    public void setOriginal(Original original) {
         this.original = original;
     }
     public Original getOriginal() {
         return original;
     }

    public void setIngbp(Ingbp ingbp) {
         this.ingbp = ingbp;
     }
     public Ingbp getIngbp() {
         return ingbp;
     }

}