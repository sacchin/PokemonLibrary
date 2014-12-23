package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
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
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.insert.PartyInsertHandler;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickCreateNewPartyButton;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickFromList;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickFromParty;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainFragment extends Fragment {
    private ViewGroup.LayoutParams LP = new ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

    private PartyDatabaseHelper databaseHelper;
    public Party party = null;
    public static ScrollView scrollView;
    public static LinearLayout partyLayout = null;
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

        Button createNewParty = (Button) rootView.findViewById(R.id.createButton);
        createNewParty.setOnClickListener(new OnClickCreateNewPartyButton(this));
//        Button showParty = (Button) rootView.findViewById(R.id.showButton);
//        showParty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("onClick", "l !");
//            }
//        });
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

    public void createPokemonList() {
        if(scrollView == null || 0 < scrollView.getChildCount()){
            Log.i("createPokemonList", "null!");
            return;
        }

        LinearLayout all = new LinearLayout(getActivity());
        all.setLayoutParams(LP);
        all.setOrientation(LinearLayout.VERTICAL);
        all.setGravity(Gravity.CENTER);
        scrollView.addView(all);

        try {
            ArrayList<PBAPokemon> list = databaseHelper.selectAllPBAPokemon();
            HashMap<String, Integer> countMap = new HashMap<String, Integer>();

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
    public FrameLayout createFrameLayout(PBAPokemon p, HashMap<String, Integer> countMap){
        Resources r = getResources();
        FrameLayout fl = new FrameLayout(getActivity());
        ImageView localView = new ImageView(getActivity());
        Bitmap temp = BitmapFactory.decodeResource(r, p.getResourceId());
        Matrix matrix = new Matrix();
        matrix.postScale(200f / (float)temp.getWidth(), 200f / (float)temp.getHeight());
        temp = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(),temp.getHeight(), matrix, true);
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
            Bitmap temp = BitmapFactory.decodeResource(getResources(), pokemon.getResourceId());
            Matrix matrix = new Matrix();
            matrix.postScale(120f / (float) temp.getWidth(), 120f / (float) temp.getHeight());
            temp = Bitmap.createBitmap(temp, 0, 0, temp.getWidth(), temp.getHeight(), matrix, true);

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

    public void start(){
        if(party.getMember() == null || party.getMember().size() < 1){
            Toast.makeText(getActivity(), "ポケモンが選択されていません。", Toast.LENGTH_SHORT).show();
            return;
        }
        party.setTime(new Timestamp(System.currentTimeMillis()));
        executorService.execute(new PartyInsertHandler(databaseHelper, party, false));
        ((MainActivity)getActivity()).startToolActivity();
    }

}