package com.example.eudge.game;

import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.radioGroup)
    protected RadioGroup mRadioGroup;
    @BindView(R.id.radioButton1)
    protected RadioButton mRadioButton1;
    @BindView(R.id.radioButton2)
    protected RadioButton mRadioButton2;
    @BindView(R.id.radioButton3)
    protected RadioButton mRadioButton3;
    @BindView(R.id.textViewResult)
    protected TextView mTextViewResult;

    @OnClick(R.id.checkButton)
    protected void testButton() {
        if (mRadioGroup.getCheckedRadioButtonId() == -1)
            Toast.makeText(MainActivity.this.getApplicationContext(), "Нужно выбрать коробку!!!", Toast.LENGTH_SHORT).show();
        else {
            for (int i = 0; i < 3; i++) {
                temp = (RadioButton) views[i];
                if (temp.isChecked())
                    checked = i;
            }
            for (int i = 0; i < 3; i++) {
                if (mas[i] != 1 && checked != i) {
                    temp = (RadioButton) views[i];
                    temp.setEnabled(false);
                    invisible = i;
                    MainActivity.this.showAlert();
                    temp = (RadioButton) views[position];
                    temp.setTextColor(Color.RED);
                    break;
                }
            }
        }
    }

    @OnClick(R.id.buttonClear)
    protected void clearButton() {
        clean();
        setRandomMas();
    }

    @OnClick(R.id.exitButton)
    protected void exitButton() {
        mTextViewResult.setText("Джексон: " + winDJ + " Мирополец: " + winMP);
    }

//    Button mCheckButton;
//    Button mExitButton;
//    Button mClearButton;
//    RadioButton mRadioButton1;
//    RadioButton mRadioButton2;
//    RadioButton mRadioButton3;
//    RadioGroup mRadioGroup;
//    TextView mTextViewResult;

    final Random random = new Random();
    int[] mas = {0, 0, 0};
    View[] views = new View[3];
    int position = 0;
    int checked = 0;
    int invisible = 0;
    int winDJ = 0;
    int winMP = 0;

    RadioButton temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        views[0] = mRadioButton1;
        views[1] = mRadioButton2;
        views[2] = mRadioButton3;

//        mCheckButton = (Button) findViewById(R.id.checkButton);
//        mExitButton = (Button) findViewById(R.id.exitButton);
//        mClearButton = (Button) findViewById(R.id.buttonClear);
//
//        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);
//
//        mRadioButton1 = (RadioButton) findViewById(R.id.radioButton1);
//        mRadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
//        mRadioButton3 = (RadioButton) findViewById(R.id.radioButton3);
//        views = new View[]{mRadioButton1, mRadioButton2, mRadioButton3};
//
//        mTextViewResult = (TextView) findViewById(R.id.textViewResult);

        setRandomMas();

//        mCheckButton.setOnClickListener(view -> {
//
//        });
//
//        mClearButton.setOnClickListener(view -> {
//
//        });
//
//        mExitButton.setOnClickListener(view -> );
    }

    protected void clean() {
        for (int i=0;i<3;i++){
            temp = (RadioButton) views[i];
            temp.setChecked(false);
            temp.setTextColor(Color.BLACK);
            temp.setEnabled(true);
        }
    }

    protected void setRandomMas() {
        for (int i = 0; i < 3; i++) {
            mas[i] = random.nextInt(1);
            position = i;
            if (mas[i] == 1) break;
            if (i == 2 && mas[i] != 1) {
                mas[0] = 1;
                position = 0;
            }
        }
    }

    protected void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Внимание!");
        builder.setMessage("Изменить коробки?");
        builder.setCancelable(false);
        builder.setPositiveButton("Да", (dialog, i) -> {
            for (int j = 0; j < 3; j++) {
                if (checked != j && invisible != j) {
                    temp = (RadioButton) views[j];
                    temp.setChecked(true);
                    position = j;
                    break;
                }
            }
            if (position == checked) {
                mTextViewResult.setText("Джексон!");
                winDJ++;
            } else {
                mTextViewResult.setText("Мирополец!");
                winMP++;
            }
        });
        builder.setNegativeButton("No", (dialog, i) -> {
            if (position == checked) {
                mTextViewResult.setText("Мирополец!");
                winMP++;
            } else {
                mTextViewResult.setText("Джексон!");
                winDJ++;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
