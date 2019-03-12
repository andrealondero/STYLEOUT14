package com.example.styleout14.PrimoAccesso.Down;

import com.example.styleout14.R;

import java.util.ArrayList;
import java.util.List;

public class Down {

    private int modelDownImage;
    private List<Integer> lstDown = new ArrayList<>();
    private List<Integer> typeDown = new ArrayList<>();

    public List<Integer> getLstDown() { return lstDown;
    }

    public void setLstDown(List<Integer> lstDown) { this.lstDown = lstDown;
    }

    public List<Integer> getTypeDown() { return typeDown;
    }

    public void setTypeDown(List<Integer> typeDown) { this.typeDown = typeDown;
    }

    public Down() {

        lstDown.add(R.drawable.trousers);
        lstDown.add(R.drawable.trousers_one);
        lstDown.add(R.drawable.trousers_two);
        lstDown.add(R.drawable.trousers_three);
        lstDown.add(R.drawable.trousers_four);
        lstDown.add(R.drawable.trousers_five);
        lstDown.add(R.drawable.skirt);
        lstDown.add(R.drawable.skirt_one);
        lstDown.add(R.drawable.skirt_two);

        typeDown.add(201);
        typeDown.add(202);
        typeDown.add(203);
        typeDown.add(204);
        typeDown.add(205);
        typeDown.add(206);
        typeDown.add(207);
        typeDown.add(208);
        typeDown.add(209);
    }

    public Down(int modelDownImage) {
        this.modelDownImage = modelDownImage;
    }

    public int getModelDownImage() {
        return modelDownImage;
    }

    public void setModelDownImage(int modelDownImage) {
        this.modelDownImage = modelDownImage;
    }
}
