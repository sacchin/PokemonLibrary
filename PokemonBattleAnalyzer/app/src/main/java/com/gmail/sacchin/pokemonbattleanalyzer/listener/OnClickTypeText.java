package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import android.view.View;
import android.view.View.OnClickListener;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.AffinityFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.ToolFragment;

import java.util.List;

public class OnClickTypeText implements OnClickListener{
	private List<PBAPokemon> oneType = null;
	private AffinityFragment fragment = null;

	public OnClickTypeText(AffinityFragment fragment, List<PBAPokemon> oneType){
		this.oneType = oneType;
		this.fragment = fragment;
	}

	@Override
	public void onClick(View v) {
        fragment.setTypeView(oneType);
	}
}
