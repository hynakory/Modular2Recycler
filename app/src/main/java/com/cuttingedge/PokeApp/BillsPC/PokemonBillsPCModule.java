package com.cuttingedge.PokeApp.BillsPC;

import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cuttingedge.PokeApp.Pokedex;
import com.cuttingedge.PokeApp.Pokemon;
import com.cuttingedge.PokeApp.PokemonModule;
import com.cuttingedge.PokeApp.R;
import com.cuttingedge.adapter2recycler.Adapter.ModularAdapter;

/**
 * Created by Robbe Sneyders
 *
 * Module to handle behaviour of Pokemon item in Bills PC context
 */
class PokemonBillsPCModule extends PokemonModule<PokemonModule.PokemonViewHolder, Pokemon> {

    /*************************
     * AdapterModule methods *
     *************************/

    PokemonBillsPCModule(ModularAdapter adapter) {
        super(adapter);
    }

    @Override
    public PokemonViewHolder onCreateViewHolder(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_tile, parent, false);
        return new PokemonViewHolder(itemView);
    }


    /*********************************
     * OnSwipeListenerModule methods *
     *********************************/


    @Override
    public String onSwiped(Pokemon pokemon, int swipeDir) {
        if (swipeDir == ItemTouchHelper.LEFT) {
            Pokedex.removePokemon(pokemon);
            return pokemon.name + " was set free";
        }
        else if (swipeDir == ItemTouchHelper.RIGHT) {
            Pokedex.getFromBill(pokemon);
            return pokemon.name + " was downloaded to backpack";
        }
        return null;
    }

    @Override
    public void onUndo(Pokemon pokemon, int swipeDir) {
        if (swipeDir == ItemTouchHelper.LEFT) {
            Pokedex.addToBillsPC(pokemon);
        }
        else if (swipeDir == ItemTouchHelper.RIGHT) {
            Pokedex.sendToBill(pokemon);
        }
    }

    @Override
    public int getDragDirs() {
        return ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
    }
}
