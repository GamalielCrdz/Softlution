package com.soflucion.softlution1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    Button btncontactoma, btnaboutma, btnservma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btncontactoma = (Button) findViewById(R.id.btncontactoma);
        btncontactoma.setOnClickListener(this);
        btnservma = (Button) findViewById(R.id.btnservma);
        btnservma.setOnClickListener(this);
        btnaboutma = (Button) findViewById(R.id.btnaboutma);
        btnaboutma.setOnClickListener(this);




        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {

            case R.id.btncontactoma:
                 intent = new Intent(MainActivity.this, contacto.class);
                break;
            case R.id.btnservma:
                intent = new Intent(MainActivity.this, servicios.class);
                break;
            case R.id.btnaboutma:
                intent = new Intent(MainActivity.this, about.class);
                break;

        }
        startActivity(intent);
    }

    public static class splash extends Activity {
        private final int DURACION_SPLASH = 3000;
        @Override
        public void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.splash);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(splash.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                };
            },DURACION_SPLASH);
        }
    }
}