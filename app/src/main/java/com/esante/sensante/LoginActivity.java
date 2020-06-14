package com.esante.sensante;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Presentation;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText txtEmail, txtPassword;
    private Button btnConnect;
    private String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        btnConnect = (Button)findViewById(R.id.btnConnect);

        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getText().toString().trim();
                password = txtPassword.getText().toString().trim();
                if (email.isEmpty() || password.isEmpty()){
                    Toast.makeText(LoginActivity.this, getString(R.string.error_parameters) , Toast.LENGTH_SHORT).show();
                }
                else{
                    //Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                    //startActivity(intent);
                    //finish();
                    String url = "http://192.168.137.1:8080/sen_hopital/connexion.php?email="+email+"&pwd="+password;
                    LoginServer ls = new LoginServer();
                    ls.execute(url);
                }
            }
        });

    }

    protected  class LoginServer extends AsyncTask<String, Void, String>
    {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(params[0])
                        .build();
                Response response = client.newCall(request).execute();
                return response.body().string();
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jo = new JSONObject(result);
                String status = jo.getString("status");

                if(status.equalsIgnoreCase("OK")){
                    /*Intent intent = new Intent(MainActivity.this, Presentation.class);
                    startActivity(intent);*/
                    Toast.makeText(LoginActivity.this, "Réussi", Toast.LENGTH_SHORT).show();
                }
                else {
                    String error = getString(R.string.error_parameters);
                    Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                e.printStackTrace();
                String error = getString(R.string.error_connection);
                Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT).show();
            }
        }

    }
}
