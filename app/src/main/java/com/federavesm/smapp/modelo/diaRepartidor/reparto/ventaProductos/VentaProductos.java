package com.federavesm.smapp.modelo.diaRepartidor.reparto.ventaProductos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.precios.PrecioProductos;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.reparto.RepartoProductos;
import com.federavesm.smapp.modelo.servidor.datosXML.XML;

/**
 * Created by Federico on 4/2/2018.
 */

public class VentaProductos extends RepartoProductos {



    public VentaProductos(Context context)
    {
        super(context);
    }




    protected Retornables retornablesBonificados = new Retornables();
    protected Descartables descartablesBonificados = new Descartables();




    @Override
    public void copiar(Object object)
    {
    try
        {
        super.copiar(object);
        VentaProductos ventaProductos = (VentaProductos)object;
        this.id = ventaProductos.getId();
        this.retornables.copiar(ventaProductos.getRetornables());
        this.descartables.copiar(ventaProductos.getDescartables());
        this.retornablesBonificados.copiar(ventaProductos.getRetornablesBonificados());
        this.descartablesBonificados.copiar(ventaProductos.getDescartablesBonificados());
        }
    catch (Exception e)
        {

        }
    }




    @Override
    public Object getCopia()
    {
    VentaProductos ventaProductos = new VentaProductos(context);
    ventaProductos.copiar(this);
    return ventaProductos;
    }




    @Override
    public boolean cargar()
    {
    if(this.id > 0)
        {
        try
            {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM VentaProductos WHERE id="+"'"+this.id+"'",null);
            boolean aux = false;
            if(cursor.moveToFirst())
                {
                aux = true;
                this.retornables.setBidones20L(cursor.getInt(1));
                this.retornables.setBidones12L(cursor.getInt(2));
                this.descartables.setBidones10L(cursor.getInt(3));
                this.descartables.setBidones8L(cursor.getInt(4));
                this.descartables.setBidones5L(cursor.getInt(5));
                this.descartables.setPackBotellas2L(cursor.getInt(6));
                this.descartables.setPackBotellas500mL(cursor.getInt(7));
                this.retornablesBonificados.setBidones20L(cursor.getInt(8));
                this.retornablesBonificados.setBidones12L(cursor.getInt(9));
                this.descartablesBonificados.setBidones10L(cursor.getInt(10));
                this.descartablesBonificados.setBidones8L(cursor.getInt(11));
                this.descartablesBonificados.setBidones5L(cursor.getInt(12));
                this.descartablesBonificados.setPackBotellas2L(cursor.getInt(13));
                this.descartablesBonificados.setPackBotellas500mL(cursor.getInt(14));

                setDinero();

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

        ContentValues ventaProductos = new ContentValues();

        ventaProductos.put("bidones20L",this.retornables.getBidones20L());
        ventaProductos.put("bidones12L",this.retornables.getBidones12L());
        ventaProductos.put("bidones10L",this.descartables.getBidones10L());
        ventaProductos.put("bidones8L",this.descartables.getBidones8L());
        ventaProductos.put("bidones5L",this.descartables.getBidones5L());
        ventaProductos.put("packBotellas2L",this.descartables.getPackBotellas2L());
        ventaProductos.put("packBotellas500mL",this.descartables.getPackBotellas500mL());
        ventaProductos.put("bidones20LBonificados",this.retornablesBonificados.getBidones20L());
        ventaProductos.put("bidones12LBonificados",this.retornablesBonificados.getBidones12L());
        ventaProductos.put("bidones10LBonificados",this.descartablesBonificados.getBidones10L());
        ventaProductos.put("bidones8LBonificados",this.descartablesBonificados.getBidones8L());
        ventaProductos.put("bidones5LBonificados",this.descartablesBonificados.getBidones5L());
        ventaProductos.put("packBotellas2LBonificados",this.descartablesBonificados.getPackBotellas2L());
        ventaProductos.put("packBotellas500mLBonificados",this.descartablesBonificados.getPackBotellas500mL());

        boolean aux = true;
        if(db.insert("VentaProductos",null,ventaProductos) > 0)
            {
            this.id = getLastId("VentaProductos");
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
        if(db.delete("VentaProductos", "id=" + "'" + this.id + "'", null)>0)
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
    public boolean actualizar()
    {
    return true;
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

            ContentValues ventaProductos = new ContentValues();

            ventaProductos.put("bidones20L", this.retornables.getBidones20L());
            ventaProductos.put("bidones12L", this.retornables.getBidones12L());
            ventaProductos.put("bidones10L", this.descartables.getBidones10L());
            ventaProductos.put("bidones8L", this.descartables.getBidones8L());
            ventaProductos.put("bidones5L", this.descartables.getBidones5L());
            ventaProductos.put("packBotellas2L", this.descartables.getPackBotellas2L());
            ventaProductos.put("packBotellas500mL", this.descartables.getPackBotellas500mL());
            ventaProductos.put("bidones20LBonificados", this.retornablesBonificados.getBidones20L());
            ventaProductos.put("bidones12LBonificados", this.retornablesBonificados.getBidones12L());
            ventaProductos.put("bidones10LBonificados", this.descartablesBonificados.getBidones10L());
            ventaProductos.put("bidones8LBonificados", this.descartablesBonificados.getBidones8L());
            ventaProductos.put("bidones5LBonificados", this.descartablesBonificados.getBidones5L());
            ventaProductos.put("packBotellas2LBonificados", this.descartablesBonificados.getPackBotellas2L());
            ventaProductos.put("packBotellas500mLBonificados", this.descartablesBonificados.getPackBotellas500mL());

            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};

            if (!(db.update("VentaProductos", ventaProductos, whereClause, whereArgs) > 0))
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
    public boolean evaluar()
    {
    incoherencia="";
    boolean aux = true;
    this.incoherencia="";

    int bidones20LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones20L() + Comunicador.getVentaProductosAux().getRetornables().getBidones20L() + Comunicador.getVentaProductosAux().getRetornablesBonificados().getBidones20L();
    int bidones12LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getRetornables().getBidones12L() + Comunicador.getVentaProductosAux().getRetornables().getBidones12L() + Comunicador.getVentaProductosAux().getRetornablesBonificados().getBidones12L();
    int bidones10LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones10L() + Comunicador.getVentaProductosAux().getDescartables().getBidones10L() + Comunicador.getVentaProductosAux().getDescartablesBonificados().getBidones10L();
    int bidones8LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones8L() + Comunicador.getVentaProductosAux().getDescartables().getBidones8L() + Comunicador.getVentaProductosAux().getDescartablesBonificados().getBidones8L();
    int bidones5LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getBidones5L() + Comunicador.getVentaProductosAux().getDescartables().getBidones5L() + Comunicador.getVentaProductosAux().getDescartablesBonificados().getBidones5L();
    int packBotellas2LEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas2L() + Comunicador.getVentaProductosAux().getDescartables().getPackBotellas2L() + Comunicador.getVentaProductosAux().getDescartablesBonificados().getPackBotellas2L();
    int packBotellas500mLEnVehiculo = Comunicador.getDiaRepartidor().getCargamento().getDescartables().getPackBotellas500mL() + Comunicador.getVentaProductosAux().getDescartables().getPackBotellas500mL() + Comunicador.getVentaProductosAux().getDescartablesBonificados().getPackBotellas500mL();


    if((this.getRetornables().getBidones20L() + this.getRetornablesBonificados().getBidones20L())>0) {

        if (bidones20LEnVehiculo < (this.getRetornables().getBidones20L() + this.getRetornablesBonificados().getBidones20L())) {
            aux = false;
            this.incoherencia += "No hay " + (this.getRetornables().getBidones20L() + this.getRetornablesBonificados().getBidones20L()) + " bidones de 20L en el vehículo para vender \n";
        }

    }


        if((this.getRetornables().getBidones12L() + this.getRetornablesBonificados().getBidones12L())>0) {


            if (bidones12LEnVehiculo < (this.getRetornables().getBidones12L() + this.getRetornablesBonificados().getBidones12L())) {
                aux = false;
                this.incoherencia += "No hay " + (this.getRetornables().getBidones12L() + this.getRetornablesBonificados().getBidones12L()) + " bidones de 12L en el vehículo para vender \n";
            }

        }


        if((this.getDescartables().getBidones10L() + this.getDescartablesBonificados().getBidones10L())>0) {

            if (bidones10LEnVehiculo < (this.getDescartables().getBidones10L() + this.getDescartablesBonificados().getBidones10L())) {
                aux = false;
                this.incoherencia += "No hay " + (this.getDescartables().getBidones10L() + this.getDescartablesBonificados().getBidones10L()) + " bidones de 10L en el vehículo para vender \n";
            }
        }

        if((this.getDescartables().getBidones8L() + this.getDescartablesBonificados().getBidones8L())>0) {

            if (bidones8LEnVehiculo < (this.getDescartables().getBidones8L() + this.getDescartablesBonificados().getBidones8L())) {
                aux = false;
                this.incoherencia += "No hay " + (this.getDescartables().getBidones8L() + this.getDescartablesBonificados().getBidones8L()) + " bidones de 8L en el vehículo para vender \n";
            }
        }

        if((this.getDescartables().getBidones5L() + this.getDescartablesBonificados().getBidones5L())>0) {

            if (bidones5LEnVehiculo < (this.getDescartables().getBidones5L() + this.getDescartablesBonificados().getBidones5L())) {
                aux = false;
                this.incoherencia += "No hay " + (this.getDescartables().getBidones5L() + this.getDescartablesBonificados().getBidones5L()) + " bidones de 5L en el vehículo para vender \n";
            }

        }

        if((this.getDescartables().getPackBotellas2L() + this.getDescartablesBonificados().getPackBotellas2L())>0) {

            if (packBotellas2LEnVehiculo < (this.getDescartables().getPackBotellas2L() + this.getDescartablesBonificados().getPackBotellas2L())) {
                aux = false;
                this.incoherencia += "No hay " + (this.getDescartables().getPackBotellas2L() + this.getDescartablesBonificados().getPackBotellas2L()) + " pack de botellas de 2L en el vehículo para vender \n";
            }
        }

        if((this.getDescartables().getPackBotellas500mL() + this.getDescartablesBonificados().getPackBotellas500mL())>0) {

            if (packBotellas500mLEnVehiculo < (this.getDescartables().getPackBotellas500mL() + this.getDescartablesBonificados().getPackBotellas500mL())) {
                aux = false;
                this.incoherencia += "No hay " + (this.getDescartables().getPackBotellas500mL() + this.getDescartablesBonificados().getPackBotellas500mL()) + " pack de botellas de 500mL en el vehículo para vender \n";
            }
        }



    return aux;
    }

    @Override
    public String getEvaluar() {
        return this.incoherencia;
    }


    public void borrarDatos()
    {
    this.retornables = new Retornables();
    this.retornablesBonificados = new Retornables();
    this.descartablesBonificados = new Descartables();
    this.descartables = new Descartables();
    this.dinero = 0;

    }

    @Override
    public String getXMLToSend()
    {

    XML xml = new XML();

    if(this.have())
        {
        xml.startTag("VentaProductos");

            xml.addValue(retornables.getXMLToSend());
            xml.addValue(descartables.getXMLToSend());

            if(retornablesBonificados.have() || descartablesBonificados.have())
                {
                xml.startTag("Bonificados");
                    xml.addValue(retornablesBonificados.getXMLToSend());
                    xml.addValue(descartablesBonificados.getXMLToSend());
                xml.closeTag("Bonificados");
                }

            xml.addTag("Dinero",String.valueOf(this.dinero));




        xml.closeTag("VentaProductos");

        }

    return xml.getXML();
    }


    public String getDatos()
    {

        String aux = "";

        if(this.have())
        {

            aux += "\n" + retornables.getDatos();
            aux += "\n" + descartables.getDatos();

            if(retornablesBonificados.have() || descartablesBonificados.have())
            {
                aux += "\nBonificados \n";
                aux += retornablesBonificados.getDatos();
                aux += descartablesBonificados.getDatos();

            }

            aux += "\n" + "Dinero: " + String.valueOf(this.dinero);


        }

        return aux;
    }




    @Override
    public boolean getEstado()
    {
    if(this.retornables.getEstado() && this.retornablesBonificados.getEstado() && this.descartables.getEstado() && this.descartablesBonificados.getEstado())
        {
        return true;
        }
    else
        {
        return false;
        }
    }

    public boolean have()
    {
    return this.retornables.have() || this.retornablesBonificados.have() || this.descartables.have() || this.descartablesBonificados.have();
    }






    public Retornables getRetornablesBonificados() {
        return retornablesBonificados;
    }

    public void setRetornablesBonificados(Retornables retornablesBonificados) {
        this.retornablesBonificados = retornablesBonificados;
    }

    public Descartables getDescartablesBonificados() {
        return descartablesBonificados;
    }

    public void setDescartablesBonificados(Descartables descartablesBonificados) {
        this.descartablesBonificados = descartablesBonificados;
    }



}
