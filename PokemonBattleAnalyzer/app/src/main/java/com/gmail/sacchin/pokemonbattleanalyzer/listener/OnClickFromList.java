package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.AddToListInterface;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;

import android.view.View;
import android.view.View.OnClickListener;

public class OnClickFromList implements OnClickListener{
	private PBAPokemon pokemon = null;
	private AddToListInterface someFragment = null;

	public OnClickFromList(AddToListInterface someFragment, PBAPokemon pokemon){
		this.pokemon = pokemon;
		this.someFragment = someFragment;
	}

	@Override
	public void onClick(View v) {
		someFragment.addPokemonToList(pokemon);
	}
}
