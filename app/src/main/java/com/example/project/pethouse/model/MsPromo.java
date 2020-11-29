package com.example.project.pethouse.model;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat model promo
*/

import java.util.Date;

public class MsPromo {
    private int PromoID;
    private String PromoName;
    private String PromoDesc;
    private String PromoCode;
    private String ValidDate;

    public int getPromoID() {
        return PromoID;
    }

    public void setPromoID(int promoID) {
        PromoID = promoID;
    }

    public String getPromoName() {
        return PromoName;
    }

    public void setPromoName(String promoName) {
        PromoName = promoName;
    }

    public String getPromoDesc() {
        return PromoDesc;
    }

    public void setPromoDesc(String promoDesc) {
        PromoDesc = promoDesc;
    }

    public String getPromoCode() {
        return PromoCode;
    }

    public void setPromoCode(String promoCode) {
        PromoCode = promoCode;
    }

    public String getValidDate() {
        return ValidDate;
    }

    public void setValidDate(String validDate) {
        ValidDate = validDate;
    }
}
