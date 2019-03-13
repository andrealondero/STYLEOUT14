package com.example.styleout14.DB;

import android.content.Context;

public class Popolamento {

        private Context context;
        private DBAdapterLogin db;

        public Popolamento(Context context){
            this.context = context;
            this.db = new DBAdapterLogin(context);
            popolaOutfit();
            popolaTipoOutfit();
            popolaTipoVestito();
        }

        private void popolaOutfit(){
            db.addOutfit(0, "InvernaleFeriale", 0, 0, 0, 0);
            db.addOutfit(0, "invernale 1", 0, 5, 1, 1);
        }

        private void popolaTipoOutfit(){
            db.addTipoOutfit(1, 2, "Completo", null, null);
            int[] i = {1};
            int[] tv1 = {3, 4, 5, 6, 7, 8, 201};
            int[] tv2 = {111};
            db.addTipoOutfit(2, 2, "Sopra", i, tv1);
            db.addTipoOutfit(3, 2, "Intimo", i, tv2);
        }

        private void popolaTipoVestito(){
            db.addTipoVestito(1, "Camicia");
            db.addTipoVestito(2, "Pantalone");
            db.addTipoVestito(3, "Maglietta");
        }
}
