package com.example.eudge.game.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eudge.game.R;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment {

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


    final protected Random random = new Random(0);
    protected int[] mas = {0, 0, 0};
    protected View[] views = new View[3];
    protected int position = 0;
    protected int checked = 0;
    protected int invisible = 0;
    public int winDJ = 0;
    public int winMP = 0;

    protected RadioButton temp;

    @OnClick(R.id.checkButton)
    protected void testButton() {
        if (mRadioGroup.getCheckedRadioButtonId() == -1)
            Toast.makeText(getActivity().getApplicationContext(), "Нужно выбрать коробку!!!", Toast.LENGTH_SHORT).show();
        else {
            for (int i = 0; i < 3; i++) {
                temp = (RadioButton) views[i];
                if (temp.isChecked())
                    checked = i;
            }
            for (int i = 0; i < 3; i++) {
                if (mas[i] == 0 && checked != i) {
                    temp = (RadioButton) views[i];
                    temp.setEnabled(false);
                    invisible = i;
                    showAlert();
                    break;
                }
            }
        }
        new Handler().postDelayed(() -> {
            temp = (RadioButton) views[position];
            temp.setTextColor(Color.RED);
        }, 2000);
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        views[0] = mRadioButton1;
        views[1] = mRadioButton2;
        views[2] = mRadioButton3;

        setRandomMas();
    }

    protected void clean() {
        for (int i = 0; i < 3; i++) {
            temp = (RadioButton) views[i];
            temp.setChecked(false);
            temp.setTextColor(Color.BLACK);
            temp.setEnabled(true);
        }
    }

    protected void setRandomMas() {

        for (int i = 0; i < 3; i++) {
            mas[i] = random.nextInt(5) - 1;
            position = i;
            if (mas[i] > 0 && mas[i] <= 2)
                break;
            else
                mas[i] = 0;
            if (i == 2 && mas[i] != 1) {
                mas[i] = 1;
                position = i;
            }
        }
    }

    protected void showAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Внимание!");
        builder.setMessage("Изменить коробки?");
        builder.setCancelable(false);
        builder.setPositiveButton("Да", (dialog, i) -> {
            for (int j = 0; j < 3; j++) {
                if (checked != j && invisible != j) {
                    temp = (RadioButton) views[j];
                    temp.setChecked(true);
                    checked = j;
                    break;
                }
            }
            if (position == checked) {
                mTextViewResult.setText("1!");
                winDJ++;
            } else {
                mTextViewResult.setText("2!");
                winMP++;
            }
        });
        builder.setNegativeButton("No", (dialog, i) -> {
            if (position == checked) {
                mTextViewResult.setText("3!");
                winMP++;
            } else {
                mTextViewResult.setText("4!");
                winDJ++;
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }
}
