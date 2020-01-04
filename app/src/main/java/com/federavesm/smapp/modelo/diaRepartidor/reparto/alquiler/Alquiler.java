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
 * Created by Federico on 8/2/2018.
 */

public class Alquiler extends GenericoDiaRepartidorEvaluar {



    public Alquiler(Context context)
    {
    super(context);
    this.excedenteAlquiler = new ExcedenteAlquiler(context);
    this.pagoAlquiler = new PagoAlquiler(context);
    }



    public Retornables getRetornablesRepartidos()
    {
    Retornables retornables = new Retornables();
    retornables.setBidones20L(this.retornables.getBidones20L() + this.excedenteAlquiler.getRetornables().getBidones20L() + this.excedenteAlquiler.getRetornablesDeudados().getBidones20L());
    retornables.setBidones12L(this.retornables.getBidones12L() + this.excedenteAlquiler.getRetornables().getBidones12L() + this.excedenteAlquiler.getRetornablesDeudados().getBidones12L());
    return retornables;
    }


    @Override
    public String getXMLToSend() {

    XML xml = new XML();

    if(this.pagoAlquiler.have()||this.excedenteAlquiler.have() || this.retornables.have())
        {
        xml.startTag("Alquiler");

            xml.addValue(retornables.getXMLToSend());
            xml.addValue(excedenteAlquiler.getXMLToSend());
            xml.addValue(pagoAlquiler.getXMLToSend());


        xml.closeTag("Alquiler");

        }


        return xml.getXML();
    }




    public String getDatos()
    {

        String aux = "";

        if(this.retornables.have())
        {

            aux += retornables.getDatos();


        }

        if(this.pagoAlquiler.have()||this.excedenteAlquiler.have() || this.retornables.have())
        {

            aux += retornables.getDatos();
            aux += excedenteAlquiler.getDatos();
            aux += pagoAlquiler.getDatos();


        }




        return aux;
    }








    private PagoAlquiler pagoAlquiler;
    private ExcedenteAlquiler excedenteAlquiler;
    private Retornables retornables = new Retornables();


    public boolean getEntregaCompleta20L()
    {
    if(Comunicador.getReparto().getCliente().getDatosAlquiler().getAlquileres().getAlquileresBidones20L())
        {
        if(Comunicador.getReparto().getCliente().getDatosAlquiler().getRetornablesAEntregar().getBidones20L() == this.retornables.getBidones20L())
            return true;
        else
            return false;
        }
    else
        {
        return false;
        }
    }

    public boolean getEntregaCompleta12L()
    {
    if(Comunicador.getReparto().getCliente().getDatosAlquiler().getAlquileres().getAlquileresBidones12L())
        {
        if(Comunicador.getReparto().getCliente().getDatosAlquiler().getRetornablesAEntregar().getBidones12L() == this.retornables.getBidones12L())
            return true;
        else
            return false;
        }
    else
        {
        return false;
        }

    }


    public boolean haveEntrega()
    {
    return retornables.have();
    }


    public boolean have()
    {
    return retornables.have() || excedenteAlquiler.have();
    }

    @Override
    public boolean getEstado()
    {
    return this.retornables.getEstado() || this.excedenteAlquiler.getEstado();
    }



    @Override
    public void copiar(Object object)
    {
    try
        {
        Alquiler alquiler = (Alquiler)object;
        this.id = alquiler.getId();
        this.retornables.copiar(alquiler.getRetornables());
        this.pagoAlquiler.copiar(alquiler.getPagoAlquiler());
        this.excedenteAlquiler.copiar(alquiler.getExcedenteAlquiler());
        }
    catch (Exception e)
        {

        }
    }


    @Override
    public Object getCopia()
    {
    Alquiler alquiler = new Alquiler(context);
    alquiler.copiar(this);
    return alquiler;
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
            ContentValues alquiler = new ContentValues();
            alquiler.put("idExcedente",this.excedenteAlquiler.getId());
            alquiler.put("idPagoAlquiler",this.pagoAlquiler.getId());
            alquiler.put("bidones20L",this.retornables.getBidones20L());
            alquiler.put("bidones12L",this.retornables.getBidones12L());
            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};
            if (!(db.update("Alquiler", alquiler, whereClause, whereArgs) > 0))
                {
                aux = false;
                }
            db.close();
            aux&=Comunicador.getReparto().modificar();
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
    public boolean guardar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues alquiler = new ContentValues();
        alquiler.put("idExcedente",this.excedenteAlquiler.getId());
        alquiler.put("idPagoAlquiler",this.pagoAlquiler.getId());
        alquiler.put("bidones20L",this.retornables.getBidones20L());
        alquiler.put("bidones12L",this.retornables.getBidones12L());
        boolean aux = true;
        if(db.insert("Alquiler",null,alquiler) > 0)
            {
            this.id = getLastId("Alquiler");
            }
        else
            {
            aux = false;
            }
        db.close();
        aux&=Comunicador.getReparto().modificar();
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
        boolean aux = true;

        aux&=this.pagoAlquiler.eliminar();
        aux&=this.excedenteAlquiler.eliminar();

        SQLiteDatabase db = getWritableDatabase();

        if(!(db.delete("Alquiler", "id=" + "'" + this.id + "'", null)>0))
            {
            aux = false;
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
    public boolean cargar()
    {
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM Alquiler WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
            {
                aux=true;
                this.excedenteAlquiler.setId(cursor.getInt(1));
                this.pagoAlquiler.setId(cursor.getInt(2));
                this.retornables.setBidones20L(cursor.getInt(3));
                this.retornables.setBidones12L(cursor.getInt(4));
                this.excedenteAlquiler.cargar();
                this.pagoAlquiler.cargar();
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
    Comunicador.getReparto().actualizar();
    return true;
    }




    @Override
    public boolean evaluar()
    {
    incoherencia="";
    boolean aux = true;
    this.incoherencia="";

    Alquiler alquilerOld = Comunicador.getReparto().getAlquiler();
    int bidones20LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones20L() + alquilerOld.getRetornablesRepartidos().getBidones20L();
    int bidones12LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones12L() + alquilerOld.getRetornablesRepartidos().getBidones12L();


    if(this.getRetornablesRepartidos().getBidones20L()>0) {
        if (bidones20LEnVehiculo < (this.getRetornablesRepartidos().getBidones20L())) {
            aux = false;
            this.incoherencia += "No hay " + this.getRetornablesRepartidos().getBidones20L() + " bidones de 20L en el vehículo para entregar \n";
        }
    }

    if(this.getRetornablesRepartidos().getBidones12L()>0) {

        if (bidones12LEnVehiculo < (this.getRetornablesRepartidos().getBidones12L())) {
            aux = false;
            this.incoherencia += "No hay " + this.getRetornablesRepartidos().getBidones12L() + " bidones de 12L en el vehículo para entregar \n";
        }
    }

    return aux;
    }

    @Override
    public String getEvaluar() {
        return this.incoherencia;
    }





    public Retornables getRetornables() {
        return retornables;
    }

    public void setRetornables(Retornables retornables) {
        this.retornables = retornables;
    }




    public PagoAlquiler getPagoAlquiler() {
        return pagoAlquiler;
    }

    public void setPagoAlquiler(PagoAlquiler pagoAlquiler) {
        this.pagoAlquiler = pagoAlquiler;
    }

    public ExcedenteAlquiler getExcedenteAlquiler() {
        return excedenteAlquiler;
    }

    public void setExcedenteAlquiler(ExcedenteAlquiler excedenteAlquiler) {
        this.excedenteAlquiler = excedenteAlquiler;
    }








}
