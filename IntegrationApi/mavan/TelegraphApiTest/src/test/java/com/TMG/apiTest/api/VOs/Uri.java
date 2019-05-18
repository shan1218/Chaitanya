/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Uri {

    private String namespace;
    @JsonProperty("localName")
    private String localname;
    public void setNamespace(String namespace) {
         this.namespace = namespace;
     }
     public String getNamespace() {
         return namespace;
     }

    public void setLocalname(String localname) {
         this.localname = localname;
     }
     public String getLocalname() {
         return localname;
     }

}