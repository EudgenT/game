package com.example.eudge.game;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.radioGroup)
    protected RadioGroup mRadioGroup;
    @BindView(R.id.radioButton1)
    protected RadioButton mRadioButton1;
    @BindView(R.id.radioButton2)
    protected RadioButton mRadioButton2;
    @BindView(R.id.radioButton1)
    protected RadioButton mRadioButton3;
    @BindView(R.id.textViewResult)
    protected TextView mTextViewResult;
    @BindView(R.id.checkButton)
    protected Button mCheckButton;
    @BindView(R.id.exitButton)
    protected Button mExitButton;

    final Random random = new Random();
    int [] mas = {0, 0, 0};
    View []views = {mRadioButton1, mRadioButton2, mRadioButton3};
    int position = 0;
    int invisible = 0;

    RadioButton temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRandomMas();

        mCheckButton.setOnClickListener(view -> {
            if (mRadioGroup.getCheckedRadioButtonId() == -1)
                Toast.makeText(MainActivity.this.getApplicationContext(), "Нужно выбрать коробку!!!", Toast.LENGTH_SHORT).show();
            else {
                for (int i = 0; i < 3; i++) {
                    if (mas[i] != 1 && mRadioGroup.getCheckedRadioButtonId() != i) {
                        temp = (RadioButton) views[i];
                        temp.setEnabled(false);
                        invisible = i;
                        MainActivity.this.showAlert();
                        break;
                    }
                }
            }
        });

        mExitButton.setOnClickListener(view -> {

        });
    }

    protected void setRandomMas(){
        for(int i=0; i<3; i++){
            mas[i] =  random.nextInt(1);
            position = i;
            if(mas[i]==1) break;
            if(i==2 && mas[i]!=1) {
                mas[i] = 1;
                position = i;
            }
        }
    }

     protected void showAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Внимание!");
        builder.setMessage("Изменить коробки?");
        builder.setCancelable(false);
        builder.setPositiveButton("Да", (dialog, i) -> {
            for (int j = 0; j < 3; j++) {
                if (mRadioGroup.getCheckedRadioButtonId() != j && invisible != j) {
                    temp = (RadioButton) views[j];
                    temp.setChecked(true);
                }
            }
            if (position == temp.getId()) {
                mTextViewResult.setText("Джексон!");
            }
            else {
                mTextViewResult.setText("Мирополец!");
            }
        });
        builder.setNegativeButton("No", (dialog, i) -> {
            if (position == temp.getId()) {
                mTextViewResult.setText("Мирополец!");
            }
            else {
                mTextViewResult.setText("Джексон!");
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
