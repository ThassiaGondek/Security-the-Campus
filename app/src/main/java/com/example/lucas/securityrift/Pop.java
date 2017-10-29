package com.example.lucas.securityrift;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

/**
 * Created by lucas on 28/10/2017.
 */


public class Pop extends Activity{

    private EditText texto;

    @Override
    protected void onCreate(Bundle savedIntantState){
        super.onCreate(savedIntantState);
        final Intent it = new Intent(Pop.this, pin.class);

        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        Button b3 = (Button) findViewById(R.id.button3);


        texto = (EditText) findViewById(R.id.texto);
        String txt = texto.getText().toString();




        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int x;
                RadioButton r1 = (RadioButton) findViewById(R.id.radioButton);
                RadioButton r2 = (RadioButton) findViewById(R.id.radioButton2);
                RadioButton r3 = (RadioButton) findViewById(R.id.radioButton3);
                RadioButton r4 = (RadioButton) findViewById(R.id.radioButton4);
                RadioButton r5 = (RadioButton) findViewById(R.id.radioButton5);

                if(r1.isChecked()){
                    x = 1;
                    it.putExtra("tipo",x);

                }
                if(r2.isChecked()){
                    x =2;
                    it.putExtra("tipo",x);

                }
                if(r3.isChecked()){
                    x = 3;
                    it.putExtra("tipo",x);

                }
                if(r4.isChecked()){
                    x = 4;
                    it.putExtra("tipo",x);

                }
                if(r5.isChecked()){
                    x = 5;
                    it.putExtra("tipo",x);

                }
                EditText textoTemp =  (EditText) findViewById(R.id.texto);
                String txt = textoTemp.getText().toString();

                it.putExtra("texto",txt);

                startActivity(it);
            }
        });


    }

}
