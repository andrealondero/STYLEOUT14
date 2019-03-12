package com.example.styleout14.Utility;

import java.util.ArrayList;

public class AbbinaColori {

    private ArrayList<String> colori;
    private String nome;
    private String[] coloriCaldi = {"apricot", "beige", "black", "bluejeans",
            "coral", "darkGreen", "gold", "magenta", "olive", "orange", "pink", "red", "rose",
            "sand", "scarlet", "silver", "tangerine", "white", "yellow"};
    private String[] coloriFreddi = {"ashGray", "azure", "beige", "black", "blue", "bluegray", "bluejeans",
            "bottlegreen", "celeste", "darkGreen", "gray", "green",
            "greenblue", "lavender", "lightblue", "midnightblue", "mintgreen",
            "navyblue", "oceanBlue", "oceanGreen", "silver", "turquoise", "violet", "white"};
    private String[] primari = {"red", "blue", "yellow"};
    private boolean eccentric;

    //il parametro nome si riferisce al nome di tipo di outfit
    public AbbinaColori(ArrayList<String> colori, String nome) {
        this.colori = colori;
        this.nome = nome;
    }

    public AbbinaColori() {
        this.eccentric=false;
    }

    public AbbinaColori(String ecc) {
        if(ecc.equals("eccentric"))
            this.eccentric = true;
    }

    //se l'outfit è di tipo "sopra" (es: camicia e jeans) non potranno essere abbinati due vestiti di colori uguali
    public boolean combina() {
        if(colori.size() == 1)
            return true;
        else if(abbina(colori.get(0),(colori.get(1)))==false && nome.equals("Sopra")) {
            return false;
        }

        else if(nome.equals("Intimo") && colori.get(colori.size()-2).equals(colori.get(colori.size()-1))==false) {
            return false;
        }
        return true;
    }

    public boolean abbina(String col1, String col2) {
        if(col1.equals(col2))
            return false;
        else if(eccentric) {
            if((col1.equals("red") && col2.equals("green"))||((col1.equals("green") && col2.equals("red"))) ||
                    (col1.equals("yellow") && col2.equals("purple"))||((col1.equals("purple") && col2.equals("yellow"))) ||
                    (col1.equals("blue") && col2.equals("orange"))||((col1.equals("orange") && col2.equals("blue")))) {
                return true;
            }
            return false;
        }
        else if((isCaldo(col1) && isCaldo(col2)) || (isFreddo(col2) && isFreddo(col1))) {
            return true;
        }
        else
            return false;
    }

    private boolean isCaldo(String col){
        for(String colr: this.coloriCaldi) {
            if (colr.equals(col))
                return true;
        }
        return false;
    }

    private boolean isFreddo(String col){
        for(String colr: this.coloriFreddi) {
            if (colr.equals(col))
                return true;
        }
        return false;
    }

    private boolean isPrimario(String col){
        for(String colr: this.primari) {
            if (colr.equals(col))
                return true;
        }
        return false;
    }
}
