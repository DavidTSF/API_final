package dev.davveg.api_final;

import java.util.ArrayList;

import dev.davveg.api_final.models.Pokemon;

public class PokemonList {
    private ArrayList<Pokemon> results;

    public ArrayList<Pokemon> getResults() {
        return results;
    }
    public void setResults(ArrayList<Pokemon> results) {
        this.results = results;
    }
}
