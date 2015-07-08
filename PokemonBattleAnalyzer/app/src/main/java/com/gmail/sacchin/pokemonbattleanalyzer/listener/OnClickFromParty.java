package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.interfaces.AddToListInterface;

import android.view.View;
import android.view.View.OnClickListener;

public class OnClickFromParty implements OnClickListener{
	private IndividualPBAPokemon pokemon = null;
	private AddToListInterface someFragment = null;
	
	public OnClickFromParty(AddToListInterface someFragment, IndividualPBAPokemon pokemon){
		this.pokemon = pokemon;
		this.someFragment = someFragment;
	}

	@Override
	public void onClick(View v) {
		someFragment.removePokemonFromList(pokemon);
	}

}
