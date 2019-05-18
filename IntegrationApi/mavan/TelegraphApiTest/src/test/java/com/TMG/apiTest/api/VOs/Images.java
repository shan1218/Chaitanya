/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;

import com.fasterxml.jackson.annotation.JsonProperty;

/* Time: 2019-05-18 17:6:12 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
public class Images {

    @JsonProperty("url_medium")
    private String urlMedium;
    @JsonProperty("url_xsmall")
    private String urlXsmall;
    public void setUrlMedium(String urlMedium) {
         this.urlMedium = urlMedium;
     }
     public String getUrlMedium() {
         return urlMedium;
     }

    public void setUrlXsmall(String urlXsmall) {
         this.urlXsmall = urlXsmall;
     }
     public String getUrlXsmall() {
         return urlXsmall;
     }

}