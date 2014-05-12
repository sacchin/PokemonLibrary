package com.gmail.sacchin.pockemonbattletools.listener;

import java.sql.Timestamp;

import com.gmail.sacchin.pockemonbattletools.MainActivity;
import com.gmail.sacchin.pockemonbattletools.entity.IndividualPockemon;
import com.gmail.sacchin.pockemonbattletools.entity.Pockemon;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class OnClickFromList implements OnClickListener{
	private Pockemon pockemon = null;
	private MainActivity context = null;

	public OnClickFromList(Context activity, Pockemon pockemon){
		this.pockemon = pockemon;
		this.context = (MainActivity) activity;
	}

	@Override
	public void onClick(View v) {
		ImageView localView = new ImageView(context);
		Bitmap temp = BitmapFactory.decodeResource(context.getResources(), pockemon.getResouceId());

		if(pockemon == null){
			Toast.makeText(context, "エラーが発生しました。", Toast.LENGTH_SHORT).show();
			return;
		}
		IndividualPockemon ip = new IndividualPockemon(pockemon, 0, new Timestamp(System.currentTimeMillis()), 
				"", "", "", "", "", "");
		int index = context.party.setMember(ip);
		if(index == -1){
			Toast.makeText(context, "6体を選択済みです。", Toast.LENGTH_SHORT).show();
		}else{
			Matrix matrix = new Matrix();
			matrix.postScale(90f / (float)temp.getWidth(), 90f / (float)temp.getHeight());
			temp = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(),temp.getHeight(), matrix, true);
			localView.setImageBitmap(temp);
			localView.setOnClickListener(new OnClickFromParty(context, ip));
			context.partyLayout.addView(localView);
		}

	}
}
