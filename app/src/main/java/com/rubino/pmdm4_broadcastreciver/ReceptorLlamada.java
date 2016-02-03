package com.rubino.pmdm4_broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.rubino.pmdm4_broadcastreciver.llamadas.GestorLlamada;
import com.rubino.pmdm4_broadcastreciver.llamadas.Llamada;

import java.util.Calendar;

/**
 * Created by marco on 24/01/2016.
 */
public class ReceptorLlamada extends BroadcastReceiver {


    GestorLlamada gc;
    Llamada llamada;
    int duration = Toast.LENGTH_SHORT;
    String tipo;


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();
        gc = new GestorLlamada(context);

        if (extras != null) {
            String state = extras.getString(TelephonyManager.EXTRA_STATE);


            Toast toast = Toast.makeText(context, "LLAMADA " + state, duration);
            toast.show();

            Log.v("LLAMADA", state);
            if (state.equals(TelephonyManager.EXTRA_STATE_RINGING)) {
                String phoneNumber = extras
                        .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                tipo = "Entrante";
                gc.open();
                llamada = new Llamada(phoneNumber, dia(),tipo);
                if(state != TelephonyManager.EXTRA_STATE_RINGING) {
                    gc.insert(llamada);
                }
                gc.close();

                toast = Toast.makeText(context, "Me esta llamando:" + llamada.toString(), duration);
                toast.show();
            } else if (state.equals(TelephonyManager.EXTRA_STATE_OFFHOOK)) {
                String phoneNumber = extras
                        .getString(TelephonyManager.EXTRA_INCOMING_NUMBER);

                tipo = "Saliente";

                gc.open();
                llamada = new Llamada(phoneNumber, dia(),tipo);
                if(phoneNumber != null){
                gc.insert(llamada);
                }
                gc.close();

                toast = Toast.makeText(context, "Llamando a:" + llamada.toString(), duration);
                toast.show();

            }
        }
    }


    private String dia() {
        String dia = "";

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);


        switch (day) {
            case 1:
                return dia = "domingo";
            case 2:
                return dia = "lunes";
            case 3:
                return dia = "martes";
            case 4:
                return dia = "miercoles";
            case 5:
                return dia = "jueves";
            case 6:
                return dia = "viernes";
            case 7:
                return dia = "sabada";
            default:
                return dia = "Hoy no es tu día";
        }
    }


    /*@Override
    public void onReceive(Context context, Intent intent) {




        try {
            // TELEPHONY MANAGER class object to register one listner
            TelephonyManager tmgr = (TelephonyManager) context
                    .getSystemService(Context.TELEPHONY_SERVICE);

            //Create Listner
            MyPhoneStateListener PhoneListener = new MyPhoneStateListener();

            // Register listener for LISTEN_CALL_STATE
            tmgr.listen(PhoneListener, PhoneStateListener.LISTEN_CALL_STATE);

        } catch (Exception e) {
            Log.e("MAPhone Receive Error", " " + e);
        }

    }

    private class MyPhoneStateListener extends PhoneStateListener {

        public void onCallStateChanged(int state, String incomingNumber) {

            Log.d("MA",state+"   incoming no:"+incomingNumber);

            if (state == 1) {

                Log.v("MA", "New Phone Call Event. Incomming Number : " + incomingNumber);


            }
        }
    }*/

}








