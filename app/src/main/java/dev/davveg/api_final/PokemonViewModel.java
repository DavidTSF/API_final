package dev.davveg.api_final;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import dev.davveg.api_final.models.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonViewModel extends AndroidViewModel {

    //MutableLiveData<PokeApiService.Respuesta> respuestaMutableLiveData = new MutableLiveData<>();

    MutableLiveData<PokemonList> pokemonLista = new MutableLiveData<>();

    Retrofit retrofit;
    PokeApiService service;

    public PokemonViewModel(@NonNull Application application) {
        super(application);
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(PokeApiService.class);

    }

    public void pokemonById(int id) {
        Call<Pokemon> pokeCall =
                service.getPokemonById(String.valueOf(id));
        pokeCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                Pokemon foundPoke = response.body();
                if (foundPoke != null) {
                    Log.d("POKEMON NAME", foundPoke.getName());
                    Log.d("POKEMON HEIGHT", foundPoke.getHeight());
                    Log.d("POKEMON WEIGHT", foundPoke.getWeight());
                }
            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
            }
        });
    }

    public void getPokemonList() {
        Call<PokemonList> pokeCall = service.getPokemonList(20, 0);
        pokeCall.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {

                response.body().getResults().forEach(
                        pokemon -> {
                            Log.d("DEBUG name", pokemon.getName());
                            Log.d("DEBUG weight", pokemon.getWeight());
                            Log.d("DEBUG height", pokemon.getHeight());

                        }
                );

                pokemonLista.postValue(response.body());
            }
            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
            }
        });
    }


    public LiveData<PokemonList> pokemonLista() {
        return this.pokemonLista;
    }
}
