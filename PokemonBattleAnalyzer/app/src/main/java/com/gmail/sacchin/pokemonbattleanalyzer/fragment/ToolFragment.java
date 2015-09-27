package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;

import com.gmail.sacchin.pokemonbattleanalyzer.BattleUtil;
import com.gmail.sacchin.pokemonbattleanalyzer.activity.DetailActivity;
import com.gmail.sacchin.pokemonbattleanalyzer.logic.BattleCalculator;
import com.gmail.sacchin.pokemonbattleanalyzer.logic.BattleCalculator.RiskDegree;
import com.gmail.sacchin.pokemonbattleanalyzer.logic.EstimateOpponentElection;
import com.gmail.sacchin.pokemonbattleanalyzer.R;
import com.gmail.sacchin.pokemonbattleanalyzer.Util;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickChoicedPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickIndividualPokemon;

import java.io.IOException;

public class ToolFragment extends PGLFragment {
    private int index = 0;

    private LinearLayout partyLayout = null;
    private LinearLayout choicedLayout = null;
    private TableLayout tl = null;

    private Party choiced = null;

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
        super.onCreateView(inflater, container, savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_tool, container, false);
        tl = (TableLayout) rootView.findViewById(R.id.status);
        partyLayout = (LinearLayout)rootView.findViewById(R.id.party);
        choicedLayout = (LinearLayout)rootView.findViewById(R.id.choiced);
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        resetParty(true);
        resetMyParty();
        createPartyList();
    }

    protected void resetMyParty() {
        try {
            choiced = databaseHelper.selectMyChoicedParty();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createPartyList() {
        if(partyLayout == null){
            Log.e("createPartyList", "null!");
            return;
        }else{
            partyLayout.removeAllViews();
        }

        if(party != null){
            for (int i = 0; i < party.getMember().size(); i++) {
                IndividualPBAPokemon p = party.getMember().get(i);
                FrameLayout frame = createFrameLayout(p, 210f);
                frame.setOnClickListener(new OnClickIndividualPokemon(this, i, (ImageView)frame.getChildAt(0)));
                partyLayout.addView(frame);
            }
        }

        if(choicedLayout == null){
            Log.e("choicedLayout", "null!");
            return;
        }else{
            choicedLayout.removeAllViews();
        }

        if(choiced != null){
            for (int i = 0; i < choiced.getMember().size(); i++) {
                IndividualPBAPokemon p = choiced.getMember().get(i);
                FrameLayout frame = createFrameLayout(p, 280f);
                frame.setOnClickListener(new OnClickChoicedPokemon(this, i));
                choicedLayout.addView(frame);
            }
        }
    }

    public FrameLayout createFrameLayout(PBAPokemon p, float size){
        FrameLayout fl = new FrameLayout(getActivity());

        Bitmap temp = Util.createImage(p, size, getResources());
        ImageView localView = new ImageView(getActivity());
        localView.setImageBitmap(temp);
        localView.setTransitionName("image");
        fl.addView(localView);

        return fl;
    }

    public IndividualPBAPokemon getIndividualPBAPokemon(int index){
        return party.getMember().get(index);
    }

    @Override
    public void finishAllDownload() {

    }

    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public void setTrend(){

    }

    public void setAlert(int index){
        IndividualPBAPokemon tapped = choiced.getMember().get(index);
        if(party != null){
            for (int i = 0; i < party.getMember().size(); i++) {
                IndividualPBAPokemon p = party.getMember().get(i);
                FrameLayout frame = (FrameLayout)partyLayout.getChildAt(i);
                if(1 < frame.getChildCount()){
                    frame.removeViewAt(frame.getChildCount() - 1);
                }
                Bitmap over;
                ImageView overView = new ImageView(getActivity());
                switch (BattleCalculator.getAttackOrder(tapped, p)){
                    case SAFE:
                        over = Util.createImage(R.drawable.safe, 210f, getResources());
                        break;
                    case FATAL:
                        over = Util.createImage(R.drawable.caution, 210f, getResources());
                        break;
                    default:
                        over = Util.createImage(R.drawable.alert, 210f, getResources());
                        break;
                }
                overView.setImageBitmap(over);
                frame.addView(overView);
            }
        }
    }

    public void startDetailActivity(IndividualPBAPokemon pokemon, ImageView from){
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("id", pokemon.getId());
        startActivity(intent,
                ActivityOptions.makeSceneTransitionAnimation(getActivity(), from, "image").toBundle());
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
