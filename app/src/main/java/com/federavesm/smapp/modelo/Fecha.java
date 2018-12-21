package com.federavesm.smapp.modelo;

/**
 * Created by Federico on 2/1/2018.
 */


public class Fecha {


    public Fecha(){}

    public Fecha(int year,int mes,int dia)
    {
        this.year = year;
        this.mes = mes;
        this.dia = dia;
    }

    private int year;
    private int mes;
    private int dia;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }


    @Override
    public String toString() {
        return String.valueOf(this.year) + "-" + String.valueOf(this.mes) + "-" + String.valueOf(this.dia);
    }





}