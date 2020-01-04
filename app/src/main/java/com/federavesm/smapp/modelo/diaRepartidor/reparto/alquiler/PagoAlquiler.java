package com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioAlquileres;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Alquileres;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */


public class PagoAlquiler extends GenericoDiaRepartidorEvaluar {



    public PagoAlquiler(Context context)
    {
        super(context);
    }


    private PrecioAlquileres precioAlquileres;

    public PrecioAlquileres getPrecioAlquileres() {
        return precioAlquileres;
    }

    public void setPrecioAlquileres(PrecioAlquileres precioAlquileres) {
        this.precioAlquileres = precioAlquileres;
    }

    public float getDineroPagos()
    {
    return this.alquileres.getAlquileres6Bidones() * Comunicador.getReparto().getCliente().getDatosAlquiler().getPrecioAlquileres().getAlquiler6Bidones() + this.alquileres.getAlquileres8Bidones() * Comunicador.getReparto().getCliente().getDatosAlquiler().getPrecioAlquileres().getAlquiler8Bidones() + this.alquileres.getAlquileres10Bidones() * Comunicador.getReparto().getCliente().getDatosAlquiler().getPrecioAlquileres().getAlquiler10Bidones() +this.alquileres.getAlquileres12Bidones() * Comunicador.getReparto().getCliente().getDatosAlquiler().getPrecioAlquileres().getAlquiler12Bidones();
    }



    private Alquileres alquileres = new Alquileres();




    @Override
    public String getXMLToSend() {

        XML xml = new XML();

        if(this.alquileres.have())
        {
            xml.startTag("PagoAlquiler");
                xml.addValue(alquileres.getXMLToSend());
            xml.closeTag("PagoAlquiler");

        }


        return xml.getXML();
    }





    public String getDatos()
    {

        String aux = "";


        if(this.alquileres.have())
        {
            aux += "\n PagoAlquiler";
            aux += alquileres.getDatos();


        }



        return aux;
    }













    @Override
    public void copiar(Object object)
    {
    try
        {
        PagoAlquiler pagoAlquiler = (PagoAlquiler)object;
        this.id = pagoAlquiler.getId();
        this.alquileres.copiar(pagoAlquiler.getAlquileres());
        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
    PagoAlquiler pagoAlquiler = new PagoAlquiler(context);
    pagoAlquiler.copiar(this);
    return pagoAlquiler;
    }





    public void limpiar()
    {
    this.alquileres = new Alquileres();
    }

    public boolean have()
    {
    return this.alquileres.have();
    }









    @Override
    public boolean cargar()
    {
    if (this.id > 0)
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM PagoAlquiler WHERE id=" + "'" + this.id + "'", null);
            boolean aux=false;
            if (cursor.moveToFirst())
                {
                aux=true;
                this.alquileres.setAlquileres6Bidones(cursor.getInt(1));
                this.alquileres.setAlquileres8Bidones(cursor.getInt(2));
                this.alquileres.setAlquileres10Bidones(cursor.getInt(3));
                this.alquileres.setAlquileres12Bidones(cursor.getInt(4));
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
        return true;
        }
    }





    @Override
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues pagoAlquiler = new ContentValues();
        pagoAlquiler.put("alquileres6Bidones",this.alquileres.getAlquileres6Bidones());
        pagoAlquiler.put("alquileres8Bidones",this.alquileres.getAlquileres8Bidones());
        pagoAlquiler.put("alquileres10Bidones",this.alquileres.getAlquileres10Bidones());
        pagoAlquiler.put("alquileres12Bidones",this.alquileres.getAlquileres12Bidones());
        boolean aux = true;
        if(db.insert("PagoAlquiler",null,pagoAlquiler) > 0)
            {
            this.id = getLastId("PagoAlquiler");
            }
        else
            {
            aux = false;
            }
        db.close();
        aux &= Comunicador.getReparto().getAlquiler().modificar();
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
    try
        {
        boolean aux = true;
        if (this.id > 0)
            {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues pagoAlquiler = new ContentValues();
            pagoAlquiler.put("alquileres6Bidones",this.alquileres.getAlquileres6Bidones());
            pagoAlquiler.put("alquileres8Bidones",this.alquileres.getAlquileres8Bidones());
            pagoAlquiler.put("alquileres10Bidones",this.alquileres.getAlquileres10Bidones());
            pagoAlquiler.put("alquileres12Bidones",this.alquileres.getAlquileres12Bidones());
            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};
            if (!(db.update("PagoAlquiler", pagoAlquiler, whereClause, whereArgs) > 0))
                {
                aux = false;
                }
            db.close();
            aux &= Comunicador.getReparto().getAlquiler().modificar();
            }
        else
            {
            aux &= guardar();
            }
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
        if(db.delete("PagoAlquiler", "id=" + "'" + this.id + "'", null)>0)
            {
            aux = true;
            }
        db.close();
        this.id = -1;
        aux &= Comunicador.getReparto().getAlquiler().modificar();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }
    @Override
    public boolean actualizar() {
        return false;
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
    public boolean getEstado()
    {
    if(this.alquileres.getAlquileres6Bidones() >= 0 && this.alquileres.getAlquileres8Bidones() >= 0 && this.alquileres.getAlquileres10Bidones() >= 0 && this.alquileres.getAlquileres12Bidones() >= 0)
        {
        return true;
        }
    else
        {
        return false;
        }
    }



    public Alquileres getAlquileres() {
        return alquileres;
    }

    public void setAlquileres(Alquileres alquileres) {
        this.alquileres = alquileres;
    }

}