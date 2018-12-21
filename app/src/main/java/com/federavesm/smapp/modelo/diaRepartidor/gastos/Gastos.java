package com.federavesm.smapp.modelo.diaRepartidor.gastos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorProductos;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.diaRepartidor.cargas.Carga;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federico on 12/3/2018.
 */




public class Gastos extends GenericoReparto {



    public Gastos(Context context)
    {
        super(context);
        this.context = context;
    }

    private Context context;


    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public boolean actualizar()
    {
    this.dinero=0;
    for(int i=0;i<gastos.size();i++)
        {
        this.dinero+=gastos.get(i).getDinero();
        }
    return true;
    }



    private List<Gasto> gastos = new ArrayList<Gasto>();

    public float getDineroTotal(){return this.dinero;}

    private float dinero=0;






    @Override
    public boolean cargar()
    {
    try
        {
        gastos.clear();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Gastos WHERE idDiaRepartidor="+"'"+this.idDiaRepartidor+"'",null);
        boolean res = true;
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            Gasto gasto = new Gasto(this.context);
            gasto.setId(cursor.getInt(0));
            res &= gasto.cargar();
            gastos.add(gasto);
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
    for(int i = 0; i < this.gastos.size(); i++)
        {
        aux&=this.gastos.get(i).eliminar();
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


    public List<Gasto> getGastos() {
        return gastos;
    }

    public void setGastos(List<Gasto> gastos) {
        this.gastos = gastos;
    }


    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }


}