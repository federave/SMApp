package com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.vertedores;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;



public class EntregaVertedores extends GenericoDiaRepartidorEvaluar {



    public EntregaVertedores(Context context)
    {
        super(context);
    }






    @Override
    public String getXMLToSend() {

        XML xml = new XML();

        return xml.getXML();
    }
















    @Override
    public void copiar(Object object)
    {
        try
        {
            /*
            PagoAlquiler pagoAlquiler = (PagoAlquiler)object;
            this.id = pagoAlquiler.getId();
            this.alquileres.copiar(pagoAlquiler.getAlquileres());
            */
        }
        catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {/*
        PagoAlquiler pagoAlquiler = new PagoAlquiler(context);
        pagoAlquiler.copiar(this);
        return pagoAlquiler;

        */
        return new Object();
    }





    public void limpiar()
    {
    }

    public boolean have()
    {
        return true;
    }









    @Override
    public boolean cargar()
    {
        if (this.id > 0)
        {
            try
            {
                SQLiteDatabase db = getReadableDatabase();
                boolean aux=false;

                /*
                Cursor cursor = db.rawQuery("SELECT * FROM PagoAlquiler WHERE id=" + "'" + this.id + "'", null);
                if (cursor.moveToFirst())
                {
                    aux=true;

                }
                */

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
            boolean aux = true;

            /*
            ContentValues pagoAlquiler = new ContentValues();
            pagoAlquiler.put("alquileres6Bidones",this.alquileres.getAlquileres6Bidones());
            pagoAlquiler.put("alquileres8Bidones",this.alquileres.getAlquileres8Bidones());
            pagoAlquiler.put("alquileres10Bidones",this.alquileres.getAlquileres10Bidones());
            pagoAlquiler.put("alquileres12Bidones",this.alquileres.getAlquileres12Bidones());
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

            */

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
                /*

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

                */
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
            /*
            if(db.delete("PagoAlquiler", "id=" + "'" + this.id + "'", null)>0)
            {
                aux = true;
            }
            db.close();
            this.id = -1;
            aux &= Comunicador.getReparto().getAlquiler().modificar();
            */
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
        return true;
    }



}

