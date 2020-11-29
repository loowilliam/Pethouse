package com.example.project.pethouse.model;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat model Header Booking
*/

/*
    edited By William Loo
    Date : 8 Mei 2020
    Purpose : tambah fk petid dan hotelid
*/

import java.io.Serializable;
import java.util.Date;

public class HeaderBooking implements Serializable {
    private int BookingID;
    private int UserID;
    private int PromoID;
    private int PetID;
    private int HotelID;
    private String Period;
    private String CheckIn;
    private String CheckOut;
    private String TotalPayment;
    private String BookingDate;
    private String StatusPayment;

    public int getBookingID() {
        return BookingID;
    }

    public void setBookingID(int bookingID) {
        BookingID = bookingID;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getPromoID() {
        return PromoID;
    }

    public void setPromoID(int promoID) {
        PromoID = promoID;
    }

    public int getPetID() {
        return PetID;
    }

    public void setPetID(int petID) {
        PetID = petID;
    }

    public int getHotelID() {
        return HotelID;
    }

    public void setHotelID(int hotelID) {
        HotelID = hotelID;
    }

    public String getPeriod() {
        return Period;
    }

    public void setPeriod(String period) {
        Period = period;
    }


    public String getTotalPayment() {
        return TotalPayment;
    }

    public void setTotalPayment(String totalPayment) {
        TotalPayment = totalPayment;
    }

    public String getCheckIn() {
        return CheckIn;
    }

    public void setCheckIn(String checkIn) {
        CheckIn = checkIn;
    }

    public String getCheckOut() {
        return CheckOut;
    }

    public void setCheckOut(String checkOut) {
        CheckOut = checkOut;
    }

    public String getBookingDate() {
        return BookingDate;
    }

    public void setBookingDate(String bookingDate) {
        BookingDate = bookingDate;
    }

    public String getStatusPayment() {
        return StatusPayment;
    }

    public void setStatusPayment(String statusPayment) {
        StatusPayment = statusPayment;
    }
}
