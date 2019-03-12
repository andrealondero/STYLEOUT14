package com.example.styleout14.PrimoAccesso;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.styleout14.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        final Button button = findViewById( R.id.done );
        final CheckBox checkelegant = findViewById( R.id.elegantaccess );
        final CheckBox checkformal = findViewById( R.id.formalaccess );
        final CheckBox checkcasual = findViewById( R.id.casualaccess );
        final CheckBox checkusual = findViewById( R.id.usualaccess );

        checkelegant.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {
                    button.setEnabled( true );
                    startActivity( new Intent( MainActivity.this, Main2ndAccessActivity.class ) );
                } else {
                    button.setEnabled( false );
                }
            }
        } );

        checkformal.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {
                    button.setEnabled( true );
                    startActivity( new Intent( MainActivity.this, Main2ndAccessActivity.class ) );
                } else {
                    button.setEnabled( false );
                }
            }
        } );

        checkcasual.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {
                    button.setEnabled( true );
                    startActivity( new Intent( MainActivity.this, Main2ndAccessActivity.class ) );
                } else {
                    button.setEnabled( false );
                }
            }
        } );

        checkusual.setOnCheckedChangeListener( new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (buttonView.isChecked()) {
                    button.setEnabled( true );
                    startActivity( new Intent( MainActivity.this, Main2ndAccessActivity.class ) );
                } else {
                    button.setEnabled( false );
                }
            }
        } );

        ActionBar actionBar = getSupportActionBar();
        actionBar.setElevation(0);
    }
}
