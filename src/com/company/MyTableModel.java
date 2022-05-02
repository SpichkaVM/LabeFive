package com.company;

import com.company.data.*;
import com.company.vew.MainWindow;
import com.sun.xml.internal.ws.api.streaming.XMLStreamWriterFactory;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class MyTableModel extends AbstractTableModel {
    private ListPlace data;


    public MyTableModel(ListPlace listPlace) {
        this.data = listPlace;
    }

    @Override
    public int getRowCount() {
        return data.getCount();
    }

    @Override
    public String getColumnName(int column) {
        switch (column){
            case 0: return "Место";
            case 1: return "Название";
            case 2: return "Число жителей";
            case 3: return "Количество районов";
            case 4: return "Площадь_в_м2";
        }
        return "";
    }

    @Override
    public int getColumnCount() {

        return 5;
    }


        @Override
        public Class getColumnClass(int columnIndex) {
            switch (columnIndex){
                case 1: return String.class;
                case 2: return Integer.class;
                case 3: return Integer.class;
                case 4: return Integer.class;
            }
            return String.class;
        }



    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0:
            break;
            case 1: data.getPlace(rowIndex).setName((String)aValue);
            break;
            case 2: data.getPlace(rowIndex).setNumberOfInhabitants((Integer)aValue);
            break;
            case 3: {
                Place p = data.getPlace(rowIndex);
                if (p instanceof Area){
                    ((Area) p).setNdistrict((Integer)aValue);
                }else{
                    if(p instanceof City){
                        ((City)p).setNmicroDistrict((Integer)aValue);
                    }

                }

            }
            case 4:{
                Place p = data.getPlace(rowIndex);
                if (p instanceof Capital){
                    ((Capital) p).setSquare((Integer)aValue);
                }
            }
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex){

            case 1: return true;
            case 2: return true;
            case 3: return true;
            case 4: return true;
        }
        return  false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex){
            case 0: return data.getPlace(rowIndex).InfoType();
            case 1: return data.getPlace(rowIndex).getName();
            case 2: return data.getPlace(rowIndex).getNumberOfInhabitants();
            case 3: {
                Place p = data.getPlace(rowIndex);
                if (p instanceof Area){
                    return ((Area) p).getNdistrict();
                }else {
                    if (p instanceof City)
                    return ((City) p).getNmicroDistrict();
                }

            }
            break;
            case 4:{
                Place p = data.getPlace(rowIndex);
                if (p instanceof Capital){
                    return ((Capital) p).getSquare();
                }
            }
        }
        return null;
    }

    public void delete(int ind){
        this.data.remove(ind);
        fireTableDataChanged();
    }

    public void ellAdd(int type, String name, int NumberOfInhabitants, int inDis){
        switch (type){
            case 0: data.AddPlace(new Area(name,NumberOfInhabitants,inDis));
            break;
            case 1:data.AddPlace(new City(name,NumberOfInhabitants,inDis));
            break;
            case 2:data.AddPlace(new Capital(name,NumberOfInhabitants,inDis));
            break;
        }
        fireTableDataChanged();
    }

}
