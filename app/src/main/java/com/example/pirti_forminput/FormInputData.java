package com.example.pirti_forminput;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FormInputData extends AppCompatActivity {
    public static final String EXTRA_INPUT_NAMA = "com.example.application.pirti_forminput.EXTRA_INPUT_NAMA";
    public static final String EXTRA_RADIO_GROUP = "com.example.application.pirti_forminput.EXTRA_RADIO_GROUP";
    public static final String EXTRA_CHECKBOX = "com.example.application.pirti_forminput.EXTRA_CHECKBOX";
    public static final String EXTRA_SPINNER = "com.example.application.pirti_forminput.EXTRA_SPINNER";
    public static final String EXTRA_TANGGAL_LAHIR = "com.example.application.pirtiforminput.EXTRA_TANGGAL_INPUT";
    RadioGroup radioGroup;
    RadioButton radioButton;
    Spinner spinnerTempat;
    String spinnerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_input_data);

        Button btnProses = (Button) findViewById(R.id.btnProses);
        btnProses.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openHasilFormInput();
            }
        });

        radioGroup = findViewById(R.id.radioGroup);
        spinnerTempat = (Spinner) findViewById(R.id.spinnerLokasi);

        List<String> listTempat = new ArrayList<>();
        listTempat.add("Yogyakarta");
        listTempat.add("Sleman");
        listTempat.add("Bantul");
        listTempat.add("Westprog");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTempat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerTempat.setAdapter(adapter);
        spinnerTempat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinnerText = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        CheckBox lainya = (CheckBox) findViewById(R.id.hobiLain);
        lainya.setOnCheckedChangeListener(new CheckBox.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                EditText hobiLain = (EditText) findViewById(R.id.inputHobiLain);
                if(isChecked)
                    hobiLain.setEnabled(true);
                else
                    hobiLain.setEnabled(false);
            }
        });

        final int[] time = new int[3];
        final Calendar calendar = Calendar.getInstance();
        final EditText tanggalLahir = (EditText) findViewById(R.id.inputTanggalLahir);
        final String[] bulan = {
            "Januari",
            "Februari",
            "Maret",
            "April",
            "Mei",
            "Juni",
            "Juli",
            "Agustus",
            "September",
            "Oktober",
            "November",
            "Desember"
        };

        tanggalLahir.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormInputData.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        time[0] = year;
                        time[1] = month;
                        time[2] = dayOfMonth;

                        tanggalLahir.setText(Integer.toString(time[2]) + ", " + bulan[time[1]] + " " + time[0]);
                    }
                }, time[0], time[1], time[2]);
                datePickerDialog.show();
            }
        });
    }

    public String getCheckBoxValue(){
        CheckBox berenang = (CheckBox) findViewById(R.id.hobiBerenang);
        CheckBox programming = (CheckBox) findViewById(R.id.hobiProgramming);
        CheckBox lainya = (CheckBox) findViewById(R.id.hobiLain);
        EditText hobiLain = (EditText) findViewById(R.id.inputHobiLain);

        String merger = "";
        if(berenang.isChecked()) merger += berenang.getText().toString() + ", ";
        if(programming.isChecked()) merger += programming.getText().toString() + ", ";
        if(lainya.isChecked()) merger += hobiLain.getText().toString() + ", ";

        if(!merger.isEmpty()) {
            StringBuilder str_merger = new StringBuilder(merger);
            str_merger.setCharAt(merger.indexOf(',', merger.length() - 3), '\0');
            merger = str_merger.toString();
        }else merger = "Tidak Ada";

        return merger;
    }

    public void openHasilFormInput(){
        EditText inputNama = (EditText) findViewById(R.id.inputNama);
        String nama = "Anonim";
        if(!inputNama.getText().toString().isEmpty())
            nama = inputNama.getText().toString();

        String strRadioButton = "Anonim";
        int radioID = radioGroup.getCheckedRadioButtonId();
        if(radioID != (-1)){
            radioButton = findViewById(radioID);
            strRadioButton = radioButton.getText().toString();
        }

        EditText inputTanggal = (EditText) findViewById(R.id.inputTanggalLahir);
        String tanggal = "Tidak Di Ketahui";
        if(!inputTanggal.getText().toString().isEmpty())
            tanggal = inputTanggal.getText().toString();

        Intent intent = new Intent(this, hasilFormInput.class);
        intent.putExtra(EXTRA_INPUT_NAMA, nama);
        intent.putExtra(EXTRA_RADIO_GROUP, strRadioButton);
        intent.putExtra(EXTRA_CHECKBOX, getCheckBoxValue());
        intent.putExtra(EXTRA_SPINNER, spinnerText);
        intent.putExtra(EXTRA_TANGGAL_LAHIR, tanggal);
        startActivity(intent);
    }
}