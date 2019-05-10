package com.federavesm.smapp.modelo.diaRepartidor.reparto.dispensadores.vertedores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.Convertidor;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;



public class VentaVertedores extends GenericoDiaRepartidorEvaluar {



    public VentaVertedores(Context context)
    {
        super(context);
    }





    private boolean especial=false;
    private float precioespecial=0;
    private int cantidad=0;


    public float getDinero()
    {
    if(especial)
        {
        return precioespecial*cantidad;
        }
    else
        {
        return Comunicador.getReparto().getCliente().getPrecioDispensadores().getVertedor() * cantidad;
        }
    }


    @Override
    public String getXMLToSend()
    {
        XML xml = new XML();
        if(this.cantidad>0)
        {
            xml.startTag("VentaVertedores");
                xml.addTag("Cantidad",String.valueOf(this.cantidad));
                xml.addTag("Especial",String.valueOf(this.especial));
                xml.addTag("PrecioEspecial",String.valueOf(this.precioespecial));
            xml.closeTag("VentaVertedores");
        }
        return xml.getXML();
    }


    @Override
    public void copiar(Object object)
    {
        try
        {
            VentaVertedores ventaVertedores = (VentaVertedores)object;
            this.id = ventaVertedores.getId();
            this.cantidad = ventaVertedores.getCantidad();
            this.especial = ventaVertedores.getEspecial();
            this.precioespecial = ventaVertedores.getPrecioespecial();
        }
        catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
    VentaVertedores ventaVertedores = new VentaVertedores(this.context);
    ventaVertedores.copiar(this);
    return ventaVertedores;
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

                Cursor cursor = db.rawQuery("SELECT * FROM VentaVertedores WHERE id=" + "'" + this.id + "'", null);
                if (cursor.moveToFirst())
                {
                    aux=true;
                    this.cantidad = cursor.getInt(1);
                    this.especial = Convertidor.toBoolean(cursor.getInt(2));
                    this.precioespecial = cursor.getFloat(3);
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
            dato.put("especial",this.especial);
            dato.put("precioespecial",this.precioespecial);
            if(db.insert("VentaVertedores",null,dato) > 0)
            {
                this.id = getLastId("VentaVertedores");
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
                dato.put("especial",this.especial);
                dato.put("precioespecial",this.precioespecial);
                String whereClause = "id=?";
                String whereArgs[] = {String.valueOf(this.id)};
                if (!(db.update("VentaVertedores", dato, whereClause, whereArgs) > 0))
                {
                    aux = false;
                }
                db.close();
                aux &= Comunicador.getReparto().modificar();

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
            if(db.delete("VentaVertedores", "id=" + "'" + this.id + "'", null)>0)
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
        int vertedoresVehiculo = Comunicador.getDiaRepartidor().getCargamento().getVertedores().getCantidad();
        if(vertedoresVehiculo < this.cantidad)
        {
            aux = false;
            this.incoherencia += "No hay " + this.cantidad  + " Vertedores en el vehículo para vender \n";
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

    public boolean getEspecial() {
        return especial;
    }

    public void setEspecial(boolean especial) {
        this.especial = especial;
    }

    public float getPrecioespecial() {
        return precioespecial;
    }

    public void setPrecioespecial(float precioespecial) {
        this.precioespecial = precioespecial;
    }

}

