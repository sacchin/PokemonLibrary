package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.ToolFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickIndividualPokemon implements OnClickListener{
	private int index = 0;
	private ToolFragment fragment = null;

	public OnClickIndividualPokemon(ToolFragment fragment, int index){
		this.index = index;
		this.fragment = fragment;
	}

	@Override
	public void onClick(View v) {
		IndividualPBAPokemon p = fragment.getIndividualPBAPokemon(index);
        fragment.setMainView(p);
        fragment.setIndex(index);
        fragment.setTrend();
	}
}
