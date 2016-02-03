package com.rubino.pmdm4_broadcastreciver.bd;

import android.provider.BaseColumns;

/**
 * Created by marco on 21/08/2015.
 */
public class Contrato{

    private Contrato (){
    }

    public static abstract  class TablaLlamadas implements BaseColumns{
        public static final String TABLA = "llamadas";
        public static final String NUMERO = "numero";
        public static final String FECHA = "fecha";
        public static final String TIPO= "tipo";

    }

}
