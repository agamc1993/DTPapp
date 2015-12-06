package com.arturo.dtp;

import android.app.Dialog;
import android.support.v4.app.DialogFragment;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.app.DatePickerDialog;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class Reporte extends AppCompatActivity {
    Button btnFecha, btnHora;
    DialogFragment newFragmentTime = new TimePickerFragment();
    DialogFragment newFragmentDate = new DatePickerFragment();
    static String txtHora="";
    static String txtFecha="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        btnFecha=(Button) findViewById(R.id.btnFecha);
        btnHora=(Button) findViewById(R.id.btnHora);

        btnHora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newFragmentTime.show(getSupportFragmentManager(), "hora");
                Toast.makeText(getApplicationContext(), "Denuncia fijada en "+txtFecha+" "+txtHora,
                        Toast.LENGTH_SHORT).show();
            }
        });

        btnFecha.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                newFragmentDate.show(getSupportFragmentManager(), "fecha");
                Toast.makeText(getApplicationContext(), "Denuncia fijada en "+txtFecha+" "+txtHora,
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            txtHora= hourOfDay+":"+minute;
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            txtFecha= year+"/"+month+"/"+day;
        }
    }
}
