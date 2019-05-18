/* Copyright 2019 freecodeformat.com */
package com.TMG.apiTest.api.VOs;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Reservationdetails {

    @JsonProperty("hotelUrl")
    private String hotelurl;
    @JsonProperty("reservationUrl")
    private String reservationurl;
    private Partner partner;
    @JsonProperty("productId")
    private String productid;
    private Price price;
    public void setHotelurl(String hotelurl) {
         this.hotelurl = hotelurl;
     }
     public String getHotelurl() {
         return hotelurl;
     }

    public void setReservationurl(String reservationurl) {
         this.reservationurl = reservationurl;
     }
     public String getReservationurl() {
         return reservationurl;
     }

    public void setPartner(Partner partner) {
         this.partner = partner;
     }
     public Partner getPartner() {
         return partner;
     }

    public void setProductid(String productid) {
         this.productid = productid;
     }
     public String getProductid() {
         return productid;
     }

    public void setPrice(Price price) {
         this.price = price;
     }
     public Price getPrice() {
         return price;
     }

}