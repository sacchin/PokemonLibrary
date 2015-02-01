package com.gmail.sacchin.pokemonbattleanalyzer.listener;

import com.gmail.sacchin.pokemonbattleanalyzer.fragment.MainFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickFromList implements OnClickListener, GestureDetector.OnGestureListener{
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


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        fragment.showAffinity();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
