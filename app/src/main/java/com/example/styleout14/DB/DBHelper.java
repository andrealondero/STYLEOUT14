package com.example.styleout14.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "database";

    public static final String TABLE_CONTACTS = "contacts";
    public static final String TABLE_VESTITI = "vestito";
    public static final String TABLE_OUTFIT = "outfit";
    public static final String TABLE_OUTFIT_FATTI = "outfitfatto";
    public static final String TABLE_OUTFITFATTO_VESTITO = "outfitfatto_vestito";
    public static final String TABLE_TIPOOUTFIT = "tipooutfit";
    public static final String TABLE_TIPOOUTFIT_TIPOOUTFIT = "tipooutfit_tipooutfit";
    public static final String TABLE_TIPOVESTITO = "tipovestito";
    public static final String TABLE_TIPOVESTITO_TIPOOUTFIT ="tipovestito_tipooutfit";

    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "name";

    private static final String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
            + KEY_EMAIL + " TEXT PRIMARY KEY," + KEY_PASSWORD + " TEXT" + ")";

    private static final String CREATE_VESTITI_TABLE = "CREATE TABLE " + TABLE_VESTITI + "(" +
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
            "COLORE TEXT," +
            "COLORE_CODE TEXT," +
            "DISPONIBILE INTEGER," +
            "NOME TEXT," +
            "TESSUTO TEXT," +
            "TIPOVESTITO_ID INTEGER," +
            "STYLE TEXT," +
            "PIC_TAG INTEGER" +
            ")";

    public static final String CREATE_OUTFIT_TABLE = "CREATE TABLE outfit(" +
            "ID INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
            "FERIALE INTEGER," +
            "NOME TEXT," +
            "TEMPERATURA INTEGER," +
            "TEMPERATURAMASSIMA INTEGER," +
            "OUTFITPRINCIPALE_ID INTEGER," +
            "TIPOOUTFIT_ID INTEGER" +
            ")";

    public static final String CREATE_OUTFITFATTI_TABLE = "CREATE TABLE `outfitfatto` (" +
            " `ID` INTEGER NOT NULL PRIMARY KEY," +
            " `OUTFITCOLLEGATO_ID` INTEGER )";

    public static final String CREATE_OUTFITFATTO_VESTITO = "CREATE TABLE `outfitfatto_vestito` (" +
            "`vestitiFatti_ID` INTEGER," +
            "`outfitCollegati_ID` INTEGER)";

    public static final String CREATE_TIPOOUTFIT = "CREATE TABLE `tipooutfit` (" +
            " `ID` INTEGER NOT NULL," +
            " `NOME` TEXT, PRIMARY KEY(`ID`) )";

    public static final String CREATE_TIPOOUTFIT_TIPOOUTFIT = "CREATE TABLE `tipooutfit_tipooutfit` (" +
            " `tipoOutfitPrincipale_ID` INTEGER NOT NULL," +
            " `tipiOutfit_ID` INTEGER NOT NULL," +
            " PRIMARY KEY(`tipoOutfitPrincipale_ID`,`tipiOutfit_ID`) )";

    public static final String CREATE_TIPOVESTITO = "CREATE TABLE `tipovestito` (" +
            " `ID` INTEGER NOT NULL, `NOME` TEXT," +
            " PRIMARY KEY(`ID`) )";

    public static final String CREATE_TIPOVESTITO_TIPOOUTFIT = "CREATE TABLE `tipovestito_tipooutfit` (" +
            " `tipiOutfit_ID` INTEGER NOT NULL," +
            " `tipiVestito_ID` INTEGER NOT NULL, " +
            " PRIMARY KEY(`tipiOutfit_ID`,`tipiVestito_ID`) )";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_VESTITI_TABLE);
        db.execSQL(CREATE_OUTFIT_TABLE);
        db.execSQL(CREATE_OUTFITFATTI_TABLE);
        db.execSQL(CREATE_OUTFITFATTO_VESTITO);
        db.execSQL(CREATE_TIPOOUTFIT);
        db.execSQL(CREATE_TIPOOUTFIT_TIPOOUTFIT);
        db.execSQL(CREATE_TIPOVESTITO);
        db.execSQL(CREATE_TIPOVESTITO_TIPOOUTFIT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VESTITI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTFIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTFIT_FATTI);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_OUTFITFATTO_VESTITO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOOUTFIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOOUTFIT_TIPOOUTFIT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOVESTITO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TIPOVESTITO_TIPOOUTFIT);

        onCreate(db);
    }
}
