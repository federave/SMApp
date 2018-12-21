package com.federavesm.smapp.modelo.diaRepartidor;

import android.content.Context;

import com.federavesm.smapp.modelo.Generico;

/**
 * Created by Federico on 4/2/2018.
 */


    public abstract class GenericoDiaRepartidorEvaluar extends GenericoDiaRepartidor {



        public GenericoDiaRepartidorEvaluar(Context context)
        {
            super(context);
        }



        public abstract boolean evaluar();

        public abstract String getEvaluar();

        public abstract String getXMLToSend();

        protected String incoherencia;

}
