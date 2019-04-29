package com.pocketoracle.mallo.pokedex;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Activity
        implements TypesListFragment.TypesListFragmentListener,
        PokemonListFragment.PokemonListFragmentListener
{
    private boolean isPhone;
    private DBAccessHelper dbAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAccess = DBAccessHelper.getInstance(this);
        dbAccess.open();

        if(savedInstanceState == null)
        {
            getFragmentManager().beginTransaction()
                    .add(new MenuFragment(), "menu_frag")
                    .commit();
        }

        isPhone = findViewById(R.id.rootLayoutDefault)!=null;
        if(savedInstanceState == null && isPhone == false)
        {
            onTypeClicked(dbAccess.getTypes().get(1));
            onPokemonClicked("Eevee");
        }
    }

    @Override
    public void onTypeClicked(String type)
    {
        // Old stuff
        //PokemonListFragment pokemonListFragment = new PokemonListFragment();
        //pokemonListFragment.setType(type);

        // New stuff
        if(isPhone)
        {
            Intent intent = new Intent(this, PokemonListActivity.class);
            intent.putExtra(PokemonListActivity.EXTRA_TYPE, type);
            startActivity(intent);
        }
        else
        {
            PokemonListFragment pokemonListFragment = PokemonListFragment.newInstance(type);

            FragmentManager fragmentManager = this.getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.pokemonFragHook, pokemonListFragment)
                    .commit();
            // Old Line
            //onPokemonClicked(Pokemon.getPokemonByMainType(type)[0].toString());
            onPokemonClicked(dbAccess.getPokemonByType(type).toString());
        }
    }

    @Override
    public void onPokemonClicked(String name) {
        // Old stuff
        //PokemonDetailsFragment pokemonDetailsFragment = new PokemonDetailsFragment();
        //pokemonDetailsFragment.setName(name);

        // New stuff
        //Bundle bundle = new Bundle();
        //bundle.putString(PokemonDetailsFragment.EXTRA_NAME, name);
        //pokemonDetailsFragment.setArguments(bundle);
        PokemonDetailsFragment pokemonDetailsFragment = PokemonDetailsFragment.newInstance(name);

        FragmentManager fragmentManager = this.getFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.detailsFragHook, pokemonDetailsFragment)
                .commit();
    }
}