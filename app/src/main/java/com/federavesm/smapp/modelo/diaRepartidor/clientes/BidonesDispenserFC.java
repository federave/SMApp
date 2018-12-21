package com.federavesm.smapp.modelo.diaRepartidor.clientes;

import android.content.Context;

import com.federavesm.smapp.modelo.diaRepartidor.GenericoDiaRepartidor;

/**
 * Created by Federico on 7/2/2018.
 */




    public class BidonesDispenserFC extends GenericoDiaRepartidor {


        public BidonesDispenserFC(Context context)
        {
            super(context);
        }




    private int bidones20L=0;
    private int bidones12L=0;
    private int dispenserFC=0;



    @Override
    public boolean modificar() {
        return false;
    }

    @Override
    public void copiar(Object object)
    {
    try
        {
        BidonesDispenserFC bidonesDispenserFC = (BidonesDispenserFC)object;
        this.id = bidonesDispenserFC.getId();
        this.bidones20L = bidonesDispenserFC.getBidones20L();
        this.bidones12L = bidonesDispenserFC.getBidones12L();
        this.dispenserFC = bidonesDispenserFC.getDispenserFC();
        }
    catch (Exception e)
        {

        }
    }


    @Override
    public Object getCopia()
    {
    BidonesDispenserFC bidonesDispenserFC = new BidonesDispenserFC(context);
    bidonesDispenserFC.copiar(this);
    return bidonesDispenserFC;
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
    public boolean getEstado() {
        return false;
    }




    @Override
    public boolean cargar(){return true;}



}
