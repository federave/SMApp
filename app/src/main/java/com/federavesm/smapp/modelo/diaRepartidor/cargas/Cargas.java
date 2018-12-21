package com.federavesm.smapp.modelo.diaRepartidor.cargas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorProductos;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.Reparto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 21/2/2018.
 */


public class Cargas extends GenericoDiaRepartidorProductos {



    public Cargas(Context context)
    {
        super(context);
        this.context = context;
    }

    private Context context;


    public Retornables getRetornables()
    {
    return this.retornables;
    }

    public Descartables getDescartables()
    {
    return this.descartables;
    }

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public boolean actualizar()
    {
    Descartables descartables = new Descartables();
    Retornables retornables = new Retornables();

    for(int i=0;i<cargas.size();i++)
        {
        retornables.setBidones20L(retornables.getBidones20L()+cargas.get(i).getRetornables().getBidones20L());
        retornables.setBidones12L(retornables.getBidones12L()+cargas.get(i).getRetornables().getBidones12L());
        descartables.setBidones10L(descartables.getBidones10L()+cargas.get(i).getDescartables().getBidones10L());
        descartables.setBidones8L(descartables.getBidones8L()+cargas.get(i).getDescartables().getBidones8L());
        descartables.setBidones5L(descartables.getBidones5L()+cargas.get(i).getDescartables().getBidones5L());
        descartables.setPackBotellas2L(descartables.getPackBotellas2L()+cargas.get(i).getDescartables().getPackBotellas2L());
        descartables.setPackBotellas500mL(descartables.getPackBotellas500mL()+cargas.get(i).getDescartables().getPackBotellas500mL());
        }

    this.retornables = retornables;
    this.descartables = descartables;


    return true;
    }


    private List<Carga> cargas = new ArrayList<Carga>();



    @Override
    public boolean cargar()
    {
    try
        {
        cargas.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Cargas WHERE idDiaRepartidor="+"'"+this.idDiaRepartidor+"'",null);
        boolean res = true;
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            Carga carga = new Carga(this.context);
            carga.setId(cursor.getInt(0));
            res &= carga.cargar();
            cargas.add(carga);
            aux = cursor.moveToNext();
            }
        db.close();
        actualizar();
        return res;
        }
    catch (Exception e)
        {
        String x=e.toString();
        return false;
        }
    }


    @Override
    public boolean guardar()
    {
    return true;
    }

    @Override
    public boolean eliminar()
    {
        boolean aux=true;
        for(int i = 0; i < this.cargas.size(); i++)
        {
            aux&=this.cargas.get(i).eliminar();
        }
        return aux;
    }



    @Override
    public boolean evaluar() {
        return false;
    }

    @Override
    public String getEvaluar() {
        return "";
    }


    @Override
    public String getXMLToSend() {
        return "";
    }


    @Override
    public boolean getEstado() {
        return false;
    }


    public List<Carga> getCargas() {
        return cargas;
    }

    public void setCargas(List<Carga> cargas) {
        this.cargas = cargas;
    }


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }


}