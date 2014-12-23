package com.gmail.sacchin.pokemonbattleanalyzer;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonTrend;
import com.gmail.sacchin.pokemonbattleanalyzer.http.PGLGetter;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickIndividualPokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sacchin on 2014/12/21.
 */
public class ToolFragment extends Fragment {
    private PartyDatabaseHelper databaseHelper = null;
    private ArrayList<String> skills = null;
    private ArrayList<String> items = null;
    private Party party = null;
    private int index = 0;
    private LinearLayout mainView = null;
    private LinearLayout partyLayout = null;

    private TableLayout tl = null;
    protected ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    public static ToolFragment newInstance(int sectionNumber) {
        ToolFragment fragment = new ToolFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public ToolFragment(){}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tool, container, false);
        mainView = (LinearLayout)rootView.findViewById(R.id.mainview);
        tl = (TableLayout) rootView.findViewById(R.id.status);
        partyLayout = (LinearLayout)rootView.findViewById(R.id.party);
        databaseHelper = new PartyDatabaseHelper(getActivity());
        Button save = (Button) rootView.findViewById(R.id.back);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        final Handler handler = new Handler();
        try {
            party = databaseHelper.selectNewestParty();
            for(int i = 0 ; i < party.getMember().size() ; i++){
                IndividualPBAPokemon p = party.getMember().get(i);
                String pokemonNo = p.getNo();
                if(!pokemonNo.contains("-")){
                    try {
                        pokemonNo = String.valueOf(Integer.parseInt(p.getNo())) + "-0";
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                }
                executorService.execute(
                        new PGLGetter(pokemonNo, this, i, handler));
            }
            skills = databaseHelper.selectAllSkill();
            items = databaseHelper.selectAllItem();
            setAutoComplete(skills, items);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rootView;
    }

        @Override
        public void onResume() {
            super.onResume();
            Log.e("ToolFragment.onResume", "onResume");
            if(partyLayout != null && party != null){
                for (int i = 0; i < party.getMember().size(); i++) {
                    IndividualPBAPokemon p = party.getMember().get(i);
                    Bitmap image = createImage(p);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setImageBitmap(image);
                    imageView.setOnClickListener(new OnClickIndividualPokemon(this, i));
                    partyLayout.addView(imageView);
                    if(i == index){
                        setMainView(p);
                    }
                }
            }
        }

        public IndividualPBAPokemon getIndividualPBAPokemon(int index){
            return party.getMember().get(index);
        }

        public void setIndex(int index){
            this.index = index;
        }

        public Bitmap createImage(PBAPokemon p){
            Bitmap image = BitmapFactory.decodeResource(getResources(), p.getResourceId());
            if(image == null){
                Log.e("createImage", p.getJname() + " - " + p.getResourceId());
                return null;
            }
            Matrix matrix = new Matrix();
            matrix.postScale(150f / (float)image.getWidth(), 150f / (float)image.getHeight());
            image = Bitmap.createBitmap(image, 0, 0, image.getWidth(),image.getHeight(), matrix, true);
            return image;
        }

//        public void applyToIndividualPBAPokemon(){
//            IndividualPBAPokemon p = party.getMember().get(index);
//            String skill1 = ((AutoCompleteTextView)findViewById(R.id.member1_skill1)).getText().toString();
//            p.setSkillNo1(skill1);
//            String skill2 = ((AutoCompleteTextView)findViewById(R.id.member1_skill2)).getText().toString();
//            p.setSkillNo2(skill2);
//            String skill3 = ((AutoCompleteTextView)findViewById(R.id.member1_skill3)).getText().toString();
//            p.setSkillNo3(skill3);
//            String skill4 = ((AutoCompleteTextView)findViewById(R.id.member1_skill4)).getText().toString();
//            p.setSkillNo4(skill4);
//            String item1 = ((AutoComplesteTextView)findViewById(R.id.member1_item1)).getText().toString();
//            p.setItem(item1);
//            party.setUserName(((TextView)findViewById(R.id.user)).getText().toString());
//            party.setMemo(((TextView)findViewById(R.id.memo)).getText().toString());
//        }
//
//        public void updateIndividualPBAPokemon(){
//            try {
//                databaseHelper.updateIndividualPBAPokemonData(party);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

        private void setAutoComplete(ArrayList<String> skills, ArrayList<String> items) {
//            ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, R.layout.list_item, items);
//            ArrayAdapter<String> skillAdapter = new ArrayAdapter<String>(this, R.layout.list_item, skills);
//
//            AutoCompleteTextView skill1 = (AutoCompleteTextView)findViewById(R.id.member1_skill1);
//            skill1.setAdapter(skillAdapter);
//            AutoCompleteTextView skill2 = (AutoCompleteTextView)findViewById(R.id.member1_skill2);
//            skill2.setAdapter(skillAdapter);
//            AutoCompleteTextView skill3 = (AutoCompleteTextView)findViewById(R.id.member1_skill3);
//            skill3.setAdapter(skillAdapter);
//            AutoCompleteTextView skill4 = (AutoCompleteTextView)findViewById(R.id.member1_skill4);
//            skill4.setAdapter(skillAdapter);
//            AutoCompleteTextView skill5 = (AutoCompleteTextView)findViewById(R.id.member1_skill5);
//            skill5.setEnabled(false);
//            AutoCompleteTextView skill6 = (AutoCompleteTextView)findViewById(R.id.member1_skill6);
//            skill6.setEnabled(false);
//
//            AutoCompleteTextView item1 = (AutoCompleteTextView)findViewById(R.id.member1_item1);
//            item1.setAdapter(itemAdapter);
//            AutoCompleteTextView item2 = (AutoCompleteTextView)findViewById(R.id.member1_item2);
//            item2.setEnabled(false);
//            AutoCompleteTextView item3 = (AutoCompleteTextView)findViewById(R.id.member1_item3);
//            item3.setEnabled(false);
//            AutoCompleteTextView item4 = (AutoCompleteTextView)findViewById(R.id.member1_item4);
//            item4.setEnabled(false);
//            AutoCompleteTextView item5 = (AutoCompleteTextView)findViewById(R.id.member1_item5);
//            item5.setEnabled(false);
//            AutoCompleteTextView item6 = (AutoCompleteTextView)findViewById(R.id.member1_item6);
//            item6.setEnabled(false);
        }

        public void setMainView(IndividualPBAPokemon p) {
            mainView.removeAllViews();

            Log.e("setMainView","setMainView");
            createPBAPokemonStatus(p);
            if(p.getMega() != null){
                for(Pokemon mega : p.getMega()){
                    createPBAPokemonStatus((PBAPokemon)mega);
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

        private void createPBAPokemonStatus(PBAPokemon p) {
            Log.e("createPBAPokemonStatus",p.getJname());
            LinearLayout sss = new LinearLayout(getActivity());
            sss.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            sss.setOrientation(LinearLayout.HORIZONTAL);

            Bitmap temp = createImage(p);
            ImageView imageView = new ImageView(getActivity());
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setImageBitmap(temp);
            sss.addView(imageView);

            TableLayout status = new TableLayout(getActivity());
            status.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
            status.setStretchAllColumns(true);

            String headers[] = {"H", "A", "B", "C", "D", "S"};
            status.addView(createTableRow(headers, 0, Color.LTGRAY));

            String statuses[] = {String.valueOf(p.getH()), String.valueOf(p.getA()), String.valueOf(p.getB()),
                    String.valueOf(p.getC()), String.valueOf(p.getD()), String.valueOf(p.getS())};
            status.addView(createTableRow(statuses, 0, Color.TRANSPARENT));

            String characteristics[] = {p.getAbility1(), p.getAbility2(), p.getAbilityd()};
            status.addView(createTableRow(characteristics, 2, Color.TRANSPARENT));

            sss.addView(status);

            mainView.addView(sss);
        }

        public TableRow createTableRow(String[] texts, int layoutSpan, int bgColor){
            TableRow row = new TableRow(getActivity());
            TableRow.LayoutParams p = new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT);
            if(0 < layoutSpan){
                p.span = layoutSpan;
            }
            row.setLayoutParams(p);
            for(String temp : texts){
                if(layoutSpan <= 0){
                    row.addView(createTextView(temp, bgColor));
                }else{
                    row.addView(createTextView(temp, bgColor), p);
                }
            }
            return row;
        }

        public TextView createTextView(String text, int bgColor){
            TextView tv = new TextView(getActivity());
            tv.setText(text);
            tv.setBackgroundColor(bgColor);
            return tv;
        }

        public void finishDownload(int index, RankingPokemonTrend trend){
            if(party == null || party.getMember() == null || party.getMember().size() < index){
                return;
            }
            party.getMember().get(index).setTrend(trend);
        }

        public void setTrend(){
            if(tl == null){
                Log.e("setTrend","tl == null!");
                return;
            }
            tl.removeAllViews();

            String skillHeaders[] = {"技", "所持率"};
            tl.addView(createTableRow(skillHeaders, 0, Color.LTGRAY));

            RankingPokemonTrend trend = party.getMember().get(index).getTrend();
            if(trend == null){
                String nullText[] = {"-", "-"};
                tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT));
            }else{
                TreeMap<String, String[]> skill = trend.createSkillMap();
                for(String key : skill.keySet()){
                    String[] temp = skill.get(key).clone();
                    temp[1] = temp[1] + "%";
                    tl.addView(createTableRow(temp, 0, Color.TRANSPARENT));
                }
            }

            String characteristicHeaders[] = {"性格", "所持率"};
            tl.addView(createTableRow(characteristicHeaders, 0, Color.LTGRAY));

            trend = party.getMember().get(index).getTrend();
            if(trend == null){
                String nullText[] = {"-", "-"};
                tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT));
            }else{
                TreeMap<String, String[]> characteristic = trend.createCharacteristicMap();
                for(String key : characteristic.keySet()){
                    String[] temp = characteristic.get(key).clone();
                    temp[1] = temp[1] + "%";
                    tl.addView(createTableRow(temp, 0, Color.TRANSPARENT));
                }
            }

            String itemHeaders[] = {"持ち物", "所持率"};
            tl.addView(createTableRow(itemHeaders, 0, Color.LTGRAY));

            trend = party.getMember().get(index).getTrend();
            if(trend == null){
                String nullText[] = {"-", "-"};
                tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT));
            }else{
                TreeMap<String, String[]> item = trend.createItemMap();
                for(String key : item.keySet()){
                    String[] temp = item.get(key).clone();
                    temp[1] = temp[1] + "%";
                    tl.addView(createTableRow(temp, 0, Color.TRANSPARENT));
                }
            }

            String abilityHeaders[] = {"特性", "所持率"};
            tl.addView(createTableRow(abilityHeaders, 0, Color.LTGRAY));

            trend = party.getMember().get(index).getTrend();
            if(trend == null){
                String nullText[] = {"-", "-"};
                tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT));
            }else{
                TreeMap<String, String[]> ability = trend.createAbilityMap();
                for(String key : ability.keySet()){
                    String[] temp = ability.get(key).clone();
                    temp[1] = temp[1] + "%";
                    tl.addView(createTableRow(temp, 0, Color.TRANSPARENT));
                }
            }
        }

        @Override
        public void onStop() {
            super.onStop();
            if(tl != null){
                tl.removeAllViews();
                tl = null;
            }
//		applyToIndividualPBAPokemon();
//		updateIndividualPBAPokemon();
        }
}
