package com.gmail.sacchin.pokemonbattleanalyzer;

import java.util.ArrayList;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class ToolActivity extends Activity{

	private ArrayList<String> skills = null;
	private ArrayList<String> items = null;
	private Party party = null;
	private int index = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        FragmentManager manager = getFragmentManager();
        // FragmentTransaction を開始
        FragmentTransaction transaction = manager.beginTransaction();

        // FragmentContainer のレイアウトに、MyFragment を割当てる
        transaction.add(R.id.FragmentContainer, ToolFragment.newInstance(0));

        // 変更を確定して FragmentTransaction を終える
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
}
