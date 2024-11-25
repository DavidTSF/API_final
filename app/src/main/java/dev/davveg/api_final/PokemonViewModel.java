package dev.davveg.api_final;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import dev.davveg.api_final.models.Pokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokemonViewModel extends AndroidViewModel {

    //MutableLiveData<PokeApiService.Respuesta> respuestaMutableLiveData = new MutableLiveData<>();

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

    public void pokemonById(PokeApiService pokeService) {
        Call<Pokemon> pokeCall =
                pokeService.getPokemonById(Integer.toString((int) (Math.random() *
                        807 + 1)));
        pokeCall.enqueue(new Callback<Pokemon>() {
            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {

                if (foundPoke != null) {
                    Log.d("POKEMON NAME", foundPoke.getName());
                    Log.d("POKEMON HEIGHT", foundPoke.getHeight());
                    Log.d("POKEMON WEIGHT", foundPoke.getWeight());
                }
            }
            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
// TOAST THE FAILURE
                Toast.makeText(MainActivity.this, t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }





}
