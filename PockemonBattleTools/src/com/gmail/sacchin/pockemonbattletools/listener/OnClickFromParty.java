package com.gmail.sacchin.pockemonbattletools.listener;

import com.gmail.sacchin.pockemonbattletools.MainActivity;
import com.gmail.sacchin.pockemonbattletools.entity.IndividualPockemon;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickFromParty  implements OnClickListener{
	private IndividualPockemon pockemon = null;
	private MainActivity context = null;
	
	public OnClickFromParty(Context activity, IndividualPockemon pockemon){
		this.pockemon = pockemon;
		this.context = (MainActivity) activity;
	}

	@Override
	public void onClick(View v) {
		try {
			int index = context.party.removeMember(pockemon);
			if(-1 < index){
				context.partyLayout.removeViewAt(index + 1);
			}else{
				context.party.setMember(pockemon);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
