package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Fecha;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;

/**
 * Created by Federico on 7/2/2018.
 */




    public class EstadoBidonesDispenserFC extends GenericoDiaRepartidor {


        public EstadoBidonesDispenserFC(Context context)
        {
            super(context);
        }




    private int bidones20L=0;
    private int bidones12L=0;
    private int dispenserFC=0;
    private Fecha fecha;
    private int idCliente;

    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public void copiar(Object object)
    {
    try
        {
        EstadoBidonesDispenserFC estadoBidonesDispenserFC = (EstadoBidonesDispenserFC)object;
        this.id = estadoBidonesDispenserFC.getId();
        this.bidones20L = estadoBidonesDispenserFC.getBidones20L();
        this.bidones12L = estadoBidonesDispenserFC.getBidones12L();
        this.dispenserFC = estadoBidonesDispenserFC.getDispenserFC();
        this.fecha = estadoBidonesDispenserFC.getFecha();
        this.idCliente = estadoBidonesDispenserFC.getIdCliente();
        }
    catch (Exception e)
        {

        }
    }


    @Override
    public Object getCopia()
    {
    EstadoBidonesDispenserFC estadoBidonesDispenserFC = new EstadoBidonesDispenserFC(context);
    estadoBidonesDispenserFC.copiar(this);
    return estadoBidonesDispenserFC;
    }




    @Override
    public boolean guardar()
    {
        try
        {
            boolean aux = true;

            SQLiteDatabase db = getWritableDatabase();

            ContentValues estadoBidonesDispenserFC = new ContentValues();
            estadoBidonesDispenserFC.put("idCliente",this.idCliente);
            estadoBidonesDispenserFC.put("dispenserFC",this.dispenserFC);
            estadoBidonesDispenserFC.put("bidones20L",this.bidones20L);
            estadoBidonesDispenserFC.put("bidones12L",this.bidones12L);
            estadoBidonesDispenserFC.put("fecha",this.fecha.toString());


            if(db.insert("EstadoBidonesDispenserFC",null,estadoBidonesDispenserFC) > 0)
            {
                this.id = getLastId("EstadoBidonesDispenserFC");
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
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM EstadoBidonesDispenserFC WHERE idCliente="+"'"+this.idCliente+"'" + " AND fecha="+"'"+this.fecha.toString()+"'",null);
        boolean aux = false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.id = cursor.getInt(0);
            this.dispenserFC = cursor.getInt(2);
            this.bidones20L = cursor.getInt(3);
            this.bidones12L = cursor.getInt(4);
            }
        db.close();
        return aux;
        }
    catch (Exception e)
        {
        return false;
        }
    }

    public boolean cargar(Fecha fecha)
    {
        this.fecha=fecha;
        try
        {
            SQLiteDatabase db = getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM EstadoBidonesDispenserFC WHERE idCliente="+"'"+this.idCliente+"'" + " AND fecha<="+"'"+this.fecha.toString()+"'"+" ORDER BY fecha DESC",null);
            boolean aux = false;
            if(cursor.moveToFirst())
            {
                aux = true;
                this.id = cursor.getInt(0);
                this.dispenserFC = cursor.getInt(2);
                this.bidones20L = cursor.getInt(3);
                this.bidones12L = cursor.getInt(4);
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
        try
        {
            boolean aux = true;

            SQLiteDatabase db = getWritableDatabase();

            ContentValues estadoBidonesDispenserFC = new ContentValues();
            estadoBidonesDispenserFC.put("idCliente",this.idCliente);
            estadoBidonesDispenserFC.put("dispenserFC",this.dispenserFC);
            estadoBidonesDispenserFC.put("bidones20L",this.bidones20L);
            estadoBidonesDispenserFC.put("bidones12L",this.bidones12L);
            estadoBidonesDispenserFC.put("fecha",this.fecha.toString());


            if(db.insert("EstadoBidonesDispenserFC",null,estadoBidonesDispenserFC) > 0)
            {
                this.id = getLastId("EstadoBidonesDispenserFC");
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






    public int getBidones20L() {
        return bidones20L;
    }

    public void setBidones20L(int bidones20L) {
        this.bidones20L = bidones20L;
    }

    public int getBidones12L() {
        return bidones12L;
    }

    public void setBidones12L(int bidones12L) {
        this.bidones12L = bidones12L;
    }

    public int getDispenserFC() {
        return dispenserFC;
    }

    public void setDispenserFC(int dispenserFC) {
        this.dispenserFC = dispenserFC;
    }


    @Override
    public boolean eliminar() {
        return false;
    }



    @Override
    public boolean getEstado() {
        return false;
    }




    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
}
