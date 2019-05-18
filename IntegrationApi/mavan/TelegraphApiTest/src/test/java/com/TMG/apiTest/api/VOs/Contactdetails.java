/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;
import com.fasterxml.jackson.annotation.JsonProperty;
public class Contactdetails {

    private String label;
    private String type;
    @JsonProperty("alternativeLabel")
    private String alternativelabel;
    private String notes;
    public void setLabel(String label) {
         this.label = label;
     }
     public String getLabel() {
         return label;
     }

    public void setType(String type) {
         this.type = type;
     }
     public String getType() {
         return type;
     }

    public void setAlternativelabel(String alternativelabel) {
         this.alternativelabel = alternativelabel;
     }
     public String getAlternativelabel() {
         return alternativelabel;
     }

    public void setNotes(String notes) {
         this.notes = notes;
     }
     public String getNotes() {
         return notes;
     }

}