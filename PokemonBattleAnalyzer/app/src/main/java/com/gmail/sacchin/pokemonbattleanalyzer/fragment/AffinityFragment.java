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
import android.widget.TextView;
import android.widget.Toast;

import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;
import com.gmail.sacchin.pokemonbattleanalyzer.R;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickTypeText;
import com.gmail.sacchin.pokemonlibrary.entity.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private Map<String, Integer> code = null;

    private LinearLayout[] mainPokemonAffinity = null;
    private LinearLayout[] targetPokemonAffinity = null;

    private LinearLayout typeList = null;
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
        typeList = (LinearLayout)rootView.findViewById(R.id.type_list);
        code = new HashMap<>();
        code.put("400", new Integer(0));
        code.put("200", new Integer(1));
        code.put("50", new Integer(2));
        code.put("25", new Integer(3));
        code.put("0", new Integer(4));

        mainPokemonAffinity = new LinearLayout[5];
        mainPokemonAffinity[0] = (LinearLayout)rootView.findViewById(R.id.main_type_of_4);
        mainPokemonAffinity[1] = (LinearLayout)rootView.findViewById(R.id.main_type_of_2);
        mainPokemonAffinity[2] = (LinearLayout)rootView.findViewById(R.id.main_type_of_h);
        mainPokemonAffinity[3] = (LinearLayout)rootView.findViewById(R.id.main_type_of_q);
        mainPokemonAffinity[4] = (LinearLayout)rootView.findViewById(R.id.main_type_of_z);

        targetPokemonAffinity = new LinearLayout[5];
        targetPokemonAffinity[0] = (LinearLayout)rootView.findViewById(R.id.target_type_of_4);
        targetPokemonAffinity[1] = (LinearLayout)rootView.findViewById(R.id.target_type_of_2);
        targetPokemonAffinity[2] = (LinearLayout)rootView.findViewById(R.id.target_type_of_h);
        targetPokemonAffinity[3] = (LinearLayout)rootView.findViewById(R.id.target_type_of_q);
        targetPokemonAffinity[4] = (LinearLayout)rootView.findViewById(R.id.target_type_of_z);

        databaseHelper = new PartyDatabaseHelper(getActivity());
        Button back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        String pokemonNo = getArguments().getString(ARG_SECTION_NUMBER);
        try {
            this.p = databaseHelper.selectPBAPokemon(pokemonNo);
            Map<String, Integer> resultMap = new HashMap<>();
            for(Type.TypeCode temp : Type.TypeCode.values()){
                Integer result = code.get(String.valueOf((int) (Type.calcurateAffinity(temp, p) * 100)));
                if(result != null){
                    resultMap.put(Type.convertTypeCodeToName(temp), result);
                }
            }
            for(String typeName : resultMap.keySet()){
                int p = resultMap.get(typeName);
                TextView temp = new TextView(getActivity());
                temp.setText(typeName);
                mainPokemonAffinity[p].addView(temp);
            }

            Map<String, List<PBAPokemon>> typeMap = new HashMap<>();
            List<PBAPokemon> list = databaseHelper.selectAllPBAPokemon();
            for(PBAPokemon temp : list){
                String key1 = Type.convertTypeCodeToName(temp.getType1()) +
                        Type.convertTypeCodeToName(temp.getType2());
                String key2 = Type.convertTypeCodeToName(temp.getType2()) +
                        Type.convertTypeCodeToName(temp.getType1());

                List<PBAPokemon> oneType = typeMap.get(key1);
                if(oneType == null){
                    oneType = typeMap.get(key2);
                    if(oneType == null) {
                        oneType = new ArrayList<PBAPokemon>();
                        typeMap.put(key1, oneType);
                    }
                }
                oneType.add(temp);
            }
            for (String key : typeMap.keySet()) {
                List<PBAPokemon> oneType = typeMap.get(key);
                PBAPokemon top = oneType.get(0);
                String type1 = Type.convertTypeCodeToName(top.getType1());
                String type2 = Type.convertTypeCodeToName(top.getType2());
                TextView temp = new TextView(getActivity());
                temp.setOnClickListener(new OnClickTypeText(this, oneType));
                if(type2.equals("エラー")){
                    temp.setText(type1);
                }else{
                    temp.setText(type1 + ", " + type2);
                }
                typeList.addView(temp);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    public void setTypeView(List<PBAPokemon> oneType){
        for(LinearLayout temp : targetPokemonAffinity){
            temp.removeAllViews();
        }

        Map<String, Integer> resultMap = new HashMap<>();
        for(Type.TypeCode temp : Type.TypeCode.values()){
            Integer result = code.get(String.valueOf((int) (Type.calcurateAffinity(temp, oneType.get(0)) * 100)));
            if(result != null){
                resultMap.put(Type.convertTypeCodeToName(temp), result);
            }
        }
        for(String typeName : resultMap.keySet()){
            int p = resultMap.get(typeName);
            TextView temp = new TextView(getActivity());
            temp.setText(typeName);
            targetPokemonAffinity[p].addView(temp);
        }
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
