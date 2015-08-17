package com.gmail.sacchin.pokemonbattleanalyzer;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonTrend;
import com.gmail.sacchin.pokemonbattleanalyzer.fragment.PGLFragment;
import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;

import java.util.TreeMap;

public class DetailActivityFragment extends PGLFragment {

    private long id;
    private TableLayout tl = null;
    private ImageView iv;
    private LinearLayout mainView = null;
    private CollapsingToolbarLayout toolbar = null;

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        iv = (ImageView)view.findViewById(R.id.image);
        tl = (TableLayout)view.findViewById(R.id.table);
        mainView = (LinearLayout)view.findViewById(R.id.mainview);
        toolbar = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        toolbar.setCollapsedTitleTextColor(Color.WHITE);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DetailActivity da = (DetailActivity)getActivity();
        if(da.getId() != -1){
            this.id = da.getId();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        resetParty();
    }

    @Override
    public void finishAllDownload() {
        for(IndividualPBAPokemon p : party.getMember()){
            if(p.getId() == this.id){
                iv.setImageResource(p.getResourceId());
                setTrend(p);
                setMainView(p);
                toolbar.setTitle(p.getJname());
                break;
            }
        }
    }

    @Override
    public void setTrend() {}

    public void setTrend(IndividualPBAPokemon p) {
        if(tl == null){
            Log.e("setTrend", "tl == null!");
            return;
        }
        tl.removeAllViews();

        TableLayout.LayoutParams lp = new TableLayout.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        tl.setLayoutParams(lp);
        Log.v("setTrend", String.valueOf(tl.getLayoutParams().width));

        String skillHeaders[] = {"技", "所持率"};
        tl.addView(createTableRow(skillHeaders, 0, Color.parseColor("#EF5350"), Color.WHITE));
        RankingPokemonTrend trend = p.getTrend();
        if(trend == null){
            String nullText[] = {"-", "-"};
            tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT, Color.BLACK));
        }else{
            TreeMap<String, String[]> skill = trend.createSkillMap();
            for(String key : skill.keySet()){
                String[] temp = skill.get(key).clone();
                temp[1] = temp[1] + "%";
                tl.addView(createTableRow(temp, 0, Color.TRANSPARENT, Color.BLACK));
            }
        }

        String characteristicHeaders[] = {"性格", "所持率"};
        tl.addView(createTableRow(characteristicHeaders, 0, Color.parseColor("#EF5350"), Color.WHITE));
        if(trend == null){
            String nullText[] = {"-", "-"};
            tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT, Color.BLACK));
        }else{
            TreeMap<String, String[]> characteristic = trend.createCharacteristicMap();
            for(String key : characteristic.keySet()){
                String[] temp = characteristic.get(key).clone();
                temp[1] = temp[1] + "%";
                tl.addView(createTableRow(temp, 0, Color.TRANSPARENT, Color.BLACK));
            }
        }

        String itemHeaders[] = {"持ち物", "所持率"};
        tl.addView(createTableRow(itemHeaders, 0, Color.parseColor("#EF5350"), Color.WHITE));
        if(trend == null){
            String nullText[] = {"-", "-"};
            tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT, Color.BLACK));
        }else{
            TreeMap<String, String[]> item = trend.createItemMap();
            for(String key : item.keySet()){
                String[] temp = item.get(key).clone();
                temp[1] = temp[1] + "%";
                tl.addView(createTableRow(temp, 0, Color.TRANSPARENT, Color.BLACK));
            }
        }

        String abilityHeaders[] = {"特性", "所持率"};
        tl.addView(createTableRow(abilityHeaders, 0, Color.parseColor("#EF5350"), Color.WHITE));
        if(trend == null){
            String nullText[] = {"-", "-"};
            tl.addView(createTableRow(nullText, 0, Color.TRANSPARENT, Color.BLACK));
        }else{
            TreeMap<String, String[]> ability = trend.createAbilityMap();
            for(String key : ability.keySet()){
                String[] temp = ability.get(key).clone();
                temp[1] = temp[1] + "%";
                tl.addView(createTableRow(temp, 0, Color.TRANSPARENT, Color.BLACK));
            }
        }
    }

    public TableRow createTableRow(String[] texts, int layoutSpan, int bgColor, int txtColor){
        TableRow row = new TableRow(getActivity());
        TableRow.LayoutParams p = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        if(0 < layoutSpan){
            p.span = layoutSpan;
        }
        row.setLayoutParams(p);
        for(String temp : texts){
            if(layoutSpan <= 0){
                row.addView(createTextView(temp, bgColor, txtColor));
            }else{
                row.addView(createTextView(temp, bgColor, txtColor), p);
            }
        }
        return row;
    }

    public TextView createTextView(String text, int bgColor, int txtColor){
        TextView tv = new TextView(getActivity());
        tv.setText(text);
        tv.setBackgroundColor(bgColor);
        tv.setTextColor(txtColor);
        TableRow.LayoutParams p = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(p);
        return tv;
    }

    public void setMainView(IndividualPBAPokemon p) {
        mainView.removeAllViews();

        createPBAPokemonStatus(p);
        if(p.getMega() != null){
            for(Pokemon mega : p.getMega()){
                createPBAPokemonStatus((PBAPokemon)mega);
            }
        }
    }

    private void createPBAPokemonStatus(PBAPokemon p) {
        Log.e("createPBAPokemonStatus", p.getJname());
        LinearLayout sss = new LinearLayout(getActivity());
        sss.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        sss.setOrientation(LinearLayout.HORIZONTAL);

        Bitmap temp = Util.createImage(p, 150f, getResources());
        ImageView imageView = new ImageView(getActivity());
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageBitmap(temp);
        sss.addView(imageView);

        TableLayout status = new TableLayout(getActivity());
        status.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT));
        status.setStretchAllColumns(true);

        String headers[] = {"H", "A", "B", "C", "D", "S"};
        status.addView(createTableRow(headers, 0, Color.parseColor("#EF5350"), Color.WHITE));

        String statuses[] = {String.valueOf(p.getH()), String.valueOf(p.getA()), String.valueOf(p.getB()),
                String.valueOf(p.getC()), String.valueOf(p.getD()), String.valueOf(p.getS())};
        status.addView(createTableRow(statuses, 0, Color.TRANSPARENT, Color.BLACK));

        String characteristics[] = {p.getAbility1(), p.getAbility2(), p.getAbilityd()};
        status.addView(createTableRow(characteristics, 2, Color.TRANSPARENT, Color.BLACK));

        sss.addView(status);

        mainView.addView(sss);
    }

    @Override
    public void onStop() {
        super.onStop();
        if(tl != null){
            tl.removeAllViews();
            tl = null;
        }
    }
}
