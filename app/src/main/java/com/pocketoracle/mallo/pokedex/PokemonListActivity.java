package com.pocketoracle.mallo.pokedex;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class PokemonListActivity extends Activity
        implements PokemonListFragment.PokemonListFragmentListener{
    public static final String EXTRA_TYPE = "extra_type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        if(savedInstanceState == null) {

            getFragmentManager().beginTransaction()
                    .add(new MenuFragment(), "menu_frag")
                    .commit();

            String type = this.getIntent().getStringExtra(EXTRA_TYPE);

            setTitle(type);

            PokemonListFragment pokemonListFragment =
                    PokemonListFragment.newInstance(type);
            this.getFragmentManager().beginTransaction()
                    .add(R.id.pokemonListFragHook, pokemonListFragment)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .commit();
        }
    }

    @Override
    public void onPokemonClicked(String name) {
        Intent intent = new Intent(this, PokemonDetailsActivity.class);
        intent.putExtra(PokemonDetailsActivity.EXTRA_NAME, name);
        startActivity(intent);
    }
}
