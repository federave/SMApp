package com.federavesm.smapp.modelo.diaRepartidor.reparto.deudaProductos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.RepartoProductos;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */


public class DeudaProductos extends RepartoProductos {



    public DeudaProductos(Context context)
    {
        super(context);
    }








    @Override
    public void copiar(Object object)
    {
    try
        {
        super.copiar(object);
        DeudaProductos deudaProductos = (DeudaProductos)object;
        this.id = deudaProductos.getId();
        this.retornables.copiar(deudaProductos.getRetornables());
        this.descartables.copiar(deudaProductos.getDescartables());
        }
    catch (Exception e)
        {

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

            ContentValues deudaProductos = new ContentValues();

            deudaProductos.put("bidones20L",this.retornables.getBidones20L());
            deudaProductos.put("bidones12L",this.retornables.getBidones12L());
            deudaProductos.put("bidones10L",this.descartables.getBidones10L());
            deudaProductos.put("bidones8L",this.descartables.getBidones8L());
            deudaProductos.put("bidones5L",this.descartables.getBidones5L());
            deudaProductos.put("packBotellas2L",this.descartables.getPackBotellas2L());
            deudaProductos.put("packBotellas500mL",this.descartables.getPackBotellas500mL());

            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};

            if (!(db.update("DeudaProductos", deudaProductos, whereClause, whereArgs) > 0))
                {
                aux = false;
                }
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
    public Object getCopia()
    {
    DeudaProductos deudaProductos = new DeudaProductos(context);
    deudaProductos.copiar(this);
    return deudaProductos;
    }


    @Override
    public boolean cargar()
    {
    if(this.id > 0)
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM DeudaProductos WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
                {
                aux=true;
                this.retornables.setBidones20L(cursor.getInt(1));
                this.retornables.setBidones12L(cursor.getInt(2));
                this.descartables.setBidones10L(cursor.getInt(3));
                this.descartables.setBidones8L(cursor.getInt(4));
                this.descartables.setBidones5L(cursor.getInt(5));
                this.descartables.setPackBotellas2L(cursor.getInt(6));
                this.descartables.setPackBotellas500mL(cursor.getInt(7));
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

        ContentValues deudaProductos = new ContentValues();

        deudaProductos.put("bidones20L",this.retornables.getBidones20L());
        deudaProductos.put("bidones12L",this.retornables.getBidones12L());
        deudaProductos.put("bidones10L",this.descartables.getBidones10L());
        deudaProductos.put("bidones8L",this.descartables.getBidones8L());
        deudaProductos.put("bidones5L",this.descartables.getBidones5L());
        deudaProductos.put("packBotellas2L",this.descartables.getPackBotellas2L());
        deudaProductos.put("packBotellas500mL",this.descartables.getPackBotellas500mL());

        boolean aux = true;
        if(db.insert("DeudaProductos",null,deudaProductos) > 0)
            {
            this.id = getLastId("DeudaProductos");
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

    public void borrarDatos()
    {
        this.retornables = new Retornables();
        this.descartables = new Descartables();
        this.dinero = 0;

    }

    @Override
    public boolean eliminar()
    {
    try
        {
        SQLiteDatabase db = getWritableDatabase();
        boolean aux=false;
        if(db.delete("DeudaProductos", "id=" + "'" + this.id + "'", null)>0)
            {
            aux=true;
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

    public boolean have()
    {
        return this.retornables.have() || this.descartables.have();
    }



    @Override
    public boolean evaluar()
    {

    incoherencia="";
    boolean aux = true;
    this.incoherencia="";

    int bidones20LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones20L() + Comunicador.getDeudaProductosAux().getRetornables().getBidones20L();
    int bidones12LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones12L() + Comunicador.getDeudaProductosAux().getRetornables().getBidones12L();

    int bidones10LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones10L() + Comunicador.getDeudaProductosAux().getDescartables().getBidones10L();
    int bidones8LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones8L() + Comunicador.getDeudaProductosAux().getDescartables().getBidones8L();
    int bidones5LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones5L() + Comunicador.getDeudaProductosAux().getDescartables().getBidones5L();
    int packBotellas2LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas2L() + Comunicador.getDeudaProductosAux().getDescartables().getPackBotellas2L();
    int packBotellas500mLEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas500mL() + Comunicador.getDeudaProductosAux().getDescartables().getPackBotellas500mL();



    if(this.getRetornables().getBidones20L()>0)
        {
        if(bidones20LEnVehiculo < (this.getRetornables().getBidones20L()))
            {
            aux=false;
            this.incoherencia+="No hay "+(this.getRetornables().getBidones20L())+" bidones de 20L en el vehículo para descargar \n";
            }
        }

    if(this.getRetornables().getBidones12L()>0)
        {
        if(bidones12LEnVehiculo < (this.getRetornables().getBidones12L() ))
            {
            aux=false;
            this.incoherencia+="No hay "+(this.getRetornables().getBidones12L() )+" bidones de 12L en el vehículo para descargar \n";
            }
        }

    if(this.getDescartables().getBidones10L()>0)
        {
        if(bidones10LEnVehiculo < (this.getDescartables().getBidones10L()  ))
            {
            aux=false;
            this.incoherencia+="No hay "+(this.getDescartables().getBidones10L() )+" bidones de 10L en el vehículo para descargar \n";
            }
        }

    if(this.getDescartables().getBidones8L()>0)
        {
        if(bidones8LEnVehiculo < (this.getDescartables().getBidones8L() ))
            {
            aux=false;
            this.incoherencia+="No hay "+(this.getDescartables().getBidones8L() )+" bidones de 8L en el vehículo para descargar \n";
            }
        }

    if(this.getDescartables().getBidones5L()>0)
        {
        if(bidones5LEnVehiculo < (this.getDescartables().getBidones5L() ))
            {
            aux=false;
            this.incoherencia+="No hay "+(this.getDescartables().getBidones5L() )+" bidones de 5L en el vehículo para descargar \n";
            }
        }

    if(this.getDescartables().getPackBotellas2L()>0)
        {
        if(packBotellas2LEnVehiculo < (this.getDescartables().getPackBotellas2L() ))
            {
            aux=false;
            this.incoherencia+="No hay "+(this.getDescartables().getPackBotellas2L() )+" pack de botellas de 2L en el vehículo para descargar \n";
            }
        }

    if(this.getDescartables().getPackBotellas500mL()>0)
        {
        if(packBotellas500mLEnVehiculo < (this.getDescartables().getPackBotellas500mL() ))
            {
            aux=false;
            this.incoherencia+="No hay "+(this.getDescartables().getPackBotellas500mL() )+" pack de botellas de 500mL en el vehículo para descargar \n";
            }
        }

    return aux;

    }

    @Override
    public String getEvaluar() {
        return this.incoherencia;
    }


    @Override
    public String getXMLToSend()
    {

        XML xml = new XML();

        if(this.have())
        {
            xml.startTag("DeudaProductos");

                xml.addValue(retornables.getXMLToSend());
                xml.addValue(descartables.getXMLToSend());


            xml.closeTag("DeudaProductos");

        }

        return xml.getXML();
    }


    public String getDatos()
    {

        String aux = "";

        if(this.have())
        {

            aux += retornables.getDatos();
            aux += descartables.getDatos();


        }

        return aux;
    }







    @Override
    public boolean getEstado()
    {
        return this.retornables.getEstado() || this.descartables.getEstado();
    }



}
