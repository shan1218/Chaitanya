/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
public class Packages {

    @JsonProperty("standAloneTotalPricePerPerson")
    private double standalonetotalpriceperperson;
    @JsonProperty("averageCostPerPerson")
    private double averagecostperperson;
    @JsonProperty("currencyCode")
    private String currencycode;
    private String source;
    @JsonProperty("hotelId")
    private String hotelid;
    private List<Pics> pics;
    @JsonProperty("numberOfNights")
    private int numberofnights;
    @JsonProperty("airportCode")
    private String airportcode;
    public void setStandalonetotalpriceperperson(double standalonetotalpriceperperson) {
         this.standalonetotalpriceperperson = standalonetotalpriceperperson;
     }
     public double getStandalonetotalpriceperperson() {
         return standalonetotalpriceperperson;
     }

    public void setAveragecostperperson(double averagecostperperson) {
         this.averagecostperperson = averagecostperperson;
     }
     public double getAveragecostperperson() {
         return averagecostperperson;
     }

    public void setCurrencycode(String currencycode) {
         this.currencycode = currencycode;
     }
     public String getCurrencycode() {
         return currencycode;
     }

    public void setSource(String source) {
         this.source = source;
     }
     public String getSource() {
         return source;
     }

    public void setHotelid(String hotelid) {
         this.hotelid = hotelid;
     }
     public String getHotelid() {
         return hotelid;
     }

    public void setPics(List<Pics> pics) {
         this.pics = pics;
     }
     public List<Pics> getPics() {
         return pics;
     }

    public void setNumberofnights(int numberofnights) {
         this.numberofnights = numberofnights;
     }
     public int getNumberofnights() {
         return numberofnights;
     }

    public void setAirportcode(String airportcode) {
         this.airportcode = airportcode;
     }
     public String getAirportcode() {
         return airportcode;
     }

}