package com.soflucion.softlution1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class about extends AppCompatActivity implements View.OnClickListener {

    Button btninicioaa, btnservicioaa, btnaboutaa, btncontactoaa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        btninicioaa = (Button) findViewById(R.id.btninicioaa);
        btninicioaa.setOnClickListener(this);

        btnservicioaa = (Button) findViewById(R.id.btnservicioaa);
        btnservicioaa.setOnClickListener(this);

        btnaboutaa = (Button) findViewById(R.id.btnaboutaa);
        btnaboutaa.setOnClickListener(this);

        btncontactoaa = (Button) findViewById(R.id.btncontactoaa);
        btncontactoaa.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent=null;

        switch (view.getId()){

            case R.id.btninicioaa:
                intent = new Intent(about.this, MainActivity.class);
                break;

            case R.id.btnservicioaa:
                intent = new Intent(about.this, servicios.class);
                break;
            case R.id.btnaboutaa:
                intent = new Intent(about.this, about.class);
                break;
            case R.id.btncontactoaa:
                intent = new Intent(about.this, contacto.class);
                break;

        }
        startActivity(intent);
    }
}
