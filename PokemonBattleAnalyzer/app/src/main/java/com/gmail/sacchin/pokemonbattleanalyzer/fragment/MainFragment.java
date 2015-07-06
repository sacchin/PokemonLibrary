package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.sacchin.pokemonbattleanalyzer.MainActivity;
import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;
import com.gmail.sacchin.pokemonbattleanalyzer.R;
import com.gmail.sacchin.pokemonbattleanalyzer.Util;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.http.PokemonRankingDownloader;
import com.gmail.sacchin.pokemonbattleanalyzer.http.PokemonTrendDownloader;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.PartyInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickCheckAffinityButton;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickCreateNewPartyButton;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickFromList;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickFromParty;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    private ViewGroup.LayoutParams LP = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT);

    private PartyDatabaseHelper databaseHelper;
    private Party party = null;
    private static ScrollView scrollView;
    private static LinearLayout partyLayout = null;
    protected ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static MainFragment newInstance(int sectionNumber) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public MainFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        partyLayout = (LinearLayout) rootView.findViewById(R.id.party);
        scrollView = (ScrollView)rootView.findViewById(R.id.scrollView);
        databaseHelper = new PartyDatabaseHelper(getActivity());
        party = new Party();

        Button createOpponentParty = (Button) rootView.findViewById(R.id.createOpponent);
        createOpponentParty.setOnClickListener(new OnClickCreateNewPartyButton(this, false));
        Button createMyParty = (Button) rootView.findViewById(R.id.createMine);
        createMyParty.setOnClickListener(new OnClickCreateNewPartyButton(this, true));
        Button showAffinity = (Button) rootView.findViewById(R.id.affinityButton);
        showAffinity.setOnClickListener(new OnClickCheckAffinityButton(this));

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            party.clear();
            partyLayout.removeAllViews();
            createPokemonList();
        } catch (ClassCastException e) {
            throw new IllegalStateException("activity should implement FragmentCallbacks", e);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    private static class PBAPokemonRankComparator implements Comparator<PBAPokemon> {
        @Override
        public int compare(PBAPokemon pokemonA, PBAPokemon pokemonB) {
            return pokemonA.getRanking() - pokemonB.getRanking();
        }
    }

    public void createPokemonList() {
        if(scrollView == null){
            Log.e("createPokemonList", "null!");
            return;
        }
        if(0 < scrollView.getChildCount()){
            Log.i("createPokemonList", "scrollView is already created!");
            return;
        }

        LinearLayout all = new LinearLayout(getActivity());
        all.setLayoutParams(LP);
        all.setOrientation(LinearLayout.VERTICAL);
        all.setGravity(Gravity.CENTER);
        scrollView.addView(all);

        try {
            List<PBAPokemon> list = databaseHelper.selectAllPBAPokemon();
            Collections.sort(list, new PBAPokemonRankComparator());
            Map<String, Integer> countMap = new HashMap<>();

            int count = 0;
            for(;count < list.size();){
                LinearLayout block = new LinearLayout(getActivity());
                block.setLayoutParams(LP);
                block.setGravity(Gravity.CENTER);
                for(int i = 0 ; i < 5 ; i++){
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
    public FrameLayout createFrameLayout(PBAPokemon p, Map<String, Integer> countMap){
        FrameLayout fl = new FrameLayout(getActivity());
        Bitmap temp = Util.createImage(p, 200f, getResources());
        ImageView localView = new ImageView(getActivity());
        localView.setImageBitmap(temp);
        fl.setOnClickListener(new OnClickFromList(this, p));

        fl.addView(localView);
        TextView tv = new TextView(getActivity());

        Integer c = countMap.get(String.valueOf(p.getRowId()));
        if(c != null){
            tv.setText(String.valueOf(c));
        }else{
            tv.setText("0");
        }
        fl.addView(tv);
        return fl;
    }

    public void addPokemonToList(PBAPokemon pokemon){
        IndividualPBAPokemon ip = new IndividualPBAPokemon(pokemon, 0, new Timestamp(System.currentTimeMillis()), "", "", "", "", "", "");
        int index = party.setMember(ip);
        if(index == -1){
            Toast.makeText(getActivity(), "すでに6体選択しています。", Toast.LENGTH_SHORT).show();
        }else {
            Bitmap temp = Util.createImage(pokemon, 120f, getResources());
            ImageView localView = new ImageView(getActivity());
            localView.setImageBitmap(temp);
            localView.setOnClickListener(new OnClickFromParty(this, ip));

            partyLayout.addView(localView);
        }
    }

    public void removePokemonFromList(IndividualPBAPokemon pokemon){
        try {
            int index = party.removeMember(pokemon);
            if(-1 < index){
                partyLayout.removeViewAt(index + 1);
            }else{
                party.setMember(pokemon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createOpponentParty(){
        if(party.getMember() == null || party.getMember().size() < 1){
            Toast.makeText(getActivity(), "ポケモンが選択されていません。", Toast.LENGTH_SHORT).show();
            return;
        }
        party.setTime(new Timestamp(System.currentTimeMillis()));
        executorService.execute(new PartyInsertHandler(databaseHelper, party, false));
        ((MainActivity)getActivity()).startToolActivity();
    }

    public void createMyParty(){
        if(party.getMember() == null || party.getMember().size() < 1){
            Toast.makeText(getActivity(), "ポケモンが選択されていません。", Toast.LENGTH_SHORT).show();
            return;
        }
        party.setTime(new Timestamp(System.currentTimeMillis()));
        party.setUserName("mine");
        executorService.execute(new PartyInsertHandler(databaseHelper, party, false));
        Toast.makeText(getActivity(), "登録しました。", Toast.LENGTH_SHORT).show();
    }

    public void showAffinity(){
        if(party.getMember() == null || party.getMember().size() < 1){
            Toast.makeText(getActivity(), "ポケモンが選択されていません。", Toast.LENGTH_SHORT).show();
            return;
        }
        ((MainActivity)getActivity()).startAffinityActivity(party.getMember().get(0));
    }
}