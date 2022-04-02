package com.develou.datepicker_en_android;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MainActivity2 extends AppCompatActivity {

    private Button botonFecha;
    private TextView textoEntrada;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        botonFecha = (Button)findViewById(R.id.button);
        botonFecha.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePicker();
            }
        } );
        textoEntrada = (TextView) findViewById(R.id.textView);
    }

    private void showDatePicker(){
        LocalDate date = getCurrentDate();
        DatePickerFragment.Companion.newInstance(date.getYear(), date.getMonthValue(),date.getDayOfMonth(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                textoEntrada.setText(formatDate(year,month,day));
            }
        }).show(this.getSupportFragmentManager(), "date-picker");

    }

    private LocalDate getCurrentDate(){
        String date = textoEntrada.getText().toString();
        return LocalDate.parse(date,formatter);
    }

    private String formatDate(Integer year, Integer month, Integer day){
        Integer sanitizeMonth = month + 1;
        return LocalDate.of(year,sanitizeMonth,day).format(formatter);
    }
}