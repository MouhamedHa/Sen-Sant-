package com.esante.sensante;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Aide extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aide);
    }

    public void hide(View view) {

        TextView txtConsult = (TextView)findViewById(R.id.txtConsult);
        TextView txtAppointment = (TextView)findViewById(R.id.txtAppointment);
        TextView txtShowConsult = (TextView)findViewById(R.id.txtShowConsult);

        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) txtConsult.getLayoutParams();

        if (txtConsult.getVisibility() == View.INVISIBLE){
            params.height = 300;
            txtConsult.setLayoutParams(params);
            txtConsult.setVisibility(View.VISIBLE);

        }
        else{
            params.height = 0;
            txtConsult.setLayoutParams(params);
            txtConsult.setVisibility(View.INVISIBLE);
        }


        /*//Toggle
        if (txtConsult.getVisibility() == View.VISIBLE)
            txtConsult.setVisibility(View.INVISIBLE);
        else
            txtConsult.setVisibility(View.VISIBLE);*/

        //If you want it only one time
        //txtView.setVisibility(View.VISIBLE);
    }
}
