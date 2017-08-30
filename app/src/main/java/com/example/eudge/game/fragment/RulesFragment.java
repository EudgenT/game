package com.example.eudge.game.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eudge.game.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class RulesFragment extends Fragment {

    protected MainFragment beginFragment;
    protected FragmentManager fragmentManager;
    protected FragmentTransaction fragmentTransaction;

    @OnClick(R.id.beginButton)
    protected void beginButton (){
        beginFragment = new MainFragment();
        fragmentManager = getActivity().getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity, beginFragment);
        fragmentTransaction.commit();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.rules_fragment,container,false);
        ButterKnife.bind(this, view);
        return view;
    }
}
