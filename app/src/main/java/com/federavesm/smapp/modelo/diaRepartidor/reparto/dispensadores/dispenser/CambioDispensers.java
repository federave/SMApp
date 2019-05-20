package com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.dispenser;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;






public class CambioDispensers extends GenericoDiaRepartidorEvaluar {



    public CambioDispensers(Context context)
    {
        super(context);
    }




    private int cantidad=0;



    @Override
    public String getXMLToSend()
    {
        XML xml = new XML();
        if(this.cantidad>0)
        {
            xml.startTag("CambioDispensers");
                xml.addTag("Cantidad",String.valueOf(this.cantidad));
            xml.closeTag("CambioDispensers");
        }
        return xml.getXML();
    }


    @Override
    public void copiar(Object object)
    {
        try
        {
            CambioDispensers cambioDispensers = (CambioDispensers)object;
            this.id = cambioDispensers.getId();
            this.cantidad = cambioDispensers.getCantidad();
        }
        catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
        CambioDispensers cambioDispensers = new CambioDispensers(this.context);
        cambioDispensers.copiar(this);
        return cambioDispensers;
    }


    public void limpiar()
    {
    this.cantidad=0;
    }

    public boolean have()
    {
        if(this.cantidad > 0)
            return true;
        else
            return false;
    }


    @Override
    public boolean getEstado()
    {
        boolean aux = true;
        if(this.cantidad>=0)
        {
        }
        else
        {
            aux=false;
        }
        return aux;
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

                Cursor cursor = db.rawQuery("SELECT * FROM CambioDispensers WHERE id=" + "'" + this.id + "'", null);
                if (cursor.moveToFirst())
                {
                    aux=true;
                    this.cantidad = cursor.getInt(1);
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
            boolean aux = true;

            ContentValues dato = new ContentValues();
            dato.put("cantidad",this.cantidad);
            if(db.insert("CambioDispensers",null,dato) > 0)
            {
                this.id = getLastId("CambioDispensers");
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
    public boolean modificar()
    {
        try
        {
            boolean aux = true;

            if (this.id > 0)
            {


                SQLiteDatabase db = getWritableDatabase();
                ContentValues dato = new ContentValues();
                dato.put("cantidad",this.cantidad);
                String whereClause = "id=?";
                String whereArgs[] = {String.valueOf(this.id)};
                if (!(db.update("CambioDispensers", dato, whereClause, whereArgs) > 0))
                {
                    aux = false;
                }
                db.close();

            }
            else
            {
                aux &= guardar();
            }

            aux &= Comunicador.getReparto().modificar();


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
            if(db.delete("CambioDispensers", "id=" + "'" + this.id + "'", null)>0)
            {
                aux = true;
            }
            db.close();
            this.id = -1;
            aux &= Comunicador.getReparto().modificar();
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
    public boolean evaluar()
    {
        incoherencia="";
        boolean aux = true;
        this.incoherencia="";
        int dispensersVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDispensers().getCantidad();
        if(dispensersVehiculo < this.cantidad)
        {
            aux = false;
            this.incoherencia += "No hay " + this.cantidad  + " Dispensers en el vehículo para cambiar \n";
        }
        return aux;
    }



    @Override
    public String getEvaluar() {
        return this.incoherencia;
    }



    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }




}

