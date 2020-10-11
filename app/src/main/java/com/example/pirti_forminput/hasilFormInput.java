package com.example.pirti_forminput;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class hasilFormInput extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_form_input);

        Intent intent = getIntent();
        TextView textOutput = (TextView) findViewById(R.id.textOutput);
        String output = "";

        output += "Nama : " + intent.getStringExtra(FormInputData.EXTRA_INPUT_NAMA) + "\n";
        output += "Jenis Kelamin: " + intent.getStringExtra(FormInputData.EXTRA_RADIO_GROUP) + "\n";
        output += "Hobi : " + intent.getStringExtra(FormInputData.EXTRA_CHECKBOX) + "\n";
        output += "Tempat Tinggal : " + intent.getStringExtra(FormInputData.EXTRA_SPINNER) + "\n";
        output += "Tanggal Lahir : " + intent.getStringExtra(FormInputData.EXTRA_TANGGAL_LAHIR) + "\n";

        textOutput.setText(output);
    }

    public void btnKeluar(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}