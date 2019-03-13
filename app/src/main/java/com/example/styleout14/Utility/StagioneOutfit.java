package com.example.styleout14.Utility;

import java.util.Calendar;

public class StagioneOutfit {

    private Calendar calendar = Calendar.getInstance();
    private int mese = calendar.get(Calendar.MONTH);

    public String getStagione(){

        if(mese == 3 || mese == 4 || mese == 5)
            return "Primaverile";

        else if(mese == 6 || mese == 7 || mese == 8)
            return "Estivo";

        else if(mese == 9 || mese == 10 || mese == 11)
            return "Autunnale";

        else
            return "Invernale";

    }
}
