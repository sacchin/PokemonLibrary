package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
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

import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;
import com.gmail.sacchin.pokemonbattleanalyzer.R;
import com.gmail.sacchin.pokemonbattleanalyzer.Util;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonTrend;
import com.gmail.sacchin.pokemonbattleanalyzer.http.PokemonTrendDownloader;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickIndividualPokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Pokemon;

import java.io.IOException;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PGLFragment extends Fragment {
    private PartyDatabaseHelper databaseHelper = null;
//    private ArrayList<String> skills = null;
//    private ArrayList<String> items = null;
    protected Party party = null;
    protected int index = 0;

    protected ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static PGLFragment newInstance(int sectionNumber) {
        PGLFragment fragment = new PGLFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }
    public PGLFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        databaseHelper = new PartyDatabaseHelper(getActivity());
        return null;
    }

    protected void resetParty() {
        final Handler handler = new Handler();
        try {
            party = databaseHelper.selectOpponentParty();
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
                        new PokemonTrendDownloader(pokemonNo, this, i, handler));
            }
//            skills = databaseHelper.selectAllSkill();
//            items = databaseHelper.selectAllItem();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IndividualPBAPokemon getIndividualPBAPokemon(int index){
        return party.getMember().get(index);
    }

    public void finishDownload(int index, RankingPokemonTrend trend){
        if(party == null || party.getMember() == null || party.getMember().size() < index){
            return;
        }
        party.getMember().get(index).setTrend(trend);
    }

    public void setTrend(){}

}
