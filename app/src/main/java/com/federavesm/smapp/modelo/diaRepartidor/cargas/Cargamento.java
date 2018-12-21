package com.federavesm.smapp.modelo.diaRepartidor.cargas;

import android.content.Context;

import com.federavesm.smapp.modelo.Comunicador;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorEvaluar;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidorProductos;
import com.federavesm.smapp.modelo.diaRepartidor.GenericoReparto;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Descartables;
import com.federavesm.smapp.modelo.diaRepartidor.productos.Retornables;

import java.util.List;

/**
 * Created by Federico on 21/2/2018.
 */



public class Cargamento extends GenericoDiaRepartidorProductos {



    public Cargamento(Context context)
    {
    super(context);
    this.cargas = new Cargas(context);
    this.descargas = new Descargas(context);
    }


    public Retornables getRetornables()
    {
    Retornables retornablesCargados = this.cargas.getRetornables();
    Retornables retornablesDescargados = this.descargas.getRetornables();
    Retornables retornablesRepartidos = Comunicador.getDiaRepartidor().getRepartos().getRetornablesRepartidos();
    Retornables retornables = new Retornables();
    retornables.setBidones20L(retornablesCargados.getBidones20L()-retornablesDescargados.getBidones20L()-retornablesRepartidos.getBidones20L());
    retornables.setBidones12L(retornablesCargados.getBidones12L()-retornablesDescargados.getBidones12L()-retornablesRepartidos.getBidones12L());
    return retornables;
    }

    public Descartables getDescartables()
    {
    Descartables descartablesCargados = this.cargas.getDescartables();
    Descartables descartablesDescargados = this.descargas.getDescartables();
    Descartables descartablesRepartidos = Comunicador.getDiaRepartidor().getRepartos().getDescartablesRepartidos();
    Descartables descartables = new Descartables();
    descartables.setBidones10L(descartablesCargados.getBidones10L()-descartablesDescargados.getBidones10L()-descartablesRepartidos.getBidones10L());
    descartables.setBidones8L(descartablesCargados.getBidones8L()-descartablesDescargados.getBidones8L()-descartablesRepartidos.getBidones8L());
    descartables.setBidones5L(descartablesCargados.getBidones5L()-descartablesDescargados.getBidones5L()-descartablesRepartidos.getBidones5L());
    descartables.setPackBotellas2L(descartablesCargados.getPackBotellas2L()-descartablesDescargados.getPackBotellas2L()-descartablesRepartidos.getPackBotellas2L());
    descartables.setPackBotellas500mL(descartablesCargados.getPackBotellas500mL()-descartablesDescargados.getPackBotellas500mL()-descartablesRepartidos.getPackBotellas500mL());
    return descartables;
    }




    public Retornables getRetornablesVacios()
    {
    Retornables retornablesVaciosRecuperados = Comunicador.getDiaRepartidor().getRepartos().getRetornablesRecuperados();
    Retornables retornablesVaciosDescargados = this.descargas.getRetornablesVacios();
    Retornables retornables = new Retornables();
    retornables.setBidones20L(retornablesVaciosRecuperados.getBidones20L()-retornablesVaciosDescargados.getBidones20L());
    retornables.setBidones12L(retornablesVaciosRecuperados.getBidones12L()-retornablesVaciosDescargados.getBidones12L());
    return retornables;
    }


    @Override
    public boolean modificar() {
        return false;
    }


    private Cargas cargas;
    private Descargas descargas;


    @Override
    public boolean getEstado() {
        return false;
    }

    @Override
    public boolean cargar()
    {
    this.cargas.setIdDiaRepartidor(this.idDiaRepartidor);
    this.descargas.setIdDiaRepartidor(this.idDiaRepartidor);
    boolean aux = true;
    aux &= this.cargas.cargar();
    aux &= this.descargas.cargar();
    return aux;
    }


    @Override
    public boolean guardar() {
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


    public Cargas getCargas() {
        return cargas;
    }

    public void setCargas(Cargas cargas) {
        this.cargas = cargas;
    }

    public Descargas getDescargas() {
        return descargas;
    }

    public void setDescargas(Descargas descargas) {
        this.descargas = descargas;
    }

    @Override
    public Object getCopia() {
        return null;
    }

    @Override
    public void copiar(Object object) {
    }
}
