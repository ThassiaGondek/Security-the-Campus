package com.example.lucas.securityrift;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        Button ocorrencia = (Button) findViewById(R.id.ocorrencia);
        Button info = (Button) findViewById(R.id.info);
        Button indice = (Button) findViewById(R.id.indice);
        Button sobre = (Button) findViewById(R.id.sobre);

        ocorrencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(MenuActivity.this, OcorrenciaActivity.class);
                startActivity(a);
            }
        });

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b = new Intent(MenuActivity.this, InfoActivity.class);
                startActivity(b);
            }
        });

        indice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c = new Intent(MenuActivity.this, IndiceActivity.class);
                startActivity(c);
            }
        });

        sobre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent d = new Intent(MenuActivity.this, SobreActivity.class);
                startActivity(d);
            }
        });
    }
}
