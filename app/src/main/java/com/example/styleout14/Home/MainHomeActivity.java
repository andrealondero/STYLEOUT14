package com.example.styleout14.Home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.styleout14.DB.Popolamento;
import com.example.styleout14.R;


public class MainHomeActivity extends AppCompatActivity {

    boolean done = false;

    static FragmentManager fragmentManager_MAIN = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentManager_MAIN = getSupportFragmentManager();

        new Popolamento(this);

        Fragment fragment = new FragmentHomeOne();
        getSupportFragmentManager().beginTransaction().add(R.id.container, fragment).commit();



        final BottomNavigationView bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;

                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        selectedFragment = new FragmentHomeOne();
                        break;
                    case R.id.navigation_dashboard:
                        selectedFragment = new FragmentHomeTwo();
                        break;
                    case R.id.navigation_registrati:
                        selectedFragment = new FragmentLogin();
                        break;
                    case R.id.navigation_settings:
                        selectedFragment = new FragmentSettings();
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.container, selectedFragment).commit();
                return true;
            }
        });
    }
}

