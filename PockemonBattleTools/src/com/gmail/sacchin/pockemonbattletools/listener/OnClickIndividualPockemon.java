package com.gmail.sacchin.pockemonbattletools.listener;

import com.gmail.sacchin.pockemonbattletools.ToolActivity;
import com.gmail.sacchin.pockemonbattletools.entity.IndividualPockemon;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickIndividualPockemon implements OnClickListener{
	private int index = 0;
	private ToolActivity context = null;

	public OnClickIndividualPockemon(Context activity, int index){
		this.index = index;
		this.context = (ToolActivity) activity;
	}

	@Override
	public void onClick(View v) {
//		context.applyToIndividualPockemon();
		IndividualPockemon p = context.getIndividualPockemon(index);
		context.setMainView(p);
		context.setIndex(index);
		context.setTrend();
	}
}
