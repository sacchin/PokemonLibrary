package com.gmail.sacchin.pockemonbattletools;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;

import com.gmail.sacchin.pockemonbattletools.entity.IndividualPockemon;
import com.gmail.sacchin.pockemonbattletools.entity.Party;
import com.gmail.sacchin.pockemonbattletools.entity.Pockemon;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class PartListActivity extends Activity {
	private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;
	public LinearLayout partyLayout = null;
	private PartyDatabaseHelper databaseHelper;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.party_list);
		databaseHelper = new PartyDatabaseHelper(this);

		partyLayout = (LinearLayout) findViewById(R.id.list);
	}

	@Override
	public void onResume() {
		super.onResume();
		try {
			forBackup();

			ArrayList<Party> parties = databaseHelper.selectAllParty(10);
			if(parties == null || parties.isEmpty()){
				return;
			}else if(parties.get(0).getMember() == null || parties.get(0).getMember().isEmpty()){
				return;
			}

			setMainView(parties.get(0).getMember().get(0));

			for(Party temp : parties){
				LinearLayout party = new LinearLayout(this);
				party.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
				party.setOrientation(LinearLayout.VERTICAL);
				partyLayout.addView(party);

				TableLayout meta = new TableLayout(this);
				meta.setLayoutParams(new TableLayout.LayoutParams(WC, WC));
				meta.setStretchAllColumns(true);
				TableRow row = new TableRow(this);
				meta.addView(row);


				TextView time = new TextView(this);
				time.setText(new SimpleDateFormat("yyyy/MM/dd").format(temp.getTime()));
				row.addView(time);

				TextView user = new TextView(this);
				user.setText(temp.getUserName() == null || temp.getUserName().isEmpty() ? "名無し" : temp.getUserName());
				row.addView(user);

				TextView memo = new TextView(this);
				memo.setText(temp.getUserName() == null || temp.getMemo().isEmpty() ? "メモ無し" : temp.getMemo());
				row.addView(memo);

				party.addView(meta);

				LinearLayout images = new LinearLayout(this);
				images.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
				images.setOrientation(LinearLayout.HORIZONTAL);
				party.addView(images);

				for (int i = 0; i < temp.getMember().size(); i++) {
					IndividualPockemon p = temp.getMember().get(i);
					Bitmap image = createImage(p);
					ImageView imageView = new ImageView(this);
					imageView.setImageBitmap(image);
//					imageView.setOnClickListener(new OnClickIndividualPockemon2(this, p));
					images.addView(imageView);
				}				
			}

		} catch (IOException e) {
		}
	}

	private void forBackup() throws IOException {
		try {
			JSONArray a = databaseHelper.selectAllIndiviualPockemonForJSON();
			JSONArray b = databaseHelper.selectAllPartyForJSON();
			for(int i = 0 ; i < a.length() ; i++){
				Log.v("IndiviualPockemon", a.get(i).toString());
			}
			for(int i = 0 ; i < b.length() ; i++){
				Log.v("AllParty", b.get(i).toString());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public Bitmap createImage(Pockemon p){
		Bitmap image = BitmapFactory.decodeResource(getResources(), p.getResouceId());
		Matrix matrix = new Matrix();
		matrix.postScale(95f / (float)image.getWidth(), 95f / (float)image.getHeight());
		image = Bitmap.createBitmap(image, 0, 0, image.getWidth(),image.getHeight(), matrix, true);	
		return image;
	}

	public void setMainView(IndividualPockemon p) {
		Bitmap temp = createImage(p);
		ImageView imageView;
		imageView = (ImageView)findViewById(R.id.image);
		imageView.setImageBitmap(temp);
		((TextView)findViewById(R.id.item)).setText(String.valueOf(p.getItem().isEmpty() ? "-" : p.getItem()));
		((TextView)findViewById(R.id.skill1)).setText(String.valueOf(p.getSkillNo1().isEmpty() ? "-" : p.getSkillNo1()));
		((TextView)findViewById(R.id.skill2)).setText(String.valueOf(p.getSkillNo2().isEmpty() ? "-" : p.getSkillNo2()));
		((TextView)findViewById(R.id.skill3)).setText(String.valueOf(p.getSkillNo3().isEmpty() ? "-" : p.getSkillNo3()));
		((TextView)findViewById(R.id.skill4)).setText(String.valueOf(p.getSkillNo4().isEmpty() ? "-" : p.getSkillNo4()));
	}
}
