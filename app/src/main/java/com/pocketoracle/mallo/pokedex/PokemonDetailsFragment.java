package com.pocketoracle.mallo.pokedex;


import android.app.Fragment;
import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class PokemonDetailsFragment extends Fragment {

    public static final String EXTRA_NAME = "extra_name";
    private String name;

    public static PokemonDetailsFragment newInstance(String name)
    {
        PokemonDetailsFragment pokemonDetailsFragment = new PokemonDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PokemonDetailsFragment.EXTRA_NAME, name);
        pokemonDetailsFragment.setArguments(bundle);
        return pokemonDetailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);

        // New
        Bundle bundle = this.getArguments();
        if(bundle != null)
        {
            name = bundle.getString(EXTRA_NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pokemon_details, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        View v = this.getView();

        DBAccessHelper dbAccess = DBAccessHelper.getInstance(this.getActivity());
        dbAccess.open();

        Pokemon pokemon = dbAccess.getPokemonByName(name);

        TextView tvName = (TextView) v.findViewById(R.id.tvName);
        TextView tvSpecies = (TextView) v.findViewById(R.id.tvSpecies);
        ImageView ivPokemon = (ImageView) v.findViewById(R.id.ivPokemon);
        TextView tvMainType= (TextView) v.findViewById(R.id.tvMainType);
        TextView tvSubType = (TextView) v.findViewById(R.id.tvSubType);
        TextView tvHeight = (TextView) v.findViewById(R.id.tvHeightNum);
        TextView tvWeigth = (TextView) v.findViewById(R.id.tvWeightNum);
        TextView tvAbout = (TextView) v.findViewById(R.id.tvAbout);

        String number = String.format("# %03d", pokemon.getNumber());
        String name = number + " - " + pokemon.getName();
        tvName.setText(name);
        tvSpecies.setText(pokemon.getSpecies());
        String resourceId = pokemon.getResourceId();
        int imageResource = getResources().
                getIdentifier(resourceId, "drawable", getActivity().getPackageName());
        ivPokemon.setImageResource(imageResource);
        tvMainType.setText(pokemon.getMainType());
        setMyTextColor(tvMainType);
        if(!pokemon.getSubType().equals("None"))
        {
            tvSubType.setText(pokemon.getSubType());
            setMyTextColor(tvSubType);
        }
        tvHeight.setText(pokemon.getHeight());
        tvWeigth.setText(pokemon.getWeight());
        tvAbout.setText(pokemon.getAbout());

        int soundResouceId = getResources().getIdentifier(resourceId, "raw", getActivity().getPackageName());
        if(soundResouceId != 0){
            final MediaPlayer pokeCry = MediaPlayer.create(this.getActivity(), soundResouceId);
            ivPokemon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pokeCry.start();
                }
            });
        }
    }

    public void setName (String name)
    {
        this.name = name;
    }
    void setMyTextColor(TextView tv)
    {
        String type = tv.getText().toString();
        switch(type)
        {
            case "Normal": tv.setTextColor(Color.argb(255, 168, 168, 120));
                break;
            case "Fighting": tv.setTextColor(Color.argb(255, 192, 48, 40));
                break;
            case "Flying": tv.setTextColor(Color.argb(255, 168, 144, 240));
                break;
            case "Poison": tv.setTextColor(Color.argb(255, 160, 64, 160));
                break;
            case "Ground": tv.setTextColor(Color.argb(255, 224, 192, 104));
                break;
            case "Rock": tv.setTextColor(Color.argb(255, 184, 160, 56));
                break;
            case "Bug": tv.setTextColor(Color.argb(255, 168, 184, 32));
                break;
            case "Ghost": tv.setTextColor(Color.argb(255, 112, 88, 152));
                break;
            case "Steel": tv.setTextColor(Color.argb(255, 184, 184, 208));
                break;
            case "Fire": tv.setTextColor(Color.argb(255, 240, 128, 48));
                break;
            case "Water": tv.setTextColor(Color.argb(255, 104, 144, 240));
                break;
            case "Grass": tv.setTextColor(Color.argb(255, 120, 200, 80));
                break;
            case "Electric": tv.setTextColor(Color.argb(255, 248, 207, 48));
                break;
            case "Physic": tv.setTextColor(Color.argb(255, 248, 88, 136));
                break;
            case "Ice": tv.setTextColor(Color.argb(255, 152, 216, 216));
                break;
            case "Dragon": tv.setTextColor(Color.argb(255, 112, 56, 248));
                break;
            case "Dark": tv.setTextColor(Color.argb(255, 112, 88, 72));
                break;
            case "Fairy": tv.setTextColor(Color.argb(255, 238, 153, 172));
                break;
        }
    }
}
