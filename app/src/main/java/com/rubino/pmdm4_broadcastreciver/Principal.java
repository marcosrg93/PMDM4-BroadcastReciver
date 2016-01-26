package com.rubino.pmdm4_broadcastreciver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.rubino.pmdm4_broadcastreciver.bd.Contrato;
import com.rubino.pmdm4_broadcastreciver.llamadas.GestorLlamada;
import com.rubino.pmdm4_broadcastreciver.llamadas.Llamada;
import com.rubino.pmdm4_broadcastreciver.recView.AdaptadorRv;
import com.rubino.pmdm4_broadcastreciver.recView.DividerItemDecoration;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Principal extends AppCompatActivity {

    private GestorLlamada gc;
    private RecyclerView recView;
    private Cursor cursor;
    private int CALL = 1;
    private List<Llamada> llamadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Listado de llamadas");

        init();

    }


    @Override
    protected void onPause() {
        super.onPause();
        gc.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gc.open();
    }

    private void init(){

        //Abrimos los gestores de cada clase
        gc = new GestorLlamada(this);
        gc.open();



       /* Llamada l = new Llamada(0,"630375701","viernes");
        Llamada l1 = new Llamada(0,"630375702","jueves");

        Llamada l3 = new Llamada(0,"630375704","martes");
        Llamada l2 = new Llamada(0,"630375733","lunes");
        gc.insert(l2);
        gc.insert(l1);
        gc.insert(l2);
        gc.insert(l3);*/


        cursor = gc.getCursor();



        Toast toast = Toast.makeText(this, cursor.getCount() + "", Toast.LENGTH_LONG);
        toast.show();


        contarLlamadas();
        recView = (RecyclerView) findViewById(R.id.Recview);
        recView.setHasFixedSize(true);

        final AdaptadorRv adaptador = new AdaptadorRv(cursor);

        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + recView.getChildPosition(v));
            }
        });

        recView.setAdapter(adaptador);




        recView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        //recView.setLayoutManager(new GridLayoutManager(this,3));
        //recView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));

        recView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));





        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                grafico(view,contarLlamadas());
            }
        });

    }

    public void grafico(View v, int [] datos) {
        Intent i = new Intent(this, Grafico.class);
        i.putExtra("Cursor", datos);
        startActivityForResult(i,CALL);
    }



    private int[] contarLlamadas(){
        String mSelectionClause =   Contrato.TablaLlamadas.FECHA+" = ?";


        String [] selectionL = {"lunes"};
        Cursor cl = gc.getCursor(mSelectionClause,selectionL);
        String [] selectionM= {"martes"};
        Cursor cm = gc.getCursor(mSelectionClause,selectionM);
        String [] selectionX = {"miercoles"};
        Cursor cx = gc.getCursor(mSelectionClause,selectionX);
        String [] selectionJ = {"jueves"};
        Cursor cj = gc.getCursor(mSelectionClause,selectionL);
        String [] selectionV= {"viernes"};
        Cursor cv = gc.getCursor(mSelectionClause,selectionM);
        String [] selectionS = {"sabado"};
        Cursor cs = gc.getCursor(mSelectionClause,selectionX);
        String [] selectionD = {"domingo"};
        Cursor cd = gc.getCursor(mSelectionClause,selectionX);



        int a[] = new  int[]{cl.getCount(),cm.getCount(),cx.getCount(),cj.getCount(),cv.getCount(),cs.getCount(),cd.getCount()};

        return a;
       /* Toast toast = Toast.makeText(this, a.toString()+ "", Toast.LENGTH_LONG);
        toast.show();*/

    }





}
