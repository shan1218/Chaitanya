/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
/* Time: 2019-05-18 17:6:12 @author freecodeformat.com @website http://www.freecodeformat.com/json2javabean.php */
public class HotelVO {

    @JsonProperty("flakeId")
    private String flakeid;
    private Uri uri;
    private String name;
    private List<String> aliases;
    @JsonProperty("reservationDetails")
    private Reservationdetails reservationdetails;
    private List<Locations> locations;
    private Approver approver;
    @JsonProperty("lastEditor")
    private Lasteditor lasteditor;
    @JsonProperty("contactDetails")
    private List<Contactdetails> contactdetails;
    private List<Images> images;
    private List<Ratings> ratings;
    @JsonProperty("isPublished")
    private boolean ispublished;
    @JsonProperty("disambiguationHint")
    private String disambiguationhint;
    @JsonProperty("geoLocalisationHint")
    private String geolocalisationhint;
    @JsonProperty("averageRating")
    private int averagerating;
    @JsonProperty("pageUrl")
    private String pageurl;
    private List<Packages> packages;
    @JsonProperty("aemId")
    private String aemid;
    @JsonProperty("updatedFields")
    private List<String> updatedfields;
    private Annotations annotations;
    @JsonProperty("productAnnotations")
    private List<Productannotations> productannotations;
    public void setFlakeid(String flakeid) {
         this.flakeid = flakeid;
     }
     public String getFlakeid() {
         return flakeid;
     }

    public void setUri(Uri uri) {
         this.uri = uri;
     }
     public Uri getUri() {
         return uri;
     }

    public void setName(String name) {
         this.name = name;
     }
     public String getName() {
         return name;
     }

    public void setAliases(List<String> aliases) {
         this.aliases = aliases;
     }
     public List<String> getAliases() {
         return aliases;
     }

    public void setReservationdetails(Reservationdetails reservationdetails) {
         this.reservationdetails = reservationdetails;
     }
     public Reservationdetails getReservationdetails() {
         return reservationdetails;
     }

    public void setLocations(List<Locations> locations) {
         this.locations = locations;
     }
     public List<Locations> getLocations() {
         return locations;
     }

    public void setApprover(Approver approver) {
         this.approver = approver;
     }
     public Approver getApprover() {
         return approver;
     }

    public void setLasteditor(Lasteditor lasteditor) {
         this.lasteditor = lasteditor;
     }
     public Lasteditor getLasteditor() {
         return lasteditor;
     }

    public void setContactdetails(List<Contactdetails> contactdetails) {
         this.contactdetails = contactdetails;
     }
     public List<Contactdetails> getContactdetails() {
         return contactdetails;
     }

    public void setImages(List<Images> images) {
         this.images = images;
     }
     public List<Images> getImages() {
         return images;
     }

    public void setRatings(List<Ratings> ratings) {
         this.ratings = ratings;
     }
     public List<Ratings> getRatings() {
         return ratings;
     }

    public void setIspublished(boolean ispublished) {
         this.ispublished = ispublished;
     }
     public boolean getIspublished() {
         return ispublished;
     }

    public void setDisambiguationhint(String disambiguationhint) {
         this.disambiguationhint = disambiguationhint;
     }
     public String getDisambiguationhint() {
         return disambiguationhint;
     }

    public void setGeolocalisationhint(String geolocalisationhint) {
         this.geolocalisationhint = geolocalisationhint;
     }
     public String getGeolocalisationhint() {
         return geolocalisationhint;
     }

    public void setAveragerating(int averagerating) {
         this.averagerating = averagerating;
     }
     public int getAveragerating() {
         return averagerating;
     }

    public void setPageurl(String pageurl) {
         this.pageurl = pageurl;
     }
     public String getPageurl() {
         return pageurl;
     }

    public void setPackages(List<Packages> packages) {
         this.packages = packages;
     }
     public List<Packages> getPackages() {
         return packages;
     }

    public void setAemid(String aemid) {
         this.aemid = aemid;
     }
     public String getAemid() {
         return aemid;
     }

    public void setUpdatedfields(List<String> updatedfields) {
         this.updatedfields = updatedfields;
     }
     public List<String> getUpdatedfields() {
         return updatedfields;
     }

    public void setAnnotations(Annotations annotations) {
         this.annotations = annotations;
     }
     public Annotations getAnnotations() {
         return annotations;
     }

    public void setProductannotations(List<Productannotations> productannotations) {
         this.productannotations = productannotations;
     }
     public List<Productannotations> getProductannotations() {
         return productannotations;
     }

}