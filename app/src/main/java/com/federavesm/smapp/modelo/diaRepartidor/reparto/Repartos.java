package com.federavesm.smapp.modelo.diaRepartidor.reparto;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;
import com.federavesm.smapp.modelo.diaRepartidor.repartidores.Repartidor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Federico on 11/2/2018.
 */


public class Repartos extends GenericoReparto {



    public Repartos(Context context)
    {
    super(context);
    this.context = context;
    }

    private Context context;





    private List<Reparto> repartos = new ArrayList<Reparto>();
    public List<Reparto> getRepartos() {
        return repartos;
    }
    public void setRepartos(List<Reparto> repartos) {
        this.repartos = repartos;
    }



    public boolean isReparto(Reparto reparto)
    {
    boolean aux = false;
    int k=0;
    while(k<repartos.size())
        {
        if(repartos.get(k).getCliente().equals(reparto.getCliente()))
            {
            aux=true;
            break;
            }
            else{k++;}
        }



    return aux;
    }



    public List<Reparto> buscarRepartos(String cadena)
    {
    List<Reparto> repartosBusqueda = new ArrayList<Reparto>();

    for(int i=0;i<this.repartos.size();i++)
        {
        if(this.repartos.get(i).getCoincidencia(cadena))
            {
            repartosBusqueda.add(this.repartos.get(i));
            }
        }

    return repartosBusqueda;
    }

    public Retornables getRetornablesRepartidos()
    {
    return retornablesRepartidos;
    }

    public Descartables getDescartablesRepartidos(){return descartablesRepartidos;}
    public Retornables getRetornablesRecuperados()
    {
        return retornablesRecuperados;
    }

    private Retornables retornablesRepartidos = new Retornables();
    private Retornables retornablesRecuperados = new Retornables();
    private Descartables descartablesRepartidos = new Descartables();

    private Retornables retornablesRepartidosRepartoSeleccionado = new Retornables();
    private Retornables retornablesRecuperadosRepartoSeleccionado = new Retornables();
    private Descartables descartablesRepartidosRepartoSeleccionado = new Descartables();

    public void guardarEstadoRepartoSeleccionado()
    {
    Reparto reparto = Comunicador.getReparto();
    retornablesRepartidosRepartoSeleccionado.copiar(reparto.getRetornablesRepartidos());
    retornablesRecuperadosRepartoSeleccionado.copiar(reparto.getVacios().getRetornables());
    descartablesRepartidosRepartoSeleccionado.copiar(reparto.getDescartablesRepartidos());
    }



    @Override
    public boolean actualizar()
    {
    Reparto reparto = Comunicador.getReparto();

    this.retornablesRepartidos.setBidones20L(this.retornablesRepartidos.getBidones20L() - retornablesRepartidosRepartoSeleccionado.getBidones20L() + reparto.getRetornablesRepartidos().getBidones20L());
    this.retornablesRepartidos.setBidones12L(this.retornablesRepartidos.getBidones12L() - retornablesRepartidosRepartoSeleccionado.getBidones12L() + reparto.getRetornablesRepartidos().getBidones12L());

    this.descartablesRepartidos.setBidones10L(this.descartablesRepartidos.getBidones10L() - descartablesRepartidosRepartoSeleccionado.getBidones10L() + reparto.getDescartablesRepartidos().getBidones10L());
    this.descartablesRepartidos.setBidones8L(this.descartablesRepartidos.getBidones8L() - descartablesRepartidosRepartoSeleccionado.getBidones8L() + reparto.getDescartablesRepartidos().getBidones8L());
    this.descartablesRepartidos.setBidones5L(this.descartablesRepartidos.getBidones5L() - descartablesRepartidosRepartoSeleccionado.getBidones5L() + reparto.getDescartablesRepartidos().getBidones5L());
    this.descartablesRepartidos.setPackBotellas2L(this.descartablesRepartidos.getPackBotellas2L() - descartablesRepartidosRepartoSeleccionado.getPackBotellas2L() + reparto.getDescartablesRepartidos().getPackBotellas2L());
    this.descartablesRepartidos.setPackBotellas500mL(this.descartablesRepartidos.getPackBotellas500mL() - descartablesRepartidosRepartoSeleccionado.getPackBotellas500mL() + reparto.getDescartablesRepartidos().getPackBotellas500mL());

    this.retornablesRecuperados.setBidones20L(this.retornablesRecuperados.getBidones20L() - retornablesRecuperadosRepartoSeleccionado.getBidones20L() + reparto.getVacios().getRetornables().getBidones20L());
    this.retornablesRecuperados.setBidones12L(this.retornablesRecuperados.getBidones12L() - retornablesRecuperadosRepartoSeleccionado.getBidones12L() + reparto.getVacios().getRetornables().getBidones12L());

    guardarEstadoRepartoSeleccionado();

    return true;
    }




    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public boolean cargar()
    {
    try
        {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM Repartos WHERE idDiaRepartidor="+"'"+this.idDiaRepartidor+"'",null);

        Comunicador.setNumeroRepartos(cursor.getCount());
        Comunicador.setInfoRepartos(true);
        Comunicador.getHiloCargarDia().publicarProgreso();
        Comunicador.setNumeroReparto(1);



        boolean res = true;
        boolean aux = cursor.moveToFirst();
        while(aux)
            {
            Reparto reparto = new Reparto(this.context);
            reparto.setId(cursor.getInt(0));
            res &= reparto.cargar();
            repartos.add(reparto);
            agregarReparto(reparto);
            aux = cursor.moveToNext();
            Comunicador.setNumeroReparto(Comunicador.getNumeroReparto()+1);
            Comunicador.getHiloCargarDia().publicarProgreso();
            }
        db.close();
        return res;
        }
    catch (Exception e)
        {
        String x=e.toString();
        return false;
        }
    }

    private void agregarReparto(Reparto reparto)
    {
    this.retornablesRepartidos.setBidones20L(this.retornablesRepartidos.getBidones20L() + reparto.getRetornablesRepartidos().getBidones20L());
    this.retornablesRepartidos.setBidones12L(this.retornablesRepartidos.getBidones12L() + reparto.getRetornablesRepartidos().getBidones12L());

    this.retornablesRecuperados.setBidones20L(this.retornablesRecuperados.getBidones20L() + reparto.getVacios().getRetornables().getBidones20L());
    this.retornablesRecuperados.setBidones12L(this.retornablesRecuperados.getBidones12L() + reparto.getVacios().getRetornables().getBidones12L());

    this.descartablesRepartidos.setBidones10L(this.descartablesRepartidos.getBidones10L() + reparto.getDescartablesRepartidos().getBidones10L());
    this.descartablesRepartidos.setBidones8L(this.descartablesRepartidos.getBidones8L() + reparto.getDescartablesRepartidos().getBidones8L());
    this.descartablesRepartidos.setBidones5L(this.descartablesRepartidos.getBidones5L() + reparto.getDescartablesRepartidos().getBidones5L());
    this.descartablesRepartidos.setPackBotellas2L(this.descartablesRepartidos.getPackBotellas2L() + reparto.getDescartablesRepartidos().getPackBotellas2L());
    this.descartablesRepartidos.setPackBotellas500mL(this.descartablesRepartidos.getPackBotellas500mL() + reparto.getDescartablesRepartidos().getPackBotellas500mL());

    }



    @Override
    public boolean guardar()
    {
    boolean aux=true;
    boolean aux2;
    int i=0;
    try
    {
        for(i = 0; i<this.repartos.size(); i++)
        {

        if(i==41)
            {
            int k=3;
            int w=1;
            k+=w;
            }
        this.repartos.get(i).setIdDiaRepartidor(this.idDiaRepartidor);
            aux2=this.repartos.get(i).guardar();
            if(aux2 == false)
            {
                int x=i;
                x=x;
            }

            aux&=aux2;

        }


    }
    catch (Exception e)
    {
        String error = e.toString();
        int n = i;
    }

    return aux;
    }

    @Override
    public boolean eliminar()
    {
    boolean aux=true;
    for(int i = 0; i < this.repartos.size(); i++)
        {
        aux&=this.repartos.get(i).eliminar();
        }
    return aux;
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
    public String getXMLToSend() {
        return "";
    }


    @Override
    public boolean getEstado()
    {
    boolean aux = false;
    int i=0;
    while(i<this.repartos.size())
        {
        aux = this.repartos.get(i).getEstado();
        if(aux){break;}
        i++;
        }
    return aux;
    }



    @Override
    public Object getCopia() {
        return null;
    }


    @Override
    public void copiar(Object object) {
    }



}