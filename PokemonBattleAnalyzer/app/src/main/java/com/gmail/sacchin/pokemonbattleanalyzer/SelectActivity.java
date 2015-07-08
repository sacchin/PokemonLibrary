package com.gmail.sacchin.pokemonbattleanalyzer;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;

import com.gmail.sacchin.pokemonbattleanalyzer.fragment.SelectFragment;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.ToolFragment;

public class SelectActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
		transaction.add(R.id.FragmentContainer, SelectFragment.newInstance(0));
		transaction.commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onStop() {
        super.onStop();
	}

	public void startToolActivity() {
		Intent intent = new Intent(SelectActivity.this, ToolActivity.class);
		startActivityForResult(intent, 1);
	}
}
