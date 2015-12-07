package com.arturo.dtp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity {
    SharedPreferences configuracion;
    TextView txtTelefono, txtPassword;
    Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtTelefono=(TextView) findViewById(R.id.txtTelefono);
        txtPassword=(TextView) findViewById(R.id.txtPassword);
        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnSignUp=(Button) findViewById(R.id.btnSignUp);

        configuracion = getSharedPreferences("DTPpreferences",MODE_PRIVATE);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String telefonoGuardado=configuracion.getString("telefono", "").toString();
                String telefonoCampo=txtTelefono.getText().toString();
                String passwordGuardado=configuracion.getString("password", "").toString();
                String passwordCampo=txtPassword.getText().toString();

                if(telefonoGuardado.equals(telefonoCampo))
                {
                    Toast.makeText(getApplicationContext(), "Este número de teléfono ya está registrado",
                            Toast.LENGTH_SHORT).show();
                }else if (telefonoCampo.length()<1)
                {
                    Toast.makeText(getApplicationContext(), "Por favor inserte un número telefónico",
                            Toast.LENGTH_SHORT).show();
                }else if (passwordCampo.length()<1)
                {
                    Toast.makeText(getApplicationContext(), "Por favor inserte una contraseña",
                            Toast.LENGTH_SHORT).show();
                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://dtpapp.esy.es/u_insert.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(), error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("u_tel", txtTelefono.getText().toString());
                            params.put("u_pass", txtPassword.getText().toString());
                            return params;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);


                    /*SharedPreferences.Editor editor = configuracion.edit();
                    editor.putString("telefono", telefonoCampo);
                    editor.putString("password", passwordCampo);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), configuracion.getString("telefono", "").toString()+" registrado correctamente",
                            Toast.LENGTH_SHORT).show();*/
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                String telefonoGuardado=configuracion.getString("telefono", "").toString();
                String telefonoCampo=txtTelefono.getText().toString();
                String passwordGuardado=configuracion.getString("password", "").toString();
                String passwordCampo=txtPassword.getText().toString();

                if(telefonoGuardado.equals(telefonoCampo)&&passwordGuardado.equals(passwordCampo))
                {
                    Toast.makeText(getApplicationContext(), "Acceso correcto",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), DTP.class);
                    startActivity(i);
                }else if (telefonoCampo.length()<1)
                {
                    Toast.makeText(getApplicationContext(), "Por favor inserte un número telefónico",
                            Toast.LENGTH_SHORT).show();
                }else if (passwordCampo.length()<1)
                {
                    Toast.makeText(getApplicationContext(), "Por favor inserte una contraseña",
                            Toast.LENGTH_SHORT).show();
                }else if(telefonoGuardado.equals(telefonoCampo))
                {
                    Toast.makeText(getApplicationContext(), "Contraseña Incorrecta",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Teléfono no registrado, le sugerimos registrarse",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}
