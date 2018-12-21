package com.federavesm.smapp.modelo.diaRepartidor.cargas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorProductos;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 21/2/2018.
 */



public class Descargas extends GenericoDiaRepartidorProductos {



    public Descargas(Context context)
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


    private Retornables retornablesVacios = new Retornables();

    public Retornables getRetornablesVacios()
    {
    return retornablesVacios;
    }



    private List<Descarga> descargas = new ArrayList<Descarga>();

    public List<Descarga> getDescargas() {
        return descargas;
    }

    public void setDescargas(List<Descarga> descargas) {
        this.descargas = descargas;
    }


    @Override
    public boolean modificar() {
        return false;
    }


    @Override
    public boolean cargar()
    {
    try
        {
        descargas.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Descargas WHERE idDiaRepartidor="+"'"+this.idDiaRepartidor+"'",null);
        boolean res = true;
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            Descarga descarga = new Descarga(this.context);
            descarga.setId(cursor.getInt(0));
            res &= descarga.cargar();
            descargas.add(descarga);
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
        for(int i = 0; i < this.descargas.size(); i++)
        {
            aux&=this.descargas.get(i).eliminar();
        }
        return aux;
    }



    @Override
    public boolean actualizar()
    {
    Descartables descartables = new Descartables();
    Retornables retornables = new Retornables();
    Retornables retornablesVacios = new Retornables();

    for(int i=0;i<descargas.size();i++)
        {
        retornables.setBidones20L(retornables.getBidones20L()+descargas.get(i).getRetornables().getBidones20L());
        retornablesVacios.setBidones20L(retornablesVacios.getBidones20L()+descargas.get(i).getRetornablesVacios().getBidones20L());
        retornables.setBidones12L(retornables.getBidones12L()+descargas.get(i).getRetornables().getBidones12L());
        retornablesVacios.setBidones12L(retornablesVacios.getBidones12L()+descargas.get(i).getRetornablesVacios().getBidones12L());
        descartables.setBidones10L(descartables.getBidones10L()+descargas.get(i).getDescartables().getBidones10L());
        descartables.setBidones8L(descartables.getBidones8L()+descargas.get(i).getDescartables().getBidones8L());
        descartables.setBidones5L(descartables.getBidones5L()+descargas.get(i).getDescartables().getBidones5L());
        descartables.setPackBotellas2L(descartables.getPackBotellas2L()+descargas.get(i).getDescartables().getPackBotellas2L());
        descartables.setPackBotellas500mL(descartables.getPackBotellas500mL()+descargas.get(i).getDescartables().getPackBotellas500mL());
        }

    this.retornables = retornables;
    this.retornablesVacios = retornablesVacios;
    this.descartables = descartables;
    return true;
    }


    @Override
    public boolean evaluar()
    {
    boolean aux = true;
    this.incoherencia="\n\n";

    if(Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones20L() < this.getRetornables().getBidones20L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornables().getBidones20L()+" bidones de 20L en el vehículo para descargar \n";
        }
    if(Comunicador.getDiaRepartidor().getCargamento().getRetornablesVacios().getBidones20L() < this.getRetornablesVacios().getBidones20L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornablesVacios().getBidones20L()+" bidones de 20L vacios en el vehículo para descargar \n";
        }

    if(Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones12L() < this.getRetornables().getBidones12L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornables().getBidones12L()+" bidones de 12L en el vehículo para descargar \n";
        }
    if(Comunicador.getDiaRepartidor().getCargamento().getRetornablesVacios().getBidones12L() < this.getRetornablesVacios().getBidones12L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getRetornablesVacios().getBidones12L()+" bidones de 12L vacios en el vehículo para descargar \n";
        }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones10L() < this.getDescartables().getBidones10L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getBidones10L()+" bidones de 10L en el vehículo para descargar \n";
        }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones8L() < this.getDescartables().getBidones8L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getBidones8L()+" bidones de 8L en el vehículo para descargar \n";
        }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones5L() < this.getDescartables().getBidones5L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getBidones5L()+" bidones de 5L en el vehículo para descargar \n";
        }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas2L() < this.getDescartables().getPackBotellas2L())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getPackBotellas2L()+" pack de botellas de 2L en el vehículo para descargar \n";
        }
    if(Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas500mL() < this.getDescartables().getPackBotellas500mL())
        {
        aux=false;
        this.incoherencia+="No hay "+this.getDescartables().getPackBotellas500mL()+" pack de botellas de 500mL en el vehículo para descargar \n";
        }
    return aux;
    }

    @Override
    public String getEvaluar() {
        return this.incoherencia;
    }


    @Override
    public String getXMLToSend() {
        return "";
    }


    @Override
    public boolean getEstado() {
        return false;
    }


    @Override
    public Object getCopia() {
        return null;
    }


    @Override
    public void copiar(Object object) {
    }



}