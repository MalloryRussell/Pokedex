package com.pocketoracle.mallo.pokedex;


import android.app.Activity;
import android.app.Fragment;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonListFragment extends ListFragment {
    public static final String EXTRA_TYPE = "extra_type";
    private PokemonListFragmentListener pokemonListFragmentListener;
    private String type;

    public interface PokemonListFragmentListener
    {
        public void onPokemonClicked(String name);
    }

    public static PokemonListFragment newInstance(String type)
    {
        PokemonListFragment pokemonListFragment = new PokemonListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_TYPE, type);
        pokemonListFragment.setArguments(bundle);
        return pokemonListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            type = bundle.getString(EXTRA_TYPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Old code
        // Pokemon[] pokemon = Pokemon.getPokemonByMainType(type);
        DBAccessHelper dbAccess = DBAccessHelper.getInstance(this.getActivity());
        dbAccess.open();

        List<String> pokemon = dbAccess.getPokemonByType(type);

        this.setListAdapter(new ArrayAdapter<String>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                pokemon));

        return super.onCreateView(inflater, container,
                savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try
        {
            pokemonListFragmentListener = (PokemonListFragmentListener)activity;
        }
        catch(ClassCastException e)
        {
            throw new ClassCastException(activity.getClass().getSimpleName() +
                    " MUST IMPLEMENT PokemonListFragmentListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String name = l.getItemAtPosition(position).toString();
        pokemonListFragmentListener.onPokemonClicked(name);
    }

    public void setType(String type) {
        this.type = type;
    }

}
