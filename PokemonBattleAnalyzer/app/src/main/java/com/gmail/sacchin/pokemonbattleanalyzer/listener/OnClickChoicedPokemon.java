package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import android.view.View;
import android.view.View.OnClickListener;

import com.gmail.sacchin.pokemonbattleanalyzer.fragment.ToolFragment;

public class OnClickChoicedPokemon implements OnClickListener{
	private int index = 0;
	private ToolFragment fragment = null;

	public OnClickChoicedPokemon(ToolFragment fragment, int index){
		this.index = index;
		this.fragment = fragment;
	}

	@Override
	public void onClick(View v) {
		fragment.setAlert(index);
	}
}
