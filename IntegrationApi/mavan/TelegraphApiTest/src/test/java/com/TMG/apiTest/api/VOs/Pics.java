/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Pics {

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