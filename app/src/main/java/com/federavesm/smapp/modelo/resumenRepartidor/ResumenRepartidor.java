package com.federavesm.smapp.modelo.resumenRepartidor;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Conector;
import com.federavesm.smapp.modelo.Fecha;

public class ResumenRepartidor extends Conector{




    public ResumenRepartidor(Context context)
    {
    super(context);
    }



    protected int idRepartidor;
    protected Fecha fechaInicio;
    protected Fecha fechaFin;


    protected int totalBidones20LVendidos=0;
    protected float dineroBidones20LVendidos=0;


    public void cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();

        //Venta Total Productos

        Cursor cursor = db.rawQuery("SELECT SUM(VP.bidones20L),SUM(PDR.bidon20L*VP.bidones20L) FROM DiaRepartidor AS DR INNER JOIN PrecioDiaRepartidor as PDR ON DR.id=PDR.idDiaRepartidor INNER JOIN Repartos as R ON DR.id=R.idDiaRepartidor INNER JOIN VentaProductos AS VP ON R.idVentaProductos=VP.id WHERE DR.fecha>="+"'"+this.fechaInicio+"'"+"AND DR.fecha<="+"'"+this.fechaFin+"'",null);
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            totalBidones20LVendidos+=cursor.getInt(0);
            dineroBidones20LVendidos+=cursor.getFloat(1);
            aux = cursor.moveToNext();
            }





        }
    catch(Exception e)
        {

        }
    }






    public int getTotalBidones20LVendidos() {
        return totalBidones20LVendidos;
    }

    public float getDineroBidones20LVendidos() {
        return dineroBidones20LVendidos;
    }

    public int getIdRepartidor() {
        return idRepartidor;
    }

    public void setIdRepartidor(int idRepartidor) {
        this.idRepartidor = idRepartidor;
    }

    public Fecha getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Fecha fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Fecha getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Fecha fechaFin) {
        this.fechaFin = fechaFin;
    }








}
