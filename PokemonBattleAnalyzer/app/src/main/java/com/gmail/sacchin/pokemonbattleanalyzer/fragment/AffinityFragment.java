package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.Toast;

import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;
import com.gmail.sacchin.pokemonbattleanalyzer.R;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonlibrary.entity.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by sacchin on 2014/12/23.
 */
public class AffinityFragment extends Fragment {
    private PartyDatabaseHelper databaseHelper = null;
    private ArrayList<String> skills = null;
    private ArrayList<String> items = null;
    private Party party = null;
    private int index = 0;
    private LinearLayout selectPokemon = null;
    private LinearLayout partyLayout = null;
    private PBAPokemon p = null;

    private TableLayout tl = null;
    protected ExecutorService executorService = Executors.newCachedThreadPool();
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    public static AffinityFragment newInstance(String pokemoNo) {
        AffinityFragment fragment = new AffinityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, pokemoNo);
        fragment.setArguments(args);
        return fragment;
    }
    public AffinityFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_affinity, container, false);
        selectPokemon = (LinearLayout)rootView.findViewById(R.id.select_pokemon);
        tl = (TableLayout) rootView.findViewById(R.id.status);
        partyLayout = (LinearLayout)rootView.findViewById(R.id.party);
        databaseHelper = new PartyDatabaseHelper(getActivity());
        Button save = (Button) rootView.findViewById(R.id.back);


        String pokemonNo = getArguments().getString(ARG_SECTION_NUMBER);
        try {
            this.p = databaseHelper.selectPBAPokemon(pokemonNo);
            Log.e("AffinityFragment", p.getJname());


            Type.
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(selectPokemon != null){
            Bitmap image = createImage(p);
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageBitmap(image);
            selectPokemon.addView(imageView);
        }
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
}
