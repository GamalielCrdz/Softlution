package com.soflucion.softlution1;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.drm.ProcessedData;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.snowdream.android.widget.SmartImage;
import com.github.snowdream.android.widget.SmartImageView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

import static com.soflucion.softlution1.R.id.btncontactoas;

public class servicios extends AppCompatActivity implements View.OnClickListener{

private ListView lvserv;

    Button btninicioas, btncontactoas, btnaboutas;


    ArrayList nombre=new ArrayList();
    ArrayList descripcion=new ArrayList();
    ArrayList imagen=new ArrayList();
   // ArrayList costo=new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicios);

        lvserv = (ListView) findViewById(R.id.lvserv);
        verimagen();

        btninicioas = (Button) findViewById(R.id.btninicioas);
        btninicioas.setOnClickListener(this);

        btncontactoas = (Button) findViewById(R.id.btncontactoas);
        btncontactoas.setOnClickListener(this);

        btnaboutas = (Button) findViewById(R.id.btnaboutas);
        btnaboutas.setOnClickListener(this);

    }

    private void verimagen() {
        nombre.clear();
        descripcion.clear();
        imagen.clear();
        //costo.clear();

        final ProgressDialog progressDialog=new ProgressDialog(servicios.this);
        progressDialog.setMessage("Cargando Datos");
        progressDialog.show();
        AsyncHttpClient client=new AsyncHttpClient();
        client.get("http://softlution.dyndns.org/softlution/jsonserv.php", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode==200)
                {
                    progressDialog.dismiss();
                    try {
                        JSONArray jsonArray = new JSONArray(new String(responseBody));
                        for (int i=0;i<jsonArray.length();i++)
                        {
                            nombre.add(jsonArray.getJSONObject(i).getString("Nombre"));
                            descripcion.add(jsonArray.getJSONObject(i).getString("Descripcion"));
                            imagen.add(jsonArray.getJSONObject(i).getString("img"));
                            //costo.add(jsonArray.getJSONObject(i).getString("Costo"));
                        }
                        lvserv.setAdapter(new ImagenAdapter(getApplicationContext()));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

 private class ImagenAdapter extends BaseAdapter {
        Context ctx;
        LayoutInflater layoutInflater;
        SmartImageView smartImageView;
        TextView tvnombre,tvdescripcion,tvcosto;

     public ImagenAdapter(Context applicationContext) {
         this.ctx=applicationContext;
         layoutInflater = (LayoutInflater) ctx.getSystemService(LAYOUT_INFLATER_SERVICE);

     }

     @Override
     public int getCount() {
         return imagen.size();
     }

     @Override
     public Object getItem(int position) {
         return position;
     }

     @Override
     public long getItemId(int position) {
         return position;
     }

     @Override
     public View getView(int position, View convertView, ViewGroup parent) {
         ViewGroup viewGroup=(ViewGroup) layoutInflater.inflate(R.layout.activity_main_item,null);
         smartImageView=(SmartImageView)viewGroup.findViewById(R.id.imglv);
         tvnombre=(TextView) viewGroup.findViewById(R.id.tvnombre);
         tvdescripcion=(TextView)viewGroup.findViewById(R.id.tvdescripcion);
         String urlfinal="http://softlution.dyndns.org/softlution/"+imagen.get(position).toString();
         Rect rect = new Rect(smartImageView.getLeft(),smartImageView.getTop(),smartImageView.getRight(),smartImageView.getBottom());
         smartImageView.setImageUrl(urlfinal,rect);
         tvnombre.setText(nombre.get(position).toString());
         tvdescripcion.setText(descripcion.get(position).toString());


         return viewGroup;
     }
 }

    @Override
    public void onClick(View view) {
        Intent intent=null;

        switch (view.getId()){

            case R.id.btninicioas:
                intent = new Intent(servicios.this, MainActivity.class);
                break;

            case R.id.btncontactoas:
                intent = new Intent(servicios.this, contacto.class);
                break;
            case R.id.btnaboutas:
                intent = new Intent(servicios.this, about.class);
                break;

        }
        startActivity(intent);
    }
}
