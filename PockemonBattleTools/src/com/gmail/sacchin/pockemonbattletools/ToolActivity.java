package com.gmail.sacchin.pockemonbattletools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.gmail.sacchin.pockemonbattletools.entity.IndividualPockemon;
import com.gmail.sacchin.pockemonbattletools.entity.Party;
import com.gmail.sacchin.pockemonbattletools.entity.Pockemon;
import com.gmail.sacchin.pockemonbattletools.entity.pgl.RankingPokemonTrend;
import com.gmail.sacchin.pockemonbattletools.http.PGLGetter;
import com.gmail.sacchin.pockemonbattletools.listener.OnClickIndividualPockemon;
import com.gmail.sacchin.pokemonlibrary.entity.*;

import android.app.Activity;
import android.content.Context;
import android.database.SQLException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ToolActivity extends Activity{

	private PartyDatabaseHelper databaseHelper = null;
	private ArrayList<String> skills = null;
	private ArrayList<String> items = null;
	private Party party = null;
	private int index = 0;
	private LinearLayout mainView = null;
	private TableLayout tl = null;

	protected ExecutorService executorService = Executors.newCachedThreadPool();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tool_main);
		databaseHelper = new PartyDatabaseHelper(this);
		mainView = (LinearLayout)findViewById(R.id.mainview);
		tl = (TableLayout) findViewById(R.id.status);
		Button save = (Button) findViewById(R.id.save);
		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				applyToIndividualPockemon();
				updateIndividualPockemon();
				finish();
			}
		});
		final Handler handler = new Handler();
		try {
			party = databaseHelper.selectNewestParty();
			for(int i = 0 ; i < party.getMember().size() ; i++){
				IndividualPockemon p = party.getMember().get(i);
				
				if(p.getNo().contains("-")){
					executorService.execute(
							new PGLGetter(p.getNo(), i, this, handler));
				}else{
					try {
						executorService.execute(
								new PGLGetter(String.valueOf(Integer.parseInt(p.getNo())) + "-0", i, this, handler));
					} catch (NumberFormatException e) {
						e.printStackTrace();
					}
				}
				
//				p.setItemStatistics(databaseHelper.selectIndividualPockemonItemCount(p.getRowId()));
//				p.setSkillStatistics(databaseHelper.selectIndividualPockemonSkillCount(p.getRowId()));
			}
			skills = databaseHelper.selectAllSkill();
			items = databaseHelper.selectAllItem();
			setAutoComplete(skills, items);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		LinearLayout partyLayout = (LinearLayout)findViewById(R.id.party);
		if(party != null){
			for (int i = 0; i < party.getMember().size(); i++) {
				IndividualPockemon p = party.getMember().get(i);
				Bitmap image = createImage(p);
				ImageView imageView = new ImageView(this);
				imageView.setImageBitmap(image);
				imageView.setOnClickListener(new OnClickIndividualPockemon(this, i));
				partyLayout.addView(imageView);
				if(i == index){
					setMainView(p);
				}
			}
		}
	}
	
	public IndividualPockemon getIndividualPockemon(int index){
		return party.getMember().get(index);
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public Bitmap createImage(Pockemon p){
		Bitmap image = BitmapFactory.decodeResource(getResources(), p.getResouceId());
		if(image == null){
			Log.e("createImage", p.getJname() + " - " + p.getResouceId());
			return null;
		}
		Matrix matrix = new Matrix();
		matrix.postScale(95f / (float)image.getWidth(), 95f / (float)image.getHeight());
		image = Bitmap.createBitmap(image, 0, 0, image.getWidth(),image.getHeight(), matrix, true);	
		return image;
	}
	
	public void applyToIndividualPockemon(){
		IndividualPockemon p = party.getMember().get(index);
		String skill1 = ((AutoCompleteTextView)findViewById(R.id.member1_skill1)).getText().toString();
		p.setSkillNo1(skill1);
		String skill2 = ((AutoCompleteTextView)findViewById(R.id.member1_skill2)).getText().toString();
		p.setSkillNo2(skill2);
		String skill3 = ((AutoCompleteTextView)findViewById(R.id.member1_skill3)).getText().toString();
		p.setSkillNo3(skill3);
		String skill4 = ((AutoCompleteTextView)findViewById(R.id.member1_skill4)).getText().toString();
		p.setSkillNo4(skill4);
		String item1 = ((AutoCompleteTextView)findViewById(R.id.member1_item1)).getText().toString();
		p.setItem(item1);
		party.setUserName(((TextView)findViewById(R.id.user)).getText().toString());
		party.setMemo(((TextView)findViewById(R.id.memo)).getText().toString());
	}
	
	public void updateIndividualPockemon(){
		try {
			databaseHelper.updateIndividualPockemonData(party);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setAutoComplete(ArrayList<String> skills, ArrayList<String> items) {
		ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
		ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(this, R.layout.list_item, skills);
		
		AutoCompleteTextView skill1 = (AutoCompleteTextView)findViewById(R.id.member1_skill1);
		skill1.setAdapter(skillAdapter);
		AutoCompleteTextView skill2 = (AutoCompleteTextView)findViewById(R.id.member1_skill2);
		skill2.setAdapter(skillAdapter);
		AutoCompleteTextView skill3 = (AutoCompleteTextView)findViewById(R.id.member1_skill3);
		skill3.setAdapter(skillAdapter);
		AutoCompleteTextView skill4 = (AutoCompleteTextView)findViewById(R.id.member1_skill4);
		skill4.setAdapter(skillAdapter);
		AutoCompleteTextView skill5 = (AutoCompleteTextView)findViewById(R.id.member1_skill5);
		skill5.setEnabled(false);
		AutoCompleteTextView skill6 = (AutoCompleteTextView)findViewById(R.id.member1_skill6);
		skill6.setEnabled(false);
		
		AutoCompleteTextView item1 = (AutoCompleteTextView)findViewById(R.id.member1_item1);
		item1.setAdapter(itemAdapter);
		AutoCompleteTextView item2 = (AutoCompleteTextView)findViewById(R.id.member1_item2);
		item2.setEnabled(false);
		AutoCompleteTextView item3 = (AutoCompleteTextView)findViewById(R.id.member1_item3);
		item3.setEnabled(false);
		AutoCompleteTextView item4 = (AutoCompleteTextView)findViewById(R.id.member1_item4);
		item4.setEnabled(false);
		AutoCompleteTextView item5 = (AutoCompleteTextView)findViewById(R.id.member1_item5);
		item5.setEnabled(false);
		AutoCompleteTextView item6 = (AutoCompleteTextView)findViewById(R.id.member1_item6);
		item6.setEnabled(false);
	}

	public void setMainView(IndividualPockemon p) {
    	mainView.removeAllViews();
    	
    	createPockemonStatus(p);
    	if(p.getMega() != null){
    		for(Pokemon mega : p.getMega()){
    	    	createPockemonStatus((Pockemon)mega);
    		}
    	}
//    	
//		Entry<String, Integer> entry = null;
//		AutoCompleteTextView skill1 = (AutoCompleteTextView)findViewById(R.id.member1_skill1);
//		entry = p.getSkillStatictics(0);
//		if(entry != null){
//			skill1.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			skill1.setHint("技1");
//		}
//		if(!p.getSkillNo1().isEmpty()){
//			skill1.setText(p.getSkillNo1());
//		}else{
//			skill1.setText("");
//		}
//		AutoCompleteTextView skill2 = (AutoCompleteTextView)findViewById(R.id.member1_skill2);
//		entry = p.getSkillStatictics(1);
//		if(entry != null){
//			skill2.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			skill2.setHint("技2");
//		}
//		if(!p.getSkillNo2().isEmpty()){
//			skill2.setText(p.getSkillNo2());
//		}else{
//			skill2.setText("");
//		}
//		AutoCompleteTextView skill3 = (AutoCompleteTextView)findViewById(R.id.member1_skill3);
//		entry = p.getSkillStatictics(2);
//		if(entry != null){
//			skill3.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			skill3.setHint("技3");
//		}
//		if(!p.getSkillNo3().isEmpty()){
//			skill3.setText(p.getSkillNo3());
//		}else{
//			skill3.setText("");
//		}
//		AutoCompleteTextView skill4 = (AutoCompleteTextView)findViewById(R.id.member1_skill4);
//		entry = p.getSkillStatictics(3);
//		if(entry != null){
//			skill4.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			skill4.setHint("技4");
//		}
//		if(!p.getSkillNo4().isEmpty()){
//			skill4.setText(p.getSkillNo4());
//		}else{
//			skill4.setText("");
//		}
//		
//		AutoCompleteTextView skill5 = (AutoCompleteTextView)findViewById(R.id.member1_skill5);
//		entry = p.getSkillStatictics(4);
//		if(entry != null){
//			skill5.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			skill5.setHint("技5");
//		}
//		
//		AutoCompleteTextView skill6 = (AutoCompleteTextView)findViewById(R.id.member1_skill6);
//		entry = p.getSkillStatictics(5);
//		if(entry != null){
//			skill6.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			skill6.setHint("技6");
//		}
//		
//		AutoCompleteTextView item1 = (AutoCompleteTextView)findViewById(R.id.member1_item1);
//		entry = p.getItemStatictics(0);
//		if(entry != null){
//			item1.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			item1.setHint("持ち物1");
//		}
//		if(!p.getItem().isEmpty()){
//			item1.setText(p.getItem());
//		}else{
//			item1.setText("");
//		}
//		AutoCompleteTextView item2 = (AutoCompleteTextView)findViewById(R.id.member1_item2);
//		entry = p.getItemStatictics(1);
//		if(entry != null){
//			item2.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			item2.setHint("持ち物2");
//		}
//		AutoCompleteTextView item3 = (AutoCompleteTextView)findViewById(R.id.member1_item3);
//		entry = p.getItemStatictics(2);
//		if(entry != null){
//			item3.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			item3.setHint("持ち物3");
//		}
//		AutoCompleteTextView item4 = (AutoCompleteTextView)findViewById(R.id.member1_item4);
//		entry = p.getItemStatictics(3);
//		if(entry != null){
//			item4.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			item4.setHint("持ち物4");
//		}
//		AutoCompleteTextView item5 = (AutoCompleteTextView)findViewById(R.id.member1_item5);
//		entry = p.getItemStatictics(4);
//		if(entry != null){
//			item5.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			item5.setHint("持ち物5");
//		}
//		AutoCompleteTextView item6 = (AutoCompleteTextView)findViewById(R.id.member1_item6);
//		entry = p.getItemStatictics(5);
//		if(entry != null){
//			item6.setHint(entry.getKey() + " - " + entry.getValue());
//		}else{
//			item6.setHint("持ち物6");
//		}
//		
//		
//		if(p.getSkillNo1().isEmpty()){
//			findViewById(R.id.member1_skill1);
//		}else{
//			
//		}
	}

	private void createPockemonStatus(Pockemon p) {
		LinearLayout sss = new LinearLayout(this);
    	sss.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
    	sss.setOrientation(LinearLayout.HORIZONTAL);
    	
		Bitmap temp = createImage(p);
		ImageView imageView = new ImageView(this);
		imageView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT));		
		imageView.setImageBitmap(temp);
    	sss.addView(imageView);
    	
    	TableLayout status = new TableLayout(this);
    	status.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
    	status.setStretchAllColumns(true);
    	
    	String headers[] = {"H", "A", "B", "C", "D", "S"};
    	status.addView(createTableRow(headers, this, 0, Color.LTGRAY));
    	
    	String statuses[] = {String.valueOf(p.getH()), String.valueOf(p.getA()), String.valueOf(p.getB()), 
    			String.valueOf(p.getC()), String.valueOf(p.getD()), String.valueOf(p.getS())};
    	status.addView(createTableRow(statuses, this, 0, Color.TRANSPARENT));
    	
    	String characteristics[] = {p.getAbility1(), p.getAbility2(), p.getAbilityd()};
    	status.addView(createTableRow(characteristics, this, 2, Color.TRANSPARENT));
    	
    	sss.addView(status);
    	
    	mainView.addView(sss);
	}
	
	public TableRow createTableRow(String[] texts, Context context, int layoutSpan, int bgColor){
    	TableRow row = new TableRow(this);
    	TableRow.LayoutParams p = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
    	if(0 < layoutSpan){
        	p.span = layoutSpan;
    	}
    	row.setLayoutParams(p);
    	for(String temp : texts){
        	if(layoutSpan <= 0){
            	row.addView(createTextView(temp, bgColor, this));
    		}else{
            	row.addView(createTextView(temp, bgColor, this), p);
    		}
    	}
    	return row;
	}
	
	public TextView createTextView(String text, int bgColor, Context context){
    	TextView tv = new TextView(context);
    	tv.setText(text);
    	tv.setBackgroundColor(bgColor);
    	return tv;
	}
	
	ToolActivity activity = this;
	public void finishDownload(int index, RankingPokemonTrend trend){
		if(party == null || party.getMember() == null || party.getMember().size() < index){
			return;
		}
		party.getMember().get(index).setTrend(trend);
	}
	
	public void setTrend(){
		if(tl == null){
			tl = (TableLayout) findViewById(R.id.status);
		}
		tl.removeAllViews();
		
    	String skillHeaders[] = {"技", "所持率"};
    	tl.addView(createTableRow(skillHeaders, this, 0, Color.LTGRAY));
    	
		RankingPokemonTrend trend = party.getMember().get(index).getTrend();
    	if(trend == null){
        	String nullText[] = {"-", "-"};
        	tl.addView(createTableRow(nullText, this, 0, Color.TRANSPARENT));
    	}else{
        	TreeMap<String, String[]> skill = trend.createSkillMap();
        	for(String key : skill.keySet()){
        		String[] temp = skill.get(key).clone();
        		temp[1] = temp[1] + "%";
            	tl.addView(createTableRow(temp, this, 0, Color.TRANSPARENT));
        	}
    	}
    	
    	String characteristicHeaders[] = {"性格", "所持率"};
    	tl.addView(createTableRow(characteristicHeaders, this, 0, Color.LTGRAY));
    	
		trend = party.getMember().get(index).getTrend();
    	if(trend == null){
        	String nullText[] = {"-", "-"};
        	tl.addView(createTableRow(nullText, this, 0, Color.TRANSPARENT));
    	}else{
        	TreeMap<String, String[]> characteristic = trend.createCharacteristicMap();
        	for(String key : characteristic.keySet()){
        		String[] temp = characteristic.get(key).clone();
        		temp[1] = temp[1] + "%";
            	tl.addView(createTableRow(temp, this, 0, Color.TRANSPARENT));
        	}
    	}
    	
    	String itemHeaders[] = {"持ち物", "所持率"};
    	tl.addView(createTableRow(itemHeaders, this, 0, Color.LTGRAY));
    	
		trend = party.getMember().get(index).getTrend();
    	if(trend == null){
        	String nullText[] = {"-", "-"};
        	tl.addView(createTableRow(nullText, this, 0, Color.TRANSPARENT));
    	}else{
        	TreeMap<String, String[]> item = trend.createItemMap();
        	for(String key : item.keySet()){
        		String[] temp = item.get(key).clone();
        		temp[1] = temp[1] + "%";
            	tl.addView(createTableRow(temp, this, 0, Color.TRANSPARENT));
        	}
    	}

    	String abilityHeaders[] = {"特性", "所持率"};
    	tl.addView(createTableRow(abilityHeaders, this, 0, Color.LTGRAY));
    	
		trend = party.getMember().get(index).getTrend();
    	if(trend == null){
        	String nullText[] = {"-", "-"};
        	tl.addView(createTableRow(nullText, this, 0, Color.TRANSPARENT));
    	}else{
        	TreeMap<String, String[]> ability = trend.createAbilityMap();
        	for(String key : ability.keySet()){
        		String[] temp = ability.get(key).clone();
        		temp[1] = temp[1] + "%";
            	tl.addView(createTableRow(temp, this, 0, Color.TRANSPARENT));
        	}
    	}
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		if(tl != null){
			tl.removeAllViews();
			tl = null;
		}
//		applyToIndividualPockemon();
//		updateIndividualPockemon();
	}
}
