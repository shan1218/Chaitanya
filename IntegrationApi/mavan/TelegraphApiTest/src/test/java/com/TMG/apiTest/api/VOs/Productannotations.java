/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;
import java.util.List;

/* Time: 2019-05-18 17:6:12 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
public class Productannotations {

    private String id;
    private String label;
    private List<String> type;
    public void setId(String id) {
         this.id = id;
     }
     public String getId() {
         return id;
     }

    public void setLabel(String label) {
         this.label = label;
     }
     public String getLabel() {
         return label;
     }

    public void setType(List<String> type) {
         this.type = type;
     }
     public List<String> getType() {
         return type;
     }

}