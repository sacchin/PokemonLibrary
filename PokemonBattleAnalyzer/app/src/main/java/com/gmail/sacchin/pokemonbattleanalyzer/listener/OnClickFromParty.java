package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.MainFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;

import android.view.View;
import android.view.View.OnClickListener;

public class OnClickFromParty implements OnClickListener{
	private IndividualPBAPokemon pokemon = null;
	private MainFragment fragment = null;
	
	public OnClickFromParty(MainFragment fragment, IndividualPBAPokemon pokemon){
		this.pokemon = pokemon;
		this.fragment = fragment;
	}

	@Override
	public void onClick(View v) {
        fragment.removePokemonFromList(pokemon);
	}

}
