package com.gmail.sacchin.pockemonbattletools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.vvakame.util.jsonpullparser.JsonFormatException;

import com.gmail.sacchin.pockemonbattletools.entity.Party;
import com.gmail.sacchin.pockemonbattletools.entity.Pockemon;
import com.gmail.sacchin.pockemonbattletools.http.PGLGetter;
import com.gmail.sacchin.pockemonbattletools.insert.IndividualPockemonInsertHandler;
import com.gmail.sacchin.pockemonbattletools.insert.ItemInsertHandler;
import com.gmail.sacchin.pockemonbattletools.insert.MegaPockemonInsertHandler;
import com.gmail.sacchin.pockemonbattletools.insert.PartyInsertHandler;
import com.gmail.sacchin.pockemonbattletools.insert.PockemonInsertHandler;
import com.gmail.sacchin.pockemonbattletools.insert.SkillInsertHandler;
import com.gmail.sacchin.pockemonbattletools.listener.OnClickCreateNewPartyButton;
import com.gmail.sacchin.pockemonbattletools.listener.OnClickFromList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends Activity {
	private LayoutParams LP = new LayoutParams(
			LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	
	private ScrollView scrollView;
	public LinearLayout partyLayout = null;
	public Party party = null;
	private PartyDatabaseHelper databaseHelper;

	protected SharedPreferences serviceStatePreferences;

	protected ExecutorService executorService = Executors.newCachedThreadPool();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		serviceStatePreferences = getSharedPreferences("pockemon", MODE_PRIVATE);
		databaseHelper = new PartyDatabaseHelper(this);
		if(serviceStatePreferences.getBoolean("isFirst", true)){
			executorService.execute(
					new PockemonInsertHandler(databaseHelper, this));
			executorService.execute(
					new ItemInsertHandler(databaseHelper, this));
			executorService.execute(
					new SkillInsertHandler(databaseHelper, this));
			executorService.execute(
					new PartyInsertHandler(databaseHelper, null, true, this));
			executorService.execute(
					new IndividualPockemonInsertHandler(databaseHelper, this));
			executorService.execute(
					new MegaPockemonInsertHandler(databaseHelper, this));
			Editor editor = serviceStatePreferences.edit();
			editor.putBoolean("isFirst", false);
			editor.commit();
		}else{
			Log.e("NotFirstTime", "don't create table!");
		}
		
		setContentView(R.layout.activity_main);
		partyLayout = (LinearLayout) findViewById(R.id.party);
		scrollView = (ScrollView)findViewById(R.id.scrollView);
		party = new Party();
		
		Button createNewParty = (Button) findViewById(R.id.create);
		createNewParty.setOnClickListener(new OnClickCreateNewPartyButton(this, party, databaseHelper, executorService));
		Button showParty = (Button) findViewById(R.id.show);
		showParty.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, PartListActivity.class);
				startActivityForResult(intent, LIST_ACTIVITY_CODE);
			}
		});
				
	}

	@Override
	public void onResume() {
		super.onResume();
		if(0 < scrollView.getChildCount()){
			return;
		}
			
		LinearLayout all = new LinearLayout(this);
		all.setLayoutParams(LP);
		all.setOrientation(LinearLayout.VERTICAL);
		all.setGravity(Gravity.CENTER);
		scrollView.addView(all);

		try {
			HashMap<String, Integer> countMap = databaseHelper.selectIndividualPockemonCount();
			databaseHelper.updatePockemonData(countMap);
			
			ArrayList<Pockemon> list = databaseHelper.selectAllPockemon();
			int count = 0;
			for(;count < list.size();){
				LinearLayout block = new LinearLayout(this);
				block.setLayoutParams(LP);
				block.setGravity(Gravity.CENTER);
				for(int i = 0 ; i < 4 ; i++){
					FrameLayout d = createFrameLayout(list.get(count), countMap);
					block.addView(d);
					count++;
					if(count > list.size() - 1){
						break;
					}
				}
				all.addView(block);
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public FrameLayout createFrameLayout(Pockemon p, HashMap<String, Integer> countMap){
		Resources r = getResources();
		FrameLayout fl = new FrameLayout(this);
		ImageView localView = new ImageView(this);
		Bitmap temp = BitmapFactory.decodeResource(r, p.getResouceId());
		Matrix matrix = new Matrix();
		matrix.postScale(150f / (float)temp.getWidth(), 150f / (float)temp.getHeight());
		temp = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(),temp.getHeight(), matrix, true);
		localView.setImageBitmap(temp);
		fl.setOnClickListener(new OnClickFromList(this, p));
		fl.addView(localView);
		TextView tv = new TextView(this);
		
		Integer c = countMap.get(String.valueOf(p.getRowId()));
		if(c != null){
			tv.setText(String.valueOf(c));
		}else{
			tv.setText("0");
		}
		fl.addView(tv);
		return fl;
	}

	private static final int TOOL_ACTIVITY_CODE = 0;
	private static final int LIST_ACTIVITY_CODE = 1;
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == TOOL_ACTIVITY_CODE) {
			partyLayout.removeAllViews();
			party.clear();
		}else if (requestCode == LIST_ACTIVITY_CODE) {
			partyLayout.removeAllViews();
			party.clear();
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	public void startToolActivity(){
		Intent intent = new Intent(MainActivity.this, ToolActivity.class);
		startActivityForResult(intent, TOOL_ACTIVITY_CODE);
	}
	
	public void makeToast(String message){
		Log.v("makeToast", message);
	}
}
