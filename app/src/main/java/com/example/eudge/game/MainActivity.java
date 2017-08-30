package com.example.eudge.game;

import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.eudge.game.fragment.RulesFragment;

public class MainActivity extends AppCompatActivity {

    protected RulesFragment rulesFragment;
    protected FragmentManager fragmentManager;
    protected FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rulesFragment = new RulesFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_activity, rulesFragment);
        fragmentTransaction.commit();
    }


}