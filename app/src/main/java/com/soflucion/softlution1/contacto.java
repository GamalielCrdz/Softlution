package com.soflucion.softlution1;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class contacto extends Activity implements OnClickListener {

    Session session = null;
    ProgressDialog pdialog = null;
    Context context = null;
    TextView reciep;
    EditText  sub, msg, mail, num, nom;
    String rec, subject, textMessage, txtmail, txtnum, txtnom;

    Button btninicioac, btnservicioac, btnabout2, btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
/*
        btninicioac = (Button) findViewById(R.id.btninicioac);
        btninicioac.setOnClickListener(this);

        btnservicioac = (Button) findViewById(R.id.btnservicioac);
        btnservicioac.setOnClickListener(this);

        btnabout2 = (Button) findViewById(R.id.btnabout2);
        btnabout2.setOnClickListener(this);
*/
        context = this;

        Button login = (Button) findViewById(R.id.btn_submit);
        reciep = (TextView) findViewById(R.id.et_to);
        sub = (EditText) findViewById(R.id.et_sub);
        msg = (EditText) findViewById(R.id.et_text);
        mail = (EditText) findViewById(R.id.et_mail);
        num = (EditText) findViewById(R.id.et_num);
        nom = (EditText) findViewById(R.id.et_nom);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
       /*Intent intent=null;


        switch (v.getId()) {

            case R.id.btninicioac:
                intent = new Intent(contacto.this, MainActivity.class);
                break;

            case R.id.btnservicioac:
                intent = new Intent(contacto.this, servicios.class);
                break;
            case R.id.btnabout2:
                intent = new Intent(contacto.this, about.class);
                break;
            }
        startActivity(intent);*/

        rec = reciep.getText().toString();
        subject = sub.getText().toString();
        textMessage = msg.getText().toString();
        txtmail = mail.getText().toString();
        txtnum = num.getText().toString();
        txtnom = nom.getText().toString();


        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("5oftlucion.mty@gmail.com", "Softlucion2018");
            }
        });

        pdialog = ProgressDialog.show(context, "", "Enviando...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }


        class RetreiveFeedTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                try {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("5oftlucion.mty@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rec));
                    message.setSubject(subject);
                    message.setContent("<b>Nombre:</b> "+ txtnom +"<br /><b>Numero de Telefono:</b> "+ txtnum +" <br /><b>Correo: </b>"+ txtmail +" <br /> <b>Mensaje: </b>" + textMessage, "text/html; charset=utf-8");
                    Transport.send(message);
                } catch (MessagingException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;

        }


            @Override
            protected void onPostExecute(String result) {
                pdialog.dismiss();
                reciep.getText();
                msg.setText("");
                sub.setText("");
                mail.setText("");
                num.setText("");
                nom.setText("");
                Toast.makeText(getApplicationContext(), "Mensaje Enviado", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }





