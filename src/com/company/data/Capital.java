package com.company.data;

public class Capital extends Place{
    protected int Square;

    public Capital(String name,int NumberOfInhabitants,int Square){
        super(name,NumberOfInhabitants);
        this.Square=Square;
    }

    public int getSquare() {
        return Square;
    }

    public void setSquare(int square) {
        Square = square;
    }

    @Override
    public String InfoType(){
        return ("Столица");
    }
}
