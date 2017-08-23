package com.example.eudge.game;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    final Random random = new Random();
    int [] mas = new int [3];
    View []views = new View[3];
    int position = 0;
    int invisible = 0;

    Button button1;
    RadioButton temp;
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    EditText editTextDJ;
    EditText editTextMP;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = (Button) findViewById(R.id.button1);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);

        views[0] = radioButton1;
        views[1] = radioButton2;
        views[2] = radioButton3;

        editTextDJ = (EditText) findViewById(R.id.editTextDJ);
        editTextMP = (EditText) findViewById(R.id.editTextMP);

        for(int i=0; i<3; i++){
            mas[i] = 0;
        }

        for(int i=0; i<3; i++){
            mas[i] =  random.nextInt(1);
            position = i;
            if(mas[i]==1) break;
            if(i==2 && mas[i]!=1) {
                mas[i] = 1;
                position = i;
            }
        }

        button1.setOnClickListener(view -> {
            if(radioGroup.getCheckedRadioButtonId() == -1)
            Toast.makeText(getApplicationContext(), "Нужно выбрать коробку!!!", Toast.LENGTH_SHORT).show();
            else{
                for (int i=0; i<3; i++){
                    if(mas[i] != 1 && radioGroup.getCheckedRadioButtonId() != i) {
                        temp = (RadioButton) views[i];
                        temp.setEnabled(false);
                        invisible = i;
                        showAlert();
                        break;
                    }
                }
            }
        });

    }

    public void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Внимание!");
        builder.setMessage("Изменить коробки?");
        builder.setCancelable(false);
        builder.setPositiveButton("Да", (dialog, i) -> {
            for (int j = 0; j < 3; j++) {
                if (radioGroup.getCheckedRadioButtonId() != j && invisible != j) {
                    temp = (RadioButton) views[j];
                    temp.setChecked(true);
                }
            }
            if (position == temp.getId()) {
                editTextMP.setText("Ты победил!");
            }
            else {
                editTextDJ.setText("Ты победил!");
            }
        });
        builder.setNegativeButton("No", (dialog, i) -> {
            if (position == temp.getId()) {
                editTextDJ.setText("Ты победил!");
            }
            else {
                editTextDJ.setText("Ты победил!");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
