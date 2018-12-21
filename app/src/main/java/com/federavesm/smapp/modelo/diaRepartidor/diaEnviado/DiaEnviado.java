package com.federavesm.smapp.modelo.diaRepartidor.diaEnviado;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Convertidor;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.diaRepartidor.gastos.Gasto;

/**
 * Created by FedeRave on 24/3/2018.
 */

public class DiaEnviado extends GenericoReparto {



    public DiaEnviado(Context context)
    {
        super(context);
        this.context = context;
    }

    private Context context;

    private int repartosEnviados=0;
    private int gastosEnviados=0;
    private int cargasEnviadas=0;
    private int descargasEnviadas=0;





    public void limpiar()
    {
    repartosEnviados=0;
    gastosEnviados=0;
    cargasEnviadas=0;
    descargasEnviadas=0;
    }



    @Override
    public boolean modificar()
    {
    try
        {

        boolean aux = true;

        if (this.id > 0) {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues diaEnviado = new ContentValues();
            diaEnviado.put("idDiaRepartidor", this.idDiaRepartidor);
            diaEnviado.put("repartosEnviados", this.repartosEnviados);
            diaEnviado.put("gastosEnviados", this.gastosEnviados);
            diaEnviado.put("cargasEnviadas", this.cargasEnviadas);
            diaEnviado.put("descargasEnviadas", this.descargasEnviadas);


            String whereClause = "id=?";
            String whereArgs[] = {String.valueOf(this.id)};

            if (!(db.update("DiaEnviado", diaEnviado, whereClause, whereArgs) > 0)) {
                aux = false;
            }

            db.close();
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
    public boolean actualizar()
    {
        return true;
    }



    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DiaEnviado WHERE idDiaRepartidor="+"'"+this.idDiaRepartidor+"'",null);
        boolean aux=false;
        if(cursor.moveToFirst())
            {
            aux = true;
            this.id = cursor.getInt(0);
            this.idDiaRepartidor = cursor.getInt(1);
            this.repartosEnviados = cursor.getInt(2);
            this.gastosEnviados = cursor.getInt(3);
            this.cargasEnviadas = cursor.getInt(4);
            this.descargasEnviadas = cursor.getInt(5);
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



    @Override
    public boolean guardar()
    {
    try
        {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues diaEnviado = new ContentValues();
        diaEnviado.put("idDiaRepartidor",this.idDiaRepartidor);
        diaEnviado.put("repartosEnviados",this.repartosEnviados);
        diaEnviado.put("gastosEnviados",this.gastosEnviados);
        diaEnviado.put("cargasEnviadas",this.cargasEnviadas);
        diaEnviado.put("descargasEnviadas",this.descargasEnviadas);

        boolean aux=true;
        if(db.insert("DiaEnviado",null,diaEnviado) > 0)
            {
            this.id = getLastId("DiaEnviado");
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
            if(db.delete("DiaEnviado", "id=" + "'" + this.id + "'", null)>0)
            {
                aux = true;
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
    public boolean evaluar()
    {
        return true;

    }



    @Override
    public String getEvaluar() {
        return "";

    }


    @Override
    public String getXMLToSend() {
        return "";
    }


    @Override
    public boolean getEstado()
    {

            return true;
    }




    @Override
    public Object getCopia()
    {
    DiaEnviado diaEnviado = new DiaEnviado(context);
    diaEnviado.copiar(this);
    return diaEnviado;
    }

    @Override
    public void copiar(Object object)
    {
    DiaEnviado diaEnviado = (DiaEnviado)object;
    this.id = diaEnviado.getId();
    this.idDiaRepartidor = diaEnviado.getIdDiaRepartidor();
    this.repartosEnviados = diaEnviado.getRepartosEnviados();
    this.gastosEnviados = diaEnviado.getGastosEnviados();
    this.cargasEnviadas = diaEnviado.getCargasEnviadas();
    this.descargasEnviadas = diaEnviado.getDescargasEnviadas();
    }







    @Override
    public String toString() {

      return "";
    }


    public int getRepartosEnviados() {
        return repartosEnviados;
    }

    public void setRepartosEnviados(int repartosEnviados) {
        this.repartosEnviados = repartosEnviados;
    }

    public int getGastosEnviados() {
        return gastosEnviados;
    }

    public void setGastosEnviados(int gastosEnviados) {
        this.gastosEnviados = gastosEnviados;
    }

    public int getCargasEnviadas() {
        return cargasEnviadas;
    }

    public void setCargasEnviadas(int cargasEnviadas) {
        this.cargasEnviadas = cargasEnviadas;
    }

    public int getDescargasEnviadas() {
        return descargasEnviadas;
    }

    public void setDescargasEnviadas(int descargasEnviadas) {
        this.descargasEnviadas = descargasEnviadas;
    }
}
