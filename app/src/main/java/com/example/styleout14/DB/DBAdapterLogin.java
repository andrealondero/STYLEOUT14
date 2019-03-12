package com.example.styleout14.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.styleout14.Home.Vestito;
import com.example.styleout14.Utility.AbbinaColori;
import com.example.styleout14.Utility.Preferenze;

import java.util.ArrayList;
import java.util.Random;

public class DBAdapterLogin {

    private Context context;
    private SQLiteDatabase database;
    private DBHelper dbHelper;

    public DBAdapterLogin(Context context) {
        this.context = context;
    }

    public DBAdapterLogin open() throws SQLException {
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
        database.close();
    }

    public void addDati(String email, String password){
        open();

        ContentValues values = new ContentValues();
        values.put(DBHelper.KEY_EMAIL, email);
        values.put(DBHelper.KEY_PASSWORD, password);

        database.insert(DBHelper.TABLE_CONTACTS, null, values);

        close();
    }

    public void addVestito(String colore, String colorCode, int disponibile, String nome, String tessuto, int tipoVestito_ID, int pic_tag){
        open();

        ContentValues values = new ContentValues();
        values.put("COLORE", colore);
        values.put("COLORE_CODE", colorCode);
        values.put("DISPONIBILE", disponibile);
        values.put("NOME", nome);
        values.put("TESSUTO", tessuto);
        values.put("TIPOVESTITO_ID", tipoVestito_ID);
        values.put("PIC_TAG", pic_tag);

        database.insert(DBHelper.TABLE_VESTITI, null, values);

        close();
    }

    void addOutfit(int feriale, String nome, int temperatura, int temperaturaMassima, int outfitprincipale_id, int tipooutfit_id){
        open();

        ContentValues values = new ContentValues();
        values.put("FERIALE", feriale);
        values.put("NOME", nome);
        values.put("TEMPERATURA", temperatura);
        values.put("TEMPERATURAMASSIMA", temperaturaMassima);
        values.put("OUTFITPRINCIPALE_ID", outfitprincipale_id);
        values.put("TIPOOUTFIT_ID", tipooutfit_id);

        database.insert(DBHelper.TABLE_OUTFIT, null, values);

        close();
    }

    public void addOutfitFatto(int outfit_collegato, ArrayList<Vestito> vestiti){

        String countQuery = "SELECT  * FROM " + DBHelper.TABLE_OUTFIT_FATTI;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        ContentValues values = new ContentValues();
        values.put("ID", count+1);
        values.put("OUTFITCOLLEGATO_ID", outfit_collegato);

        database.insert(DBHelper.TABLE_OUTFIT_FATTI, null, values);

        ContentValues values2 = new ContentValues();
        for(Vestito v: vestiti){
            values2.put("vestitiFatti_ID", v.getId());
            values2.put("outfitCollegati_ID", count+1);
            database.insert(DBHelper.TABLE_OUTFITFATTO_VESTITO, null, values2);
        }


    }

    public int getoutfitFattiCount(){
        open();
        String countQuery = "SELECT  * FROM " + DBHelper.TABLE_OUTFIT_FATTI;
        Cursor cursor = database.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();
        return count;
    }

    void addTipoOutfit(int id, int outfitprincipale, String nome, int[] tipoOutfitPrincipale_ID, int[] tipiVestito){
        open();

        ContentValues values = new ContentValues();
        values.put("NOME", nome);

        database.insert(DBHelper.TABLE_TIPOOUTFIT, null, values);

        ContentValues values2 = new ContentValues();

        if(tipoOutfitPrincipale_ID!=null) {
            for (int i : tipoOutfitPrincipale_ID) {
                values2.put("tipoOutfitPrincipale_ID", outfitprincipale);
                values2.put("tipiOutfit_ID", id);
            }
            database.insert(DBHelper.TABLE_TIPOOUTFIT_TIPOOUTFIT, null, values2);
        }

        ContentValues values3 = new ContentValues();

        if(tipiVestito!=null){
            for(int i : tipiVestito){
                values3.put("tipiOutfit_ID", id);
                values3.put("tipiVestito_ID", i);
                database.insert(DBHelper.TABLE_TIPOVESTITO_TIPOOUTFIT, null, values3);
            }
        }

        close();

    }

    void addTipoVestito(int id, String nome){
        open();

        ContentValues values = new ContentValues();
        values.put("ID", id);
        values.put("NOME", nome);

        database.insert(DBHelper.TABLE_TIPOVESTITO, null, values);

        close();
    }

    ArrayList<Vestito> getVestiti(String tipo){
        open();
        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_VESTITI + " WHERE DISPONIBILE = ? OR DISPONIBILE = ?";
        Cursor cursor = database.rawQuery(selectQuery, new String[]{"0","1"});

        ArrayList<Vestito> vest = new ArrayList<Vestito>();

        if(cursor.moveToFirst()) {
            do {
                Vestito v = new Vestito();
                v.setColore(cursor.getString(1));
                v.setColorCode(cursor.getString(2));
                v.setDisponibile(cursor.getString(3));
                v.setNome(cursor.getString(4));
                v.setTessuto(cursor.getString(5));
                v.setTipoVestito(cursor.getString(6));
                v.setPic_tag(Integer.parseInt(cursor.getString(8)));

                if (tipo.equals("top")) {
                    if (Integer.parseInt(v.getTipoVestito()) < 100) {
                        vest.add(v);
                    }
                }
                if (tipo.equals("up")) {
                    if (Integer.parseInt(v.getTipoVestito()) > 100 && Integer.parseInt(v.getTipoVestito()) < 200) {
                        vest.add(v);
                    }
                }
                if (tipo.equals("down")) {
                    if (Integer.parseInt(v.getTipoVestito()) > 200) {
                        vest.add(v);
                    }
                }
            } while (cursor.moveToNext()) ;
        }
        return vest;
    }

    public ArrayList<Vestito> getVestitiFatti(String outfit, Preferenze pref, ArrayList<Integer> posFatto){

        ArrayList<Vestito> outfitFatto = new ArrayList<>();
        int temperatura = 0;
        open();
        String selectquery = "SELECT * FROM " + DBHelper.TABLE_OUTFIT + " WHERE NOME = ? ";
        Cursor cursor = database.rawQuery(selectquery, new String[]{outfit});
        int id_out = 0;
        if(cursor.moveToFirst()){
            do{
                id_out = Integer.parseInt(cursor.getString(0));
                break;
            }while(cursor.moveToNext());
        }

        String selectQuery2 = "SELECT * FROM " + DBHelper.TABLE_OUTFIT + " WHERE OUTFITPRINCIPALE_ID = ? ";
        Cursor cursor2 = database.rawQuery(selectQuery2, new String[]{String.valueOf(id_out)});
        int id_out_sel = 0;
        if(cursor2.moveToFirst()){
            do{
                if(temperatura >= Integer.parseInt(cursor2.getString(3)) && temperatura <= Integer.parseInt(cursor2.getString(4))) {
                    id_out_sel = Integer.parseInt(cursor2.getString(0));
                    break;
                }
            }while(cursor2.moveToNext());
        }

        ArrayList<String> listaID = new ArrayList<>();

        String selectQuery3 = "SELECT * FROM " + DBHelper.TABLE_OUTFIT_FATTI + " WHERE OUTFITCOLLEGATO_ID = ?";
        Cursor cursor3 = database.rawQuery(selectQuery3, new String[]{String.valueOf(id_out_sel)});
        if(cursor3.moveToFirst()) {
            do {
                listaID.add(cursor3.getString(0));
            } while (cursor3.moveToNext());
        }
        else
            return getVestiti(outfit, pref);

        Random rand = new Random();
        int n = 0;
        do
            n = rand.nextInt(listaID.size())+1;
        while (posFatto.contains(n));


        String[] listaIDA = new String[listaID.size()];
        for (int i = 0; i < listaID.size(); i++) {
            listaIDA[i] = listaID.get(i);
        }

        ArrayList<ArrayList<String>> lista_id = new ArrayList<ArrayList<String>>();
        String selectQuery4 = "SELECT * FROM " + DBHelper.TABLE_OUTFITFATTO_VESTITO + " WHERE outfitCollegati_ID = ?";
        Cursor cursor4 = database.rawQuery(selectQuery4, new String[]{listaID.get(n-1)});


        ArrayList<String> idVestiti = new ArrayList<String>();
        if (cursor4.moveToFirst()) {
            do {
                idVestiti.add(cursor4.getString(0));
            } while (cursor4.moveToNext());
        }

        String[] listaVestiti = new String[idVestiti.size()];
        for(int i=0; i<idVestiti.size(); i++){
            listaVestiti[i] = idVestiti.get(i);
        }

        String selectQuery5 = "SELECT * FROM " + DBHelper.TABLE_VESTITI + " WHERE ID = ? AND DISPONIBILE = 1 OR ID = ? AND DISPONIBILE = 1";
        Cursor cursor5 = database.rawQuery(selectQuery5, listaVestiti);

        if (cursor5==null)
            return getVestiti(outfit, pref);

        if (cursor5.moveToFirst()) {
            do {
                Vestito v = new Vestito();
                v.setId(cursor5.getString(0));
                v.setColore(cursor5.getString(1));
                v.setColorCode(cursor5.getString(2));
                v.setDisponibile(cursor5.getString(3));
                v.setNome(cursor5.getString(4));
                v.setTessuto(cursor5.getString(5));
                v.setTipoVestito(cursor5.getString(6));
                v.setPic_tag(Integer.parseInt(cursor5.getString(8)));
                v.setPosFatto(n);
                outfitFatto.add(v);
            } while (cursor5.moveToNext());
        }
        return outfitFatto;
    }

    ArrayList<Vestito> getVestiti(String outfit, Preferenze prefer){
        int temperatura = 0;
        open();
        Cursor cursor = database.query(DBHelper.TABLE_OUTFIT, new String[]{"ID"}, "NOME" + "=?",
                new String[]{outfit},null,null,null,null);
        int id_out = 0;

        if(cursor!=null)
            cursor.moveToFirst();
        id_out = Integer.parseInt(cursor.getString(0));

        Cursor cursor2 = database.query(DBHelper.TABLE_OUTFIT, new String[]{"ID","TEMPERATURA","TEMPERATURAMASSIMA","TIPOOUTFIT_ID"}, "OUTFITPRINCIPALE_ID" + "=?",
                new String[]{String.valueOf(id_out)},null,null,null,null);

        int selected_out = 0;
        int id_tipoOutfit = 0;
        if(cursor2.moveToFirst()){
            do{
                if(temperatura >= Integer.parseInt(cursor2.getString(1)) && temperatura <= Integer.parseInt(cursor2.getString(2))) {
                    selected_out = Integer.parseInt(cursor2.getString(0));
                    id_tipoOutfit = Integer.parseInt(cursor2.getString(3));
                    break;
                }
            }while (cursor2.moveToNext());
        }

        String selectQuery = "SELECT * FROM " + DBHelper.TABLE_TIPOOUTFIT_TIPOOUTFIT + " WHERE tipoOutfitPrincipale_ID = ?";
        Cursor cursor3 = database.rawQuery(selectQuery, new String[]{String.valueOf(selected_out)});

        ArrayList<String> tipiOutfit_id = new ArrayList<String>();
        if(cursor3.moveToFirst()) {
            do {
                tipiOutfit_id.add(cursor3.getString(1));
            } while (cursor3.moveToNext());
        }

        ArrayList<String> sopra = new ArrayList<String>();
        ArrayList<Vestito> lista_vestiti = new ArrayList<Vestito>();

        ArrayList<ArrayList<Vestito>> eccentrici = new ArrayList<ArrayList<Vestito>>();
        ArrayList<ArrayList<Vestito>> abbinati = new ArrayList<ArrayList<Vestito>>();
        ArrayList<ArrayList<Vestito>> disponibil = new ArrayList<ArrayList<Vestito>>();
        ArrayList<ArrayList<Vestito>> ripiego = new ArrayList<ArrayList<Vestito>>();

        for(String s: tipiOutfit_id){
            if(s.equals("2")){

                ArrayList<Vestito> parteSopra = new ArrayList<Vestito>();
                ArrayList<Vestito> parteSotto = new ArrayList<Vestito>();

                String selectQuery2 = "SELECT * FROM " + DBHelper.TABLE_TIPOVESTITO_TIPOOUTFIT + " WHERE tipiOutfit_ID = ?";
                Cursor cursor4 = database.rawQuery(selectQuery2, new String[]{s});

                if(cursor4.moveToFirst()){
                    do{
                        sopra.add(cursor4.getString(1));
                    }while(cursor4.moveToNext());
                }

                String selectQuery32 = "SELECT * FROM " + DBHelper.TABLE_VESTITI + " WHERE TIPOVESTITO_ID = ? OR TIPOVESTITO_ID = ? OR TIPOVESTITO_ID = ?";
                StringBuilder sb = new StringBuilder();
                sb.append("SELECT * FROM " + DBHelper.TABLE_VESTITI + " WHERE TIPOVESTITO_ID = ? ");
                for(int i=1; i<sopra.size(); i++){
                    sb.append("OR TIPOVESTITO_ID = ? ");
                }
                String selectQuery3 = sb.toString();
                String[] sopr = new String[sopra.size()];
                for(int i=0; i<sopra.size(); i++){
                    sopr[i] = sopra.get(i);
                }
                Cursor cursor5 = database.rawQuery(selectQuery3, sopr);

                if(cursor5.moveToFirst()) {
                    do {
                        Vestito v = new Vestito();
                        v.setId(cursor5.getString(0));
                        v.setColore(cursor5.getString(1));
                        v.setColorCode(cursor5.getString(2));
                        v.setDisponibile(cursor5.getString(3));
                        v.setNome(cursor5.getString(4));
                        v.setTessuto(cursor5.getString(5));
                        v.setTipoVestito(cursor5.getString(6));
                        v.setPic_tag(Integer.parseInt(cursor5.getString(8)));
                        v.setSelected(selected_out);

                        if(sopra.contains(cursor5.getString(6)) && Integer.parseInt(cursor5.getString(6))<200) {
                            parteSopra.add(v);
                        }
                        else if(sopra.contains(cursor5.getString(6)) && Integer.parseInt(cursor5.getString(6))>=200){
                            parteSotto.add(v);
                        }
                    } while (cursor5.moveToNext());
                }

                for(Vestito v: parteSopra) {
                    for(Vestito v2: parteSotto) {
                        ArrayList<Vestito> selezionati = new ArrayList<Vestito>();
                        selezionati.add(v);
                        selezionati.add(v2);
                        if(v.isDisponibile().equals("1")) {

                            if(v2.isDisponibile().equals("1")) {

                                if(new AbbinaColori("eccentric").abbina(v.getColore(), v2.getColore())) {
                                    eccentrici.add(selezionati);
                                }

                                else if(new AbbinaColori().abbina(v.getColore(), v2.getColore())) {
                                    abbinati.add(selezionati);
                                }
                                else
                                    disponibil.add(selezionati);
                            }
                            else if(v2.isDisponibile().equals("0") && new AbbinaColori().abbina(v.getColore(),v2.getColore()))
                                ripiego.add(selezionati);
                        }
                    }
                }

                boolean ecc = false;
                boolean abb = false;
                boolean disp = false;
                boolean ripie = false;
                boolean pref = prefer.isEccentric();

                if(eccentrici.size()>0 && pref)
                    ecc = true;
                else if(abbinati.size()>0)
                    abb=true;
                else if(eccentrici.size()>0)
                    ecc=true;
                else if(disponibil.size()>0)
                    disp=true;
                else ripie=true;

                if(ecc) {
                    Random r = new Random();
                    int i = r.nextInt(eccentrici.size());
                    lista_vestiti.addAll(eccentrici.get(i));
                }

                if(abb) {
                    Random r = new Random();
                    int i = r.nextInt(abbinati.size());
                    lista_vestiti.addAll(abbinati.get(i));
                }

                if(disp) {
                    Random r = new Random();
                    int i = r.nextInt(disponibil.size());
                    lista_vestiti.addAll(disponibil.get(i));
                }

                if(ripie) {
                    Random r = new Random();
                    int i = r.nextInt(ripiego.size());
                    lista_vestiti.addAll(ripiego.get(i));
                }
            }
        }

        return lista_vestiti;
    }

    public Boolean isEmailPresent(String email){
        open();

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, new String[]{DBHelper.KEY_EMAIL, DBHelper.KEY_PASSWORD}, DBHelper.KEY_EMAIL + "=?",
                new String[]{email},null,null,null,null);

        if(cursor.moveToFirst()) {
            String email2 = cursor.getString(0);
            if(email.equals(email2))
                return true;
            else
                return false;
        }
        else return false;
    }

    public String getPassword(String email){
        open();

        Cursor cursor = database.query(DBHelper.TABLE_CONTACTS, new String[]{DBHelper.KEY_PASSWORD}, DBHelper.KEY_EMAIL + "=?",
                new String[]{email},null,null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            String pw = cursor.getString(0);
            return pw;
        }
        return null;
    }
}
