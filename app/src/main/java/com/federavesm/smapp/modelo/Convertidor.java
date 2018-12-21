package com.federavesm.smapp.modelo;

/**
 * Created by Federico on 15/3/2018.
 */

public class Convertidor {


public static boolean toBoolean(int dato)
{
    if(dato==1)
        return true;
    else
        return false;
}

public static int toInteger(boolean dato)
{
    if(dato)
        return 1;
    else
        return 0;
}









}
