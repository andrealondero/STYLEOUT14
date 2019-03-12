package com.example.styleout14.PrimoAccesso.Up;

import com.example.styleout14.R;

import java.util.ArrayList;
import java.util.List;

public class Up {

    private int modelUpImage;
    private List<Integer> lstUp = new ArrayList<>();
    private List<Integer> typeUp = new ArrayList<>();

    public List<Integer> getLstUp() { return lstUp;
    }

    public void setLstUp(List<Integer> lstUp) { this.lstUp = lstUp;
    }

    public List<Integer> getTypeUp() { return typeUp;
    }

    public void setTypeUp(List<Integer> typeUp) { this.typeUp = typeUp;
    }

    public Up() {

        lstUp.add(R.drawable.cardigan);
        lstUp.add(R.drawable.cardigan_uno);
        lstUp.add(R.drawable.hoodie);
        lstUp.add(R.drawable.hoodie_uno);
        lstUp.add(R.drawable.hoodie_two);
        lstUp.add(R.drawable.necked);
        lstUp.add(R.drawable.pullover);
        lstUp.add(R.drawable.lons_sleevs);

        typeUp.add(1);
        typeUp.add(2);
        typeUp.add(3);
        typeUp.add(4);
        typeUp.add(5);
        typeUp.add(6);
        typeUp.add(7);
        typeUp.add(8);
    }

    public Up(int modelUpImage) {
        this.modelUpImage = modelUpImage;
    }

    public int getModelUpImage() {
        return modelUpImage;
    }

    public void setModelUpImage(int modelUpImage) {
        this.modelUpImage = modelUpImage;
    }
}
