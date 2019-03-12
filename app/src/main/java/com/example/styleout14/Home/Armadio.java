package com.example.styleout14.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.styleout14.PrimoAccesso.Down.Down;
import com.example.styleout14.PrimoAccesso.Top.Top;
import com.example.styleout14.PrimoAccesso.Up.Up;
import com.example.styleout14.R;

public class Armadio extends Fragment {

    public Armadio() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate( R.layout.activity_armadio, container, false);

        final Up up = new Up();
        final Down down = new Down();
        final Top top = new Top();

        RelativeLayout reLT = view.findViewById(R.id.top_list);
        RelativeLayout reLU = view.findViewById(R.id.up_list);
        RelativeLayout relD = view.findViewById(R.id.down_list);

        return view;
    }
}
