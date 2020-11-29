package com.example.project.pethouse.model;

/*
    Created By William Loo
    Date : 8 Mei 2020
    Purpose : Buat model pet
*/

import java.io.Serializable;

public class MsPet implements Serializable {

    private int PetID;
    private String PetType;
    private String PetName;
    private String PetGender;
    private String PetAge;
    private String PetDesc;
    private String PetOwnerName;

    public int getPetID() {
        return PetID;
    }

    public void setPetID(int petID) {
        PetID = petID;
    }

    public String getPetType() {
        return PetType;
    }

    public void setPetType(String petType) {
        PetType = petType;
    }

    public String getPetName() {
        return PetName;
    }

    public void setPetName(String petName) {
        PetName = petName;
    }

    public String getPetGender() {
        return PetGender;
    }

    public void setPetGender(String petGender) {
        PetGender = petGender;
    }

    public String getPetAge() {
        return PetAge;
    }

    public void setPetAge(String petAge) {
        PetAge = petAge;
    }

    public String getPetDesc() {
        return PetDesc;
    }

    public void setPetDesc(String petDesc) {
        PetDesc = petDesc;
    }

    public String getPetOwnerName() {
        return PetOwnerName;
    }

    public void setPetOwnerName(String petOwnerName) {
        PetOwnerName = petOwnerName;
    }
}
