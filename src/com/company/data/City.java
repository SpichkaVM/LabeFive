package com.company.data;

public class City extends Place {
    protected int NmicroDistrict;

    public City(String name,int NumberOfInhabitants,int NmicroDistrict){
        super(name,NumberOfInhabitants);
        this.NmicroDistrict=NmicroDistrict;
    }

    public int getNmicroDistrict() {
        return NmicroDistrict;
    }

    public void setNmicroDistrict(int nmicroDistrict) {
        NmicroDistrict = nmicroDistrict;
    }

    @Override
    public String InfoType(){
        return ("Город");
    }
}
