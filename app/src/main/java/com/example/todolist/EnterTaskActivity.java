package com.example.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class EnterTaskActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.todolist.MESSAGE" ;
    EditText editText;
    DatePickerDialog picker;
    EditText eText;
    RadioGroup rg;
    String v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_task);
        editText = (EditText) findViewById(R.id.newTaskText);
        eText=(EditText) findViewById(R.id.editTextDate);
        eText.setInputType(InputType.TYPE_NULL);
        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(EnterTaskActivity.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        rg = (RadioGroup) findViewById(R.id.radioGroup);
        final String value = ((RadioButton)findViewById(rg.getCheckedRadioButtonId()))==null?"High":((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        v = value;
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Toast.makeText(getBaseContext(), value, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void sendMessage(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        Log.d("d1", editText.getText().toString());
        Log.d("d2",eText.getText().toString());
        Log.d("d3",((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString());
        String message = editText.getText().toString()+","+eText.getText().toString()+","+((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        setResult(RESULT_OK,intent);
        finish();
    }
    public void clear(View view){
        eText.getText().clear();
        editText.getText().clear();
        rg.clearCheck();

    }
}
