package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.fragment.MainFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;

import android.view.View;
import android.view.View.OnClickListener;

public class OnClickFromList implements OnClickListener{
	private PBAPokemon pokemon = null;
	private MainFragment fragment = null;

	public OnClickFromList(MainFragment fragment, PBAPokemon pokemon){
		this.pokemon = pokemon;
		this.fragment = fragment;
	}

	@Override
	public void onClick(View v) {
        fragment.addPokemonToList(pokemon);
	}
}
