package com.federavesm.smapp.modelo.diaRepartidor.reparto.tipoVisita;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;

import com.federavesm.smapp.R;
import com.federavesm.smapp.modelo.Generico;
import com.federavesm.smapp.modelo.GenericoVista;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

import java.util.Objects;

/**
 * Created by Federico on 13/2/2018.
 */


public class TipoVisita extends GenericoVista {



    public TipoVisita(Context context)
    {
        super(context);
    }

    public TipoVisita(Context context,int id)
    {

    super(context);
    this.id=id;

    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM TipoVisita WHERE id="+"'"+this.id+"'",null);
        if(cursor.moveToFirst())
            {
            this.tipoVisita = cursor.getString(1);
            }
        db.close();
        }
    catch (Exception e)
        {
        }

    }


    private String tipoVisita;


    private boolean pagoAlquiler=false;
    private boolean entrega=false;
    private boolean vacios=false;

    private boolean vertedores=false;
    private boolean dispensers=false;


    public boolean getVertedores() {
        return vertedores;
    }

    public void setVertedores(boolean vertedores) {
        this.vertedores = vertedores;
    }

    public boolean getDispensers() {
        return dispensers;
    }

    public void setDispensers(boolean dispensers) {
        this.dispensers = dispensers;
    }

    public boolean getPagoAlquiler() {
        return pagoAlquiler;
    }
    public void setPagoAlquiler(boolean pagoAlquiler) {
        this.pagoAlquiler = pagoAlquiler;
    }
    public boolean getEntrega() {
        return entrega;
    }
    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }
    public boolean getVacios() {
        return vacios;
    }
    public void setVacios(boolean vacios) {
        this.vacios = vacios;
    }

    @Override
    public void copiar(Object object)
    {
    try
        {
        TipoVisita tipoVisita = (TipoVisita)object;
        this.id = tipoVisita.getId();
        this.tipoVisita = tipoVisita.getTipoVisita();
        this.pagoAlquiler = tipoVisita.getPagoAlquiler();
        this.entrega = tipoVisita.getEntrega();
        this.vacios = tipoVisita.getVacios();
        this.vertedores = tipoVisita.getVertedores();
        this.dispensers = tipoVisita.getDispensers();



        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
        TipoVisita tipoVisita = new TipoVisita(context);
        tipoVisita.copiar(this);
        return tipoVisita;
    }


    @Override
    public boolean equals(Object objetc) {

        try
            {
            TipoVisita tipoVisita = (TipoVisita)objetc;

            if(this.id == tipoVisita.getId() && this.pagoAlquiler==tipoVisita.getPagoAlquiler() && this.entrega==tipoVisita.getEntrega() && this.vacios==tipoVisita.getVacios() && this.vertedores==tipoVisita.getVertedores() && this.dispensers==tipoVisita.getDispensers())
                return true;
            else
                return false;

            }
        catch (Exception e)
            {
            return false;
            }



    }



    public String getXMLToSend()
    {
        XML xml = new XML();

        xml.startTag("TipoVisita");
            xml.addTag("IdVisita",String.valueOf(this.id));
        xml.closeTag("TipoVisita");

        return xml.getXML();
    }





    public String getTipoVisita() {
        return tipoVisita;
    }

    public void setTipoVisita(String tipoVisita) {
        this.tipoVisita = tipoVisita;
    }

    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues tipoVisita = new ContentValues();
        tipoVisita.put("id",this.id);
        tipoVisita.put("tipoVisita",this.tipoVisita);

        long x = db.insert("TipoVisita",null,tipoVisita);
        boolean aux = true;
        if(x==0){aux = false;}
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }


    @Override
    public boolean modificar()
    {
        return false;
    }



    @Override
    public boolean eliminar() {
        return false;
    }

    @Override
    public boolean actualizar() {
        return false;
    }


    @Override
    public int getRecursoImagen()
    {

    switch (this.id)
        {
        case 1:
            {
            return R.drawable.clienteatendido;
            }
        case 3:
            {
            return R.drawable.clientenoestaba;
            }
        default:
            {
            return -1;
            }
        }
    }


    public int getRecursoPagoAlquiler()
    {
    return R.drawable.conpagoalquiler;
    }
    public int getRecursoEntrega()
    {
    return R.drawable.conentrega;
    }
    public int getRecursoVacios()
    {
    return R.drawable.convacios;
    }

    public int getRecursoVertedores(){
        return R.drawable.convacios;
    }
    public int getRecursoDispensers()
    {
        return R.drawable.convacios;
    }


    @Override
    public boolean cargar()
    {
    if(this.id > 0)
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM TipoVisita WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
                {
                aux = true;
                this.tipoVisita = cursor.getString(1);
                }
            db.close();
            return aux;
            }
        catch (Exception e)
            {
            return false;
            }
        }
    else
        {

            try
            {
                this.id=2;
                SQLiteDatabase db = getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM TipoVisita WHERE id="+"'"+this.id+"'",null);
                boolean aux = false;
                if(cursor.moveToFirst())
                {                aux = true;

                    this.tipoVisita = cursor.getString(1);
                }
                db.close();
                return aux;
            }
            catch (Exception e)
            {
                return false;
            }
        }
    }







}
