package com.pocketoracle.mallo.pokedex;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PokemonDetailsActivity extends Activity {
    public static final String EXTRA_NAME = "extra_name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_details);

        if(savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(new MenuFragment(), "menu_frag")
                    .commit();

            String name = this.getIntent().getStringExtra(EXTRA_NAME);

            setTitle(name);

            PokemonDetailsFragment pokemonDetailsFragment =
                    PokemonDetailsFragment.newInstance(name);
            this.getFragmentManager().beginTransaction()
                    .add(R.id.PokemonDetailsFragHook, pokemonDetailsFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }
}