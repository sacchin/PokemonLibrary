package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.fragment.ToolFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class OnClickIndividualPokemon implements OnClickListener{
	private int index = 0;
	private ToolFragment fragment = null;
	private ImageView from = null;

	public OnClickIndividualPokemon(ToolFragment fragment, int index, ImageView from){
		this.index = index;
		this.fragment = fragment;
		this.from = from;
	}

	@Override
	public void onClick(View v) {
		IndividualPBAPokemon p = fragment.getIndividualPBAPokemon(index);
		fragment.startDetailActivity(p, from);
//        fragment.setMainView(p);
//        fragment.setIndex(index);
//        fragment.setTrend();
	}
}
