package com.company.data;

public class Area extends Place {
    protected int Ndistrict;

    public Area(String name, int NumberOfInhabitants, int Ndistrict){
        super(name, NumberOfInhabitants);
        this.Ndistrict=Ndistrict;
    }

    public int getNdistrict() {
        return Ndistrict;
    }

    public void setNdistrict(int ndistrict) {
        this.Ndistrict = ndistrict;
    }
    @Override
    public String InfoType(){
        return ("Область");
    }
}
