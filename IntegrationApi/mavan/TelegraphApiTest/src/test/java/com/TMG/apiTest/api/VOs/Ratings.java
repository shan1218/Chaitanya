/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;

/* Time: 2019-05-18 17:6:12 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
public class Ratings {

    private String label;
    private int value;
    private String text;
    public void setLabel(String label) {
         this.label = label;
     }
     public String getLabel() {
         return label;
     }

    public void setValue(int value) {
         this.value = value;
     }
     public int getValue() {
         return value;
     }

    public void setText(String text) {
         this.text = text;
     }
     public String getText() {
         return text;
     }

}