package com.federavesm.smapp.modelo;

import java.util.Calendar;

/**
 * Created by Federico on 2/1/2018.
 */


public class Fecha {


    public Fecha()
    {
        Calendar date = Calendar.getInstance();
        this.year = date.get(date.YEAR);
        this.mes = 1+date.get(date.MONTH);
        this.dia = date.get(date.DAY_OF_MONTH);
    }

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

    // retorna 1 si es menor
    // retorna 0 si es igual
    // retorna -1 si es mayor
    public int comparar(Fecha fecha)
    {
    if(this.year < fecha.getYear())
        {
        return 1;
        }
    else if (this.year == fecha.getYear())
        {
        if(this.mes < fecha.getMes())
            {
            return 1;
            }
        else if (this.mes == fecha.getMes())
            {
            if(this.dia < fecha.getDia())
                {
                return 1;
                }
            else if (this.dia == fecha.getDia())
                {
                return 0;
                }
            else
                {
                return -1;
                }
            }
        else
            {
            return -1;
            }

        }
    else
        {
        return -1;
        }
    }




    @Override
    public String toString() {
        return String.valueOf(this.year) + "-" + String.valueOf(this.mes) + "-" + String.valueOf(this.dia);
    }





}