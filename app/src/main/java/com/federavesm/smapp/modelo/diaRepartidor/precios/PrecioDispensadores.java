package com.federavesm.smapp.modelo.diaRepartidor.precios;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;




public class PrecioDispensadores extends GenericoReparto {



    public PrecioDispensadores(Context context){super(context);this.context=context;}



    protected float vertedor=0;
    protected float dispenser=0;

    protected Fecha fecha;

    public void setFecha(Fecha fecha) {this.fecha = fecha;}



    @Override
    public boolean cargar()
    {
        if(this.idDiaRepartidor>0){
            try
            {
                SQLiteDatabase db = getReadableDatabase();
                Cursor cursor = db.rawQuery("SELECT * FROM PrecioDiaRepartidor WHERE idDiaRepartidor="+"'"+this.idDiaRepartidor+"'",null);
                boolean aux=false;
                if(cursor.moveToFirst())
                {
                    aux = true;
                    this.id = cursor.getInt(0);
                    this.vertedor = cursor.getFloat(13);
                    this.dispenser = cursor.getFloat(14);
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
        else
            return false;
    }




    public boolean cargar(Fecha fecha)
    {
        this.fecha=fecha;
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM PrecioDiaRepartidor AS PDR INNER JOIN DiaRepartidor AS DR ON PDR.idDiaRepartidor = DR.id " +
                    "WHERE DR.fecha <="+"'"+this.fecha.toString()+"'"+" ORDER BY DR.fecha DESC",null);
            boolean aux=false;
            if(cursor.moveToFirst())
            {
                aux = true;
                this.id = cursor.getInt(0);
                this.idDiaRepartidor = cursor.getInt(1);
                this.vertedor = cursor.getFloat(13);
                this.dispenser = cursor.getFloat(14);
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













    public void copiar(Object object)
    {
        try
        {
        PrecioDispensadores precio = (PrecioDispensadores)object;
        this.vertedor = precio.getVertedor();
        this.dispenser= precio.getDispenser();
        }
        catch (Exception e)
        {
        }
    }


    public Object getCopia()
    {
    PrecioDispensadores precio = new PrecioDispensadores(this.context);
    precio.copiar(this);
    return precio;
    }


    public float getVertedor() {
        return vertedor;
    }

    public void setVertedor(float vertedor) {
        this.vertedor = vertedor;
    }

    public float getDispenser() {
        return dispenser;
    }

    public void setDispenser(float dispenser) {
        this.dispenser = dispenser;
    }

    @Override
    public boolean evaluar() {
        return false;
    }

    @Override
    public String getEvaluar() {
        return null;
    }

    @Override
    public String getXMLToSend() {
        return null;
    }

    @Override
    public boolean getEstado() {
        return false;
    }

    @Override
    public boolean guardar() {
        return false;
    }

    @Override
    public boolean modificar() {
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


}
