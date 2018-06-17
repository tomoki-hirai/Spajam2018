package com.minoru.minoru.spajam2018;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import layout.DrumFragment;
import layout.HomeFragment;
import layout.HomeMultiFragment;
import layout.PianoFragment;

public class MultiActivity extends AppCompatActivity implements HomeMultiFragment.OnFragmentInteractionListener, DrumFragment.OnFragmentInteractionListener, PianoFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.content_main);

        //        初期画面をhomefragmentにする
        HomeMultiFragment homeMultiFragment = new HomeMultiFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, homeMultiFragment);
        transaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
