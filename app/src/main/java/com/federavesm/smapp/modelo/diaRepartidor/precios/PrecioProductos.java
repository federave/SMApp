package com.federavesm.smapp.modelo.diaRepartidor.precios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;

/**
 * Created by Federico on 4/2/2018.
 */

public abstract class PrecioProductos extends GenericoReparto {


    public PrecioProductos(Context context){super(context);}


    protected PrecioRetornables precioRetornables = new PrecioRetornables();
    protected PrecioDescartables precioDescartables = new PrecioDescartables();


    protected Fecha fecha;

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }


    public PrecioRetornables getPrecioRetornables() {
        return precioRetornables;
    }

    public void setPrecioRetornables(PrecioRetornables precioRetornables) {
        this.precioRetornables = precioRetornables;
    }

    public PrecioDescartables getPrecioDescartables() {
        return precioDescartables;
    }

    public void setPrecioDescartables(PrecioDescartables precioDescartables) {
        this.precioDescartables = precioDescartables;
    }

    @Override
    public Object getCopia() {
        return null;
    }


    public abstract boolean getEspecial();



    public boolean cargar(Fecha fecha)
    {
        this.fecha=fecha;
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM PrecioDiaRepartidor AS PDR INNER JOIN DiaRepartidor AS DR ON PDR.idDiaRepartidor = DR.id " +
                    "WHERE DR.fecha <="+"'"+this.fecha.toString()+"'"+" ORDER BY DR.fecha DESC",null);
            boolean aux=false;
            if(cursor.moveToFirst())
            {
                aux = true;
                this.id = cursor.getInt(0);
                this.idDiaRepartidor = cursor.getInt(1);
                this.precioRetornables.setBidon20L(cursor.getFloat(2));
                this.precioRetornables.setBidon12L(cursor.getFloat(3));
                this.precioDescartables.setBidon10L(cursor.getFloat(4));
                this.precioDescartables.setBidon8L(cursor.getFloat(5));
                this.precioDescartables.setBidon5L(cursor.getFloat(6));
                this.precioDescartables.setPackBotellas2L(cursor.getFloat(7));
                this.precioDescartables.setPackBotellas500mL(cursor.getFloat(8));
            }
            db.close();
            return aux;
        }
        catch (Exception e)
        {
            String x=e.toString();
            return false;
        }


    }






    @Override
    public void copiar(Object object)
    {
        try
        {
            PrecioProductos precioProductos = (PrecioProductos)object;
            this.id = precioProductos.getId();
            this.precioRetornables.copiar(precioProductos.getPrecioRetornables());
            this.precioDescartables.copiar(precioProductos.getPrecioDescartables());

        }
        catch (Exception e)
        {

        }
    }















}
