
package com.TMG.apiTest.api.VOs;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
public class Annotations {

    private List<Facilities> facilities;
    private List<Editorial> editorial;
    @JsonProperty("activeOffers")
    private List<Activeoffers> activeoffers;
    private List<Types> types;
    @JsonProperty("explicitAnnotations")
    private List<Explicitannotations> explicitannotations;
    public void setFacilities(List<Facilities> facilities) {
         this.facilities = facilities;
     }
     public List<Facilities> getFacilities() {
         return facilities;
     }

    public void setEditorial(List<Editorial> editorial) {
         this.editorial = editorial;
     }
     public List<Editorial> getEditorial() {
         return editorial;
     }

    public void setActiveoffers(List<Activeoffers> activeoffers) {
         this.activeoffers = activeoffers;
     }
     public List<Activeoffers> getActiveoffers() {
         return activeoffers;
     }

    public void setTypes(List<Types> types) {
         this.types = types;
     }
     public List<Types> getTypes() {
         return types;
     }

    public void setExplicitannotations(List<Explicitannotations> explicitannotations) {
         this.explicitannotations = explicitannotations;
     }
     public List<Explicitannotations> getExplicitannotations() {
         return explicitannotations;
     }

}