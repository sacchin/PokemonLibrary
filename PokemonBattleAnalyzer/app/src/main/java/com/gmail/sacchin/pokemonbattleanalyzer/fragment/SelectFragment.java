package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gmail.sacchin.pokemonbattleanalyzer.SelectActivity;
import com.gmail.sacchin.pokemonbattleanalyzer.interfaces.AddToListInterface;
import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;
import com.gmail.sacchin.pokemonbattleanalyzer.R;
import com.gmail.sacchin.pokemonbattleanalyzer.Util;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickFromList;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SelectFragment extends Fragment implements AddToListInterface {
    private PartyDatabaseHelper databaseHelper = null;

    private Party opponent = null;
    private Party mine = null;

    private LinearLayout selected = null;

    private ImageView myParty[] = null;
    private ImageView opponentParty[] = null;

    private List<IndividualPBAPokemon> selectedPokemon = null;

    protected ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static SelectFragment newInstance(int sectionNumber) {
        SelectFragment fragment = new SelectFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public SelectFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_select, container, false);
        selected = (LinearLayout) rootView.findViewById(R.id.selected);

        myParty = new ImageView[6];
        myParty[0] = (ImageView) rootView.findViewById(R.id.my_party1);
        myParty[1] = (ImageView) rootView.findViewById(R.id.my_party2);
        myParty[2] = (ImageView) rootView.findViewById(R.id.my_party3);
        myParty[3] = (ImageView) rootView.findViewById(R.id.my_party4);
        myParty[4] = (ImageView) rootView.findViewById(R.id.my_party5);
        myParty[5] = (ImageView) rootView.findViewById(R.id.my_party6);
        opponentParty = new ImageView[6];
        opponentParty[0] = (ImageView) rootView.findViewById(R.id.opponent_party1);
        opponentParty[1] = (ImageView) rootView.findViewById(R.id.opponent_party2);
        opponentParty[2] = (ImageView) rootView.findViewById(R.id.opponent_party3);
        opponentParty[3] = (ImageView) rootView.findViewById(R.id.opponent_party4);
        opponentParty[4] = (ImageView) rootView.findViewById(R.id.opponent_party5);
        opponentParty[5] = (ImageView) rootView.findViewById(R.id.opponent_party6);

        databaseHelper = new PartyDatabaseHelper(getActivity());
        Button save = (Button) rootView.findViewById(R.id.back);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
        Button next = (Button) rootView.findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SelectActivity)getActivity()).startToolActivity();
            }
        });
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        createPartyList();
    }

    private void createPartyList() {
        try {
            opponent = databaseHelper.selectOpponentParty();
            if (opponent != null) {
                for (int i = 0; i < opponent.getMember().size(); i++) {
                    IndividualPBAPokemon p = opponent.getMember().get(i);
                    Bitmap image = Util.createImage(p, 200f, getResources());
                    opponentParty[i].setImageBitmap(image);
                    opponentParty[i].setOnClickListener(new OnClickFromList(this, p));
                }
            }

            mine = databaseHelper.selectMyParty();
            if (mine != null) {
                for (int i = 0; i < mine.getMember().size(); i++) {
                    IndividualPBAPokemon p = mine.getMember().get(i);
                    Bitmap image = Util.createImage(p, 200f, getResources());
                    myParty[i].setImageBitmap(image);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addPokemonToList(PBAPokemon pokemon) {
        IndividualPBAPokemon ip = new IndividualPBAPokemon(pokemon, 0, new Timestamp(System.currentTimeMillis()), "", "", "", "", "", "");
        if (selectedPokemon == null) {
            selectedPokemon = new ArrayList<>();
        } else if (selectedPokemon.size() == 3) {
            Toast.makeText(getActivity(), "すでに3体選択しています。", Toast.LENGTH_SHORT).show();
        }
        selectedPokemon.add(ip);

        Bitmap temp = Util.createImage(pokemon, 150f, getResources());
        ImageView localView = new ImageView(getActivity());
        localView.setImageBitmap(temp);
        selected.addView(localView);
    }

    public void removePokemonFromList(PBAPokemon pokemon) {

    }
}
