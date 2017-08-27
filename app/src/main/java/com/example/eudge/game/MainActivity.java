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
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

//    @BindView(R.id.radioGroup)
//    protected RadioGroup mRadioGroup;
//    @BindView(R.id.radioButton1)
//    protected RadioButton mRadioButton1;
//    @BindView(R.id.radioButton2)
//    protected RadioButton mRadioButton2;
//    @BindView(R.id.radioButton3)
//    protected RadioButton mRadioButton3;
//    @BindView(R.id.textViewResult)
//    protected TextView mTextViewResult;
//    @BindView(R.id.checkButton) Button mCheckButton;
//    @BindView(R.id.exitButton) Button mExitButton;

    Button mCheckButton;
    Button mExitButton;
    Button mClearButton;
    RadioButton mRadioButton1;
    RadioButton mRadioButton2;
    RadioButton mRadioButton3;
    RadioGroup mRadioGroup;
    TextView mTextViewResult;

    final Random random = new Random();
    int[] mas = {0, 0, 0};
    View[] views;
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

//        ButterKnife.bind(this);

        mCheckButton = (Button) findViewById(R.id.checkButton);
        mExitButton = (Button) findViewById(R.id.exitButton);
        mClearButton = (Button) findViewById(R.id.buttonClear);

        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        mRadioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        mRadioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        mRadioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        views = new View[]{mRadioButton1, mRadioButton2, mRadioButton3};

        mTextViewResult = (TextView) findViewById(R.id.textViewResult);

        setRandomMas();

        mCheckButton.setOnClickListener(view -> {
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
                        break;
                    }
                }
            }
        });

        mClearButton.setOnClickListener(view -> {
            clean();
            setRandomMas();
        });

        mExitButton.setOnClickListener( view -> {
            mTextViewResult.setText("Джексон: " + winDJ + " Мирополец: " + winMP);
        });
    }

    protected void clean() {
        mRadioButton1.setChecked(false);
        mRadioButton2.setChecked(false);
        mRadioButton3.setChecked(false);

        for (int i = 0; i < 3; i++) {
            temp = (RadioButton) views[i];
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
                    break;
                }
            }
            if (position == checked) {
                mTextViewResult.setText("Мирополец!");
                winMP++;
            } else {
                mTextViewResult.setText("Джексон!");
                winDJ++;
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
