package com.pocketoracle.mallo.pokedex;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class PokemonActivity extends ListActivity {
    public static final String EXTRA_TYPE = "extra_type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DBAccessHelper dbAccess = DBAccessHelper.getInstance(this);
        dbAccess.open();

        String type = this.getIntent().getStringExtra(EXTRA_TYPE);
        setTitle(type + " Pokémon");
        List<String> pokemon = dbAccess.getPokemonByType(type);
        ArrayAdapter<String>pokemonArrayAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pokemon);
        this.getListView().setAdapter(pokemonArrayAdapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, PokemonDetailsActivity.class);
        String name = l.getItemAtPosition(position).toString();
        intent.putExtra(PokemonDetailsActivity.EXTRA_NAME, name);
        startActivity(intent);
    }
}

