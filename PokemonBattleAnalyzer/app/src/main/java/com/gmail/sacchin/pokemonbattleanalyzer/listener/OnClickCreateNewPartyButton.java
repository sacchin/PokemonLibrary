package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.MainFragment;

import android.view.View;
import android.view.View.OnClickListener;

public class OnClickCreateNewPartyButton implements OnClickListener{
	private MainFragment fragment = null;

	public OnClickCreateNewPartyButton(MainFragment fragment){
		this.fragment = fragment;
	}

	@Override
	public void onClick(View v) {
        fragment.start();
	}
}
