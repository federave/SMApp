package com.federavesm.smapp.modelo.diaRepartidor.reparto.alquiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */



public class ExcedenteAlquiler extends GenericoDiaRepartidorEvaluar {



    public ExcedenteAlquiler(Context context)
    {
        super(context);
    }



    private Retornables retornables = new Retornables();
    private Retornables retornablesDeudados = new Retornables();



    @Override
    public String getXMLToSend()
    {
    XML xml = new XML();

    if(this.have())
        {
        xml.startTag("ExcedenteAlquiler");

        if(retornables.have())
            {
            xml.startTag("Excedente");
                xml.addValue(retornables.getXMLToSend());
                xml.addTag("Dinero",String.valueOf(dinero));
            xml.closeTag("Excedente");
            }
        if(retornablesDeudados.have())
            {
            xml.startTag("Deuda");
                xml.addValue(retornablesDeudados.getXMLToSend());
            xml.closeTag("Deuda");
            }
        xml.closeTag("ExcedenteAlquiler");
        }


    return xml.getXML();
    }





    public String getDatos()
    {

        String aux = "";



        if(this.have())
        {

            if(retornables.have())
            {

                aux += "\n Excedente";
                aux += "\n" + retornables.getDatos();
                aux += "\n Dinero: " + String.valueOf(dinero);

            }
            if(retornablesDeudados.have())
            {

                aux += "\n Deuda";
                aux += "\n" + retornablesDeudados.getDatos();


            }
        }




        return aux;
    }















    public void limpiar()
    {
    this.retornables = new Retornables();
    this.retornablesDeudados = new Retornables();
    }


    public float getDineroDeuda()
    {
        float dinero20L = this.retornablesDeudados.getBidones20L() * Comunicador.getCliente().getPrecioProductos().getPrecioRetornables().getBidon20L();
        float dinero12L = this.retornablesDeudados.getBidones12L() * Comunicador.getCliente().getPrecioProductos().getPrecioRetornables().getBidon12L();
        return dinero20L + dinero12L;
    }


    @Override
    public boolean guardar()
    {
        try
        {
            SQLiteDatabase db = getWritableDatabase();
            ContentValues excedente = new ContentValues();
            excedente.put("bidones20L",this.retornables.getBidones20L());
            excedente.put("bidones20LDeudados",this.retornablesDeudados.getBidones20L());
            excedente.put("bidones12L",this.retornables.getBidones12L());
            excedente.put("bidones12LDeudados",this.retornablesDeudados.getBidones12L());
            boolean aux = true;
            if(db.insert("ExcedenteAlquiler",null,excedente) > 0)
            {
                this.id = getLastId("ExcedenteAlquiler");
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
                ContentValues excedente = new ContentValues();
                excedente.put("bidones20L",this.retornables.getBidones20L());
                excedente.put("bidones20LDeudados",this.retornablesDeudados.getBidones20L());
                excedente.put("bidones12L",this.retornables.getBidones12L());
                excedente.put("bidones12LDeudados",this.retornablesDeudados.getBidones12L());
                String whereClause = "id=?";
                String whereArgs[] = {String.valueOf(this.id)};
                if (!(db.update("ExcedenteAlquiler", excedente, whereClause, whereArgs) > 0))
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
    public void copiar(Object object)
    {
    try
        {
        ExcedenteAlquiler excedenteAlquiler = (ExcedenteAlquiler)object;
        this.id = excedenteAlquiler.getId();
        this.retornables.copiar(excedenteAlquiler.getRetornables());
        this.retornablesDeudados.copiar(excedenteAlquiler.getRetornablesDeudados());
        this.dinero = excedenteAlquiler.getDinero();
        }
    catch (Exception e)
        {
        }
    }


    @Override
    public Object getCopia()
    {
    ExcedenteAlquiler excedenteAlquiler = new ExcedenteAlquiler(context);
    excedenteAlquiler.copiar(this);
    return excedenteAlquiler;
    }




    public float getDineroVenta()
    {
    float dinero20L = this.retornables.getBidones20L() * Comunicador.getCliente().getPrecioProductos().getPrecioRetornables().getBidon20L();
    float dinero12L = this.retornables.getBidones12L() * Comunicador.getCliente().getPrecioProductos().getPrecioRetornables().getBidon12L();
    this.dinero = dinero20L + dinero12L ;
    return dinero;
    }

    public void setDineroVenta(){getDineroVenta();};


    public float getDinero() {
        return dinero;
    }

    private float dinero;


    public boolean have()
    {
    return retornables.have() || retornablesDeudados.have();
    }



    @Override
    public boolean getEstado() {
        return this.retornables.getEstado() || this.retornablesDeudados.getEstado();
    }




    @Override
    public boolean cargar()
    {
    if(this.id > 0 )
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM ExcedenteAlquiler WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
                {
                aux = true;
                this.retornables.setBidones20L(cursor.getInt(1));
                this.retornables.setBidones12L(cursor.getInt(2));
                this.retornablesDeudados.setBidones20L(cursor.getInt(3));
                this.retornablesDeudados.setBidones12L(cursor.getInt(4));
                setDineroVenta();
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
    public boolean eliminar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        boolean aux = false;
        if(db.delete("ExcedenteAlquiler", "id=" + "'" + this.id + "'", null)>0)
            {aux=true;}
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
    public boolean actualizar() {
        return false;
    }




    @Override
    public boolean evaluar()
    {
    return false;
    }




    @Override
    public String getEvaluar() {
        return "";
    }







    public Retornables getRetornables() {
        return retornables;
    }

    public void setRetornables(Retornables retornables) {
        this.retornables = retornables;
    }

    public Retornables getRetornablesDeudados() {
        return retornablesDeudados;
    }

    public void setRetornablesDeudados(Retornables retornablesDeudados) {
        this.retornablesDeudados = retornablesDeudados;
    }



}
