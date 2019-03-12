package com.example.styleout14.Home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.styleout14.DB.DBAdapterLogin;
import com.example.styleout14.DB.Popolamento;
import com.example.styleout14.PrimoAccesso.Down.Down;
import com.example.styleout14.PrimoAccesso.Top.Top;
import com.example.styleout14.PrimoAccesso.Up.Up;
import com.example.styleout14.R;
import com.example.styleout14.Utility.Preferenze;

import java.util.ArrayList;

public class MainHomeActivity extends AppCompatActivity {

// ex fragmentHomeOne
    ImageView imageView;
    ImageView imageView2;
    DBAdapterLogin db;
    ArrayList<Vestito> selectedOutfit;
    ArrayList<Integer> postFatto;
    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main_home );

        // ex fragmentHomeOne
        db = new DBAdapterLogin(view.getContext());
        final Preferenze pref = new Preferenze();
        final Up up = new Up();
        final Down down = new Down();
        final Top top = new Top();
        postFatto = new ArrayList<>();
        selectedOutfit = new ArrayList<>();

        imageView = findViewById(R.id.upperView);
        imageView2 = findViewById(R.id.downView);

        int asd = view.getContext().getResources().getIdentifier("tshirt_red", "drawable", MainHomeActivity.this.getPackageName());

        db.addVestito("red", "#C40233", 1, "maglia rossa", "avorio", 3, R.drawable.ic_tshirt);
        db.addVestito("yellow", "#FFD400", 1, "pantalone", "cacca", 201, R.drawable.pantaloni_sigaretta_tasconi);

        ArrayList<Vestito> id = db.getVestitiFatti("InvernaleFeriale", pref, postFatto);
        StringBuilder sb = new StringBuilder();
        if(id!=null) {
            int i = 0;
            for (Vestito v1 : id) {

                int res = 0;
                if(Integer.parseInt(v1.getTipoVestito())>100 && Integer.parseInt(v1.getTipoVestito()) < 200)
                    res = up.getLstUp().get(up.getTypeUp().indexOf(Integer.parseInt(v1.getTipoVestito())));

                else if(Integer.parseInt(v1.getTipoVestito())<100) {
                    res = top.getLstTop().get(top.getTypeTop().indexOf(Integer.parseInt(v1.getTipoVestito())));
                    imageView.setImageResource(res);
                    imageView.setBackgroundColor( Color.parseColor(v1.getColorCode()));
                }

                else {
                    res = down.getLstDown().get(down.getTypeDown().indexOf(Integer.parseInt(v1.getTipoVestito())));
                    imageView2.setImageResource(res);
                    imageView2.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                }
            }
        }

        // fine ex fragmentHomeOne

        new Popolamento( this );

        BottomNavigationView bottomNav = findViewById( R.id.home_navigation );
        bottomNav.setOnNavigationItemSelectedListener( navListener );
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {

                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                    switch (menuItem.getItemId()) {

                        case R.id.navigation_home:
                            Intent intent = new Intent( MainHomeActivity.this, Armadio.class );
                            startActivity( intent );
                            break;
                        case R.id.navigation_dashboard:
                            Intent intent0 = new Intent( MainHomeActivity.this, Armadio.class );
                            startActivity( intent0 );
                            break;
                        case R.id.navigation_registrati:
                            Intent intent1 = new Intent( MainHomeActivity.this, Armadio.class );
                            startActivity( intent1 );
                            break;
                        case R.id.navigation_settings:
                            Intent intent2 = new Intent( MainHomeActivity.this, Armadio.class );
                            startActivity( intent2 );
                            break;
                    }
                    return false;

                }
            };

    public void Accapt(View view) {
        getSupportFragmentManager().beginTransaction().replace( R.id.model_line );
        new FragmentHomeTwo().commit;

        if (selectedOutfit!=null)
            db.addOutfitFatto(selectedOutfit.get(0).getSelected(), selectedOutfit);
        Toast.makeText(MainHomeActivity.this, "Outfit SCELTO", Toast.LENGTH_SHORT).show();
    }

    public void Refuse(View view) {

        if(postFatto.size() >= db.getoutfitFattiCount()-1){
            postFatto.clear();
        }
        ArrayList<Vestito> id = db.getVestitiFatti("InvernaleFeriale", pref, postFatto);
        postFatto.add(id.get(0).getPosFatto());
        StringBuilder sb = new StringBuilder();

        for(Vestito v1: id){
            Toast.makeText(MainHomeActivity.this, sb.append(v1.getId())+ " ", Toast.LENGTH_SHORT).show();
        }
        if(id!=null) {
            for (Vestito v1 : id) {

                int res = 0;
                if(Integer.parseInt(v1.getTipoVestito())>100 && Integer.parseInt(v1.getTipoVestito()) < 200)
                    res = up.getLstUp().get(up.getTypeUp().indexOf(Integer.parseInt(v1.getTipoVestito())));

                else if(Integer.parseInt(v1.getTipoVestito())<100) {
                    res = top.getLstTop().get(top.getTypeTop().indexOf(Integer.parseInt(v1.getTipoVestito())));
                    imageView.setImageResource(res);
                    imageView.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                }

                else {
                    res = down.getLstDown().get(down.getTypeDown().indexOf(Integer.parseInt(v1.getTipoVestito())));
                    imageView2.setImageResource(res);
                    imageView2.setBackgroundColor(Color.parseColor(v1.getColorCode()));
                }
            }
        }
    }

    public void Modify(View view) {
        Intent intent = new Intent( MainHomeActivity.this, Armadio.class );
        startActivity( intent );
    }

    public void Outlist(View view) {
        Intent intent = new Intent( MainHomeActivity.this, Armadio.class );
        startActivity( intent );
    }
}

