package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import android.view.View;
import android.view.View.OnClickListener;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.AffinityFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.ToolFragment;

import java.util.List;

public class OnClickFromAffinityList implements OnClickListener{
	private PBAPokemon pokemon = null;
	private AffinityFragment fragment = null;

	public OnClickFromAffinityList(AffinityFragment fragment, PBAPokemon pokemon){
		this.pokemon = pokemon;
		this.fragment = fragment;
	}

	@Override
	public void onClick(View v) {
        fragment.setTypeView(pokemon);
	}
}
