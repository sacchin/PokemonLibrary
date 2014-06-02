package com.gmail.sacchin.pockemonbattletools.listener;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;

import com.gmail.sacchin.pockemonbattletools.MainActivity;
import com.gmail.sacchin.pockemonbattletools.PartyDatabaseHelper;
import com.gmail.sacchin.pockemonbattletools.entity.Party;
import com.gmail.sacchin.pockemonbattletools.insert.PartyInsertHandler;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class OnClickCreateNewPartyButton implements OnClickListener{
	private Party party = null;
	private MainActivity context = null;
	private PartyDatabaseHelper databaseHelper = null;
	protected ExecutorService executorService = null;
	
	public OnClickCreateNewPartyButton(Context activity, Party party, PartyDatabaseHelper databaseHelper, ExecutorService executorService){
		this.party = party;
		this.context = (MainActivity) activity;
		this.databaseHelper = databaseHelper;
		this.executorService = executorService;
	}

	@Override
	public void onClick(View v) {
		if(party.getMember() == null || party.getMember().size() < 1){
			Toast.makeText(context, "ポケモンが選択されていません。", Toast.LENGTH_SHORT).show();
			return;
		}
		party.setTime(new Timestamp(System.currentTimeMillis()));
		executorService.execute(
				new PartyInsertHandler(databaseHelper, party, false, context));
		context.startToolActivity();
	}

}
