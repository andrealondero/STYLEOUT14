package com.example.styleout14.PrimoAccesso.Top;

import com.example.styleout14.R;

import java.util.ArrayList;
import java.util.List;

public class Top {

    private int modelImage;
    private List<Integer> lstTop = new ArrayList<>();
    private List<Integer> typeTop = new ArrayList<>();

    public List<Integer> getLstTop() { return lstTop;
    }

    public void setLstTop(List<Integer> lstTop) { this.lstTop = lstTop;
    }

    public List<Integer> getTypeTop() { return typeTop;
    }

    public void setTypeTop(List<Integer> typeTop) { this.typeTop = typeTop;
    }

    public Top() {

        lstTop.add(R.drawable.cardigan);
        lstTop.add(R.drawable.cardigan_uno);
        lstTop.add(R.drawable.hoodie);
        lstTop.add(R.drawable.hoodie_uno);
        lstTop.add(R.drawable.hoodie_two);
        lstTop.add(R.drawable.necked);
        lstTop.add(R.drawable.pullover);
        lstTop.add(R.drawable.lons_sleevs);

        typeTop.add(1);
        typeTop.add(2);
        typeTop.add(3);
        typeTop.add(4);
        typeTop.add(5);
        typeTop.add(6);
        typeTop.add(7);
        typeTop.add(8);
    }

    public Top(int modelImage) {
        this.modelImage = modelImage;
    }

    public int getModelImage() {
        return modelImage;
    }

    public void setModelImage(int modelImage) {
        this.modelImage = modelImage;
    }
}
