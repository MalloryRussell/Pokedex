package com.pocketoracle.mallo.pokedex;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

public class DBAccessHelper {
    private DBOpenHelper dbOpenHelper;
    private SQLiteDatabase database;
    private static DBAccessHelper instance;

    private DBAccessHelper(Context context){

        this.dbOpenHelper = new DBOpenHelper(context);
    }

    public static DBAccessHelper getInstance(Context context){
        if(instance == null){
            instance = new DBAccessHelper(context);
        }
        return instance;
    }

    public void open(){
        this.database = dbOpenHelper.getReadableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public List<String> getTypes(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * from type ORDER BY name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<String> getPokemonByType(String type){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT name FROM pokemon WHERE mainType = '" + type + "' OR subType = '" + type + "' ORDER BY name", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public Pokemon getPokemonByName(String name){
        int number;
        String species;
        String mainType;
        String subType;
        String height;
        String weight;
        String about;
        String resourceId;

        Cursor cursor = database.rawQuery("SELECT * FROM pokemon WHERE name = '" + name + "'", null);
        cursor.moveToFirst();
        if(cursor != null){
            number = cursor.getInt(cursor.getColumnIndex("number"));
            species = cursor.getString(cursor.getColumnIndex("species"));
            mainType = cursor.getString(cursor.getColumnIndex("mainType"));
            subType = cursor.getString(cursor.getColumnIndex("subType"));
            height = cursor.getString(cursor.getColumnIndex("height"));
            weight = cursor.getString(cursor.getColumnIndex("weight"));
            about = cursor.getString(cursor.getColumnIndex("about"));
            resourceId = cursor.getString(cursor.getColumnIndex("resourceId"));

            Pokemon pokemon = new Pokemon(number, name, species, mainType, subType, height, weight, about, resourceId);
            cursor.close();
            return pokemon;
        } else {
            return null;
        }
    }
}
