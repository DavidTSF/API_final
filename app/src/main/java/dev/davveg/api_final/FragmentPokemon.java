package dev.davveg.api_final;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.List;

import dev.davveg.api_final.databinding.FragmentPokemonBinding;
import dev.davveg.api_final.databinding.ViewholderPokemonBinding;
import dev.davveg.api_final.models.Pokemon;


public class FragmentPokemon extends Fragment {
    FragmentPokemonBinding binding;
    PokemonViewModel pokemonViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return (binding = FragmentPokemonBinding.inflate(inflater, container, false)).getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pokemonViewModel = new ViewModelProvider(requireActivity()).get(PokemonViewModel.class);
        pokemonViewModel.getPokemonList();
        pokemonViewModel.pokemonById(5);

        PokemonsAdapter pa = new PokemonsAdapter();
        binding.recyclerviewContenidos.setAdapter(pa);




        pokemonViewModel.pokemonLista().observe(getViewLifecycleOwner(), new Observer<PokemonList>() {
            @Override
            public void onChanged(PokemonList elementos) {
                pa.establecerLista(elementos.getResults());
            }
        });



    }


    class PokemonsAdapter extends RecyclerView.Adapter<PokemonViewHolder> {

        List<Pokemon> elementos;

        @NonNull
        @Override
        public PokemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PokemonViewHolder(ViewholderPokemonBinding.inflate(getLayoutInflater(), parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull PokemonViewHolder holder, int position) {
            Pokemon elemento = elementos.get(position);

            holder.binding.textName.setText(elemento.getName());
            holder.binding.textHeight.setText(elemento.getHeight());
            holder.binding.textWeight.setText(elemento.getWeight());

        }

        @Override
        public int getItemCount() {
            return elementos != null ? elementos.size() : 0;
        }

        public void establecerLista(List<Pokemon> elementos){
            this.elementos = elementos;
            notifyDataSetChanged();
        }

        public Pokemon obtenerElemento(int posicion){
            return elementos.get(posicion);
        }
    }

    static class PokemonViewHolder extends RecyclerView.ViewHolder {
        private final ViewholderPokemonBinding binding;

        public PokemonViewHolder(ViewholderPokemonBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }


}