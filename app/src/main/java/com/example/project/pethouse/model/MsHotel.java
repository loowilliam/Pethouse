package com.example.project.pethouse.model;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat model Hotel
*/

public class MsHotel {
    private int HotelID;
    private String HotelName;
    private String HotelLocation;
    private String HotelDesc;
    private int RemainingSlot;
    private String OwnerName;
    private String OwnerEmail;
    private String OwnerPhone;
    private int OwnerPrice;

    public int getHotelID() {
        return HotelID;
    }

    public void setHotelID(int hotelID) {
        HotelID = hotelID;
    }

    public String getHotelName() {
        return HotelName;
    }

    public void setHotelName(String hotelName) {
        HotelName = hotelName;
    }

    public String getHotelLocation() {
        return HotelLocation;
    }

    public void setHotelLocation(String hotelLocation) {
        HotelLocation = hotelLocation;
    }

    public String getHotelDesc() {
        return HotelDesc;
    }

    public void setHotelDesc(String hotelDesc) {
        HotelDesc = hotelDesc;
    }

    public int getRemainingSlot() {
        return RemainingSlot;
    }

    public void setRemainingSlot(int remainingSlot) {
        RemainingSlot = remainingSlot;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerEmail() {
        return OwnerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        OwnerEmail = ownerEmail;
    }

    public String getOwnerPhone() {
        return OwnerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        OwnerPhone = ownerPhone;
    }

    public int getOwnerPrice() {
        return OwnerPrice;
    }

    public void setOwnerPrice(int ownerPrice) {
        OwnerPrice = ownerPrice;
    }
}
