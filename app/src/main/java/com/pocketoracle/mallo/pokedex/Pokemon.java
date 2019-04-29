package com.pocketoracle.mallo.pokedex;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by mallo on 5/20/2016.
 * Modified 5/24/16
 */
public class Pokemon
{
    private int number;
    private String name;
    private String species;
    private String mainType;
    private String subType;
    private String height;
    private String weight;
    private String about;
    private String resourceId;

    public Pokemon(int number, String name, String species, String mainType, String subType, String height, String weight, String about,
                    String resourceId) {
        this.number = number;
        this.name = name;
        this.mainType = mainType;
        this.subType = subType;
        this.height = height;
        this.weight = weight;
        this.about = about;
        this.resourceId = resourceId;
    }

    private void setNumber(int number){this.number = number;}

    public int getNumber(){return number; }

    private void setName(String name) {this.name = name; }

    public String getName() {
        return name;
    }

    private void setSpecies(String species) {this.species = species;}

    public String getSpecies(){return species;}

    private void setMainType(String mainType){this.mainType = mainType;}

    public String getMainType() {
        return mainType;
    }

    private void setSubType(String subType){this.subType = subType;}

    public String getSubType() {
        return subType;
    }

    private void setHeight(String height){this.height = height;}

    public String getHeight(){return height;}

    private void setWeight(String weight){this.weight = weight;}

    public String getWeight(){return weight;}

    private void setAbout (String about){this.about = about;}

    public String getAbout() {
        return about;
    }

    public String getResourceId() {
        return resourceId;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

