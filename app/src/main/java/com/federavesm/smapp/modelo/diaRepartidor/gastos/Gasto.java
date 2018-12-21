package com.federavesm.smapp.modelo.diaRepartidor.gastos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Convertidor;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Federico on 12/3/2018.
 */




public class Gasto extends GenericoReparto {



    public Gasto(Context context)
    {
        super(context);
        this.context = context;
    }

    private Context context;



    private String descripcion="";
    private boolean combustible=false;
    private boolean combustibleConTarjeta=false;



    private float dinero=0;

    public void limpiar()
    {
    this.descripcion="";
    this.combustible=false;
    this.dinero=0;
    }


    @Override
    public boolean modificar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues gasto = new ContentValues();
        gasto.put("idDiaRepartidor",this.idDiaRepartidor);
        gasto.put("combustible",Convertidor.toInteger(this.combustible));
        gasto.put("combustibleConTarjeta",Convertidor.toInteger(this.combustibleConTarjeta));
        gasto.put("descripcion",this.descripcion);
        gasto.put("dinero",this.dinero);

        String whereClause = "id=?";
        String whereArgs[] = {String.valueOf(this.id)};

        boolean aux=true;
        if(!(db.update("Gastos", gasto, whereClause, whereArgs)> 0))
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
    public boolean actualizar()
    {
    return true;
    }



    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Gastos WHERE id="+"'"+this.id+"'",null);
        boolean aux=false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.id = cursor.getInt(0);
            this.idDiaRepartidor = cursor.getInt(1);
            this.combustible = Convertidor.toBoolean(cursor.getInt(2));
            this.combustibleConTarjeta = Convertidor.toBoolean(cursor.getInt(3));
            this.descripcion = cursor.getString(4);
            this.dinero = cursor.getFloat(5);
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
    public boolean guardar()
    {
    try
        {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues gasto = new ContentValues();
        gasto.put("idDiaRepartidor",this.idDiaRepartidor);
        gasto.put("combustible",Convertidor.toInteger(this.combustible));
        gasto.put("combustibleConTarjeta",Convertidor.toInteger(this.combustibleConTarjeta));
        gasto.put("descripcion",this.descripcion);
        gasto.put("dinero",this.dinero);

        boolean aux=true;
        if(db.insert("Gastos",null,gasto) > 0)
            {
            this.id = getLastId("Gastos");
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
        if(db.delete("Gastos", "id=" + "'" + this.id + "'", null)>0)
            {
            aux = true;
            }
        db.close();
        this.id = -1;
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }



    @Override
    public boolean evaluar()
    {
    if(combustible==false && descripcion=="" && combustibleConTarjeta==false)
        {
        return false;
        }
    else
        {
        if(combustibleConTarjeta==false)
            {
            if(dinero<=0)
                return false;
            else
                return true;
            }
        else
            {
            if(dinero>0)
                return false;
            else
                return true;
            }
        }
    }



    @Override
    public String getEvaluar() {
    this.incoherencia="";
    if(combustible==false && descripcion.length()==0 && combustibleConTarjeta==false)
        this.incoherencia+="\nDebe especificarse de que es el gasto";
    if(dinero<=0 && combustibleConTarjeta == false)
        this.incoherencia+="\nDebe ingresarse el dinero gastado";
    if(dinero >0 && combustibleConTarjeta)
        this.incoherencia+="\nNo debe ingresarse el dinero gastado, ya que es con tarjeta";


    return this.incoherencia;
    }


    @Override
    public String getXMLToSend()
    {
        XML xml = new XML();

        xml.startTag("Gasto");

        if(combustible)
            {
            xml.addTag("Combustible",String.valueOf(Convertidor.toInteger(combustible)));
            xml.addTag("Descripcion",descripcion);
            }
        else
            {
            xml.addTag("Otros","1");
            if(combustibleConTarjeta)
                {
                descripcion+=" Combustible con tarjeta";
                }
            xml.addTag("Descripcion",descripcion);
            }

            xml.addTag("Dinero",String.valueOf(dinero));

        xml.closeTag("Gasto");




        return xml.getXML();
    }





    @Override
    public boolean getEstado()
    {
    if( combustible == false && descripcion.length() == 0 && dinero==0 && combustibleConTarjeta == false)
        return false;
    else
        return true;
    }




    @Override
    public Object getCopia()
    {
    Gasto gasto = new Gasto(context);
    gasto.copiar(this);
    return gasto;
    }

    @Override
    public void copiar(Object object)
    {
    Gasto gasto = (Gasto)object;
    this.combustible = gasto.getCombustible();
    this.combustibleConTarjeta = gasto.getCombustibleConTarjeta();
    this.descripcion = gasto.getDescripcion();
    this.dinero = gasto.getDinero();
    this.id = gasto.getId();
    this.idDiaRepartidor = gasto.getIdDiaRepartidor();
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getCombustible() {
        return combustible;
    }

    public void setCombustible(boolean combustible) {
        this.combustible = combustible;
    }

    public float getDinero() {
        return dinero;
    }

    public void setDinero(float dinero) {
        this.dinero = dinero;
    }


    public boolean getCombustibleConTarjeta() {
        return combustibleConTarjeta;
    }

    public void setCombustibleConTarjeta(boolean combustibleConTarjeta) {
        this.combustibleConTarjeta = combustibleConTarjeta;
    }






    @Override
    public String toString() {

    if(this.combustible || this.combustibleConTarjeta)
        return "Combustible";
    else
        return descripcion;
    }





}