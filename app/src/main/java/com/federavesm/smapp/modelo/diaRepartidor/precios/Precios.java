package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;

/**
 * Created by Federico on 4/2/2018.
 */

public class Precios extends GenericoReparto {


    public Precios(Context context)
    {
    super(context);
    this.precioProductos = new PrecioNormalProductos(context);
    this.precioAlquileres = new PrecioNormalAlquileres(context);
    this.precioDispensadores = new PrecioDispensadores(context);
    }

    @Override
    public boolean modificar() {
        return false;
    }

    private PrecioProductos precioProductos;
    private PrecioAlquileres precioAlquileres;
    private PrecioDispensadores precioDispensadores;



    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues precios = new ContentValues();

        precios.put("idDiaRepartidor",this.idDiaRepartidor);

        precios.put("bidon20L",this.precioProductos.getPrecioRetornables().getBidon20L());
        precios.put("bidon12L",this.precioProductos.getPrecioRetornables().getBidon12L());
        precios.put("bidon10L",this.precioProductos.getPrecioDescartables().getBidon10L());
        precios.put("bidon8L",this.precioProductos.getPrecioDescartables().getBidon8L());
        precios.put("bidon5L",this.precioProductos.getPrecioDescartables().getBidon5L());
        precios.put("packBotellas2L",this.precioProductos.getPrecioDescartables().getPackBotellas2L());
        precios.put("packBotellas500mL",this.precioProductos.getPrecioDescartables().getPackBotellas500mL());
        precios.put("alquiler6Bidones",this.precioAlquileres.getAlquiler6Bidones());
        precios.put("alquiler8Bidones",this.precioAlquileres.getAlquiler8Bidones());
        precios.put("alquiler10Bidones",this.precioAlquileres.getAlquiler10Bidones());
        precios.put("alquiler12Bidones",this.precioAlquileres.getAlquiler12Bidones());
        precios.put("vertedor",this.precioDispensadores.getVertedor());
        precios.put("dispenser",this.precioDispensadores.getDispenser());

        boolean aux=true;
        if(db.insert("PrecioDiaRepartidor",null,precios) > 0)
            {
            this.id = getLastId("PrecioDiaRepartidor");
            }
            else
            {
            aux = false;
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }

    @Override
    public boolean eliminar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        boolean aux = false;
        if(db.delete("PrecioDiaRepartidor", "id=" + "'" + this.id + "'", null)>0)
            {aux = true;}
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }



    @Override
    public boolean evaluar() {
        return false;
    }

    @Override
    public boolean actualizar() {
        return false;
    }

    @Override
    public String getEvaluar() {
        return null;
    }

    @Override
    public String getXMLToSend() {
        return null;
    }


    @Override
    public boolean getEstado() {
        return false;
    }



    @Override
    public boolean cargar()
    {
    boolean aux = true;

    this.precioProductos.setIdDiaRepartidor(this.idDiaRepartidor);
    this.precioAlquileres.setIdDiaRepartidor(this.idDiaRepartidor);
    this.precioDispensadores.setIdDiaRepartidor(this.idDiaRepartidor);

    aux&=this.precioProductos.cargar();
    aux&=this.precioAlquileres.cargar();
    aux&=this.precioDispensadores.cargar();

    return aux;
    }


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }

    public PrecioProductos getPrecioProductos() {
        return precioProductos;
    }

    public void setPrecioProductos(PrecioProductos precioProductos) {
        this.precioProductos = precioProductos;
    }
    public PrecioAlquileres getPrecioAlquileres() {
        return precioAlquileres;
    }

    public void setPrecioAlquileres(PrecioAlquileres precioAlquileres) {
        this.precioAlquileres = precioAlquileres;
    }

    public PrecioDispensadores getPrecioDispensadores() {
        return precioDispensadores;
    }

    public void setPrecioDispensadores(PrecioDispensadores precioDispensadores) {
        this.precioDispensadores = precioDispensadores;
    }
}
