package com.company.data;

import java.util.ArrayList;

public class ListPlace {
    private ArrayList<Place> places = new ArrayList<Place>();
    public ListPlace(){
        places.add(new Area("Иркутская", 235654, 33));
        places.add(new City("Саянск",3000,9));
        places.add(new Capital("Москва",1000000,70));
    }

    public int getCount(){
        return this.places.size();
    }

    public Place getPlace(int index){
        return places.get(index);
    }

    public  void AddPlace(Place place){
        places.add(place);
    }

    public  void remove(int ind){
        this.places.remove(ind);
    }

}
