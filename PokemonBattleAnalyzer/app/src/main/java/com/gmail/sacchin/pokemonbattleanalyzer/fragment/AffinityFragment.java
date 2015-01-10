package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.gmail.sacchin.pokemonbattleanalyzer.PartyDatabaseHelper;
import com.gmail.sacchin.pokemonbattleanalyzer.R;
import com.gmail.sacchin.pokemonbattleanalyzer.Util;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.AffinityRank;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.listener.OnClickTypeText;
import com.gmail.sacchin.pokemonlibrary.entity.Type;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sacchin on 2014/12/23.
 */
public class AffinityFragment extends Fragment {
    private PartyDatabaseHelper databaseHelper = null;
    private LinearLayout selectPokemon = null;
    private Map<String, Integer> affinityCode = null;

    private LinearLayout[] mainPokemonAffinity = null;
    private LinearLayout[] targetPokemonAffinity = null;

    private TableLayout typeList = null;
    private PBAPokemon p = null;

    /**
     *
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";


    public static AffinityFragment newInstance(String pokemonNo) {
        AffinityFragment fragment = new AffinityFragment();
        Bundle args = new Bundle();
        args.putString(ARG_SECTION_NUMBER, pokemonNo);
        fragment.setArguments(args);
        return fragment;
    }
    public AffinityFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_affinity, container, false);
        initView(rootView);

        affinityCode = new HashMap<>();
        affinityCode.put("400", new Integer(0));
        affinityCode.put("200", new Integer(1));
        affinityCode.put("50", new Integer(2));
        affinityCode.put("25", new Integer(3));
        affinityCode.put("0", new Integer(4));

        databaseHelper = new PartyDatabaseHelper(getActivity());

        String pokemonNo = getArguments().getString(ARG_SECTION_NUMBER);
        try {
            this.p = databaseHelper.selectPBAPokemon(pokemonNo);
            Map<String, Integer> selectedPokemonAffinity = calcAffinity(p);

            for(String typeName : selectedPokemonAffinity.keySet()){
                Integer p = affinityCode.get(String.valueOf(selectedPokemonAffinity.get(typeName)));
                if(p != null) {
                    TextView temp = new TextView(getActivity());
                    temp.setText(typeName);
                    mainPokemonAffinity[p].addView(temp);
                }
            }

            List<AffinityRank> ranks = new ArrayList<>();
            Map<String, List<PBAPokemon>> typeMap = getStringListMap();
            for (String key : typeMap.keySet()) {
                List<PBAPokemon> oneType = typeMap.get(key);
                PBAPokemon top = oneType.get(0);

                Map<String, Integer> targetAffinity = calcAffinity(top);
                int sum = 0;
                for(Type.TypeCode temp : Type.TypeCode.values()) {
                    Integer sp = selectedPokemonAffinity.get(Type.convertTypeCodeToName(temp));
                    sp = (sp != null) ? sp : 100;
                    Integer tp = targetAffinity.get(Type.convertTypeCodeToName(temp));
                    tp = (tp != null) ? tp : 100;
                    sum += (sp > tp) ? tp : sp;
                }
                ranks.add(new AffinityRank(sum, oneType, targetAffinity));
            }
            AffinityRank.sort(ranks);
            AffinityRank.calcDeviation(ranks);

            TableRow header = new TableRow(getActivity());
            header.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

            TextView deviationTitle = new TextView(getActivity());
            deviationTitle.setText("偏差値");
            deviationTitle.setBackgroundColor(Color.LTGRAY);
            header.addView(deviationTitle);
            TextView typeTitle = new TextView(getActivity());
            typeTitle.setText("タイプ");
            typeTitle.setBackgroundColor(Color.LTGRAY);
            header.addView(typeTitle);
            TextView pokemonTitle = new TextView(getActivity());
            pokemonTitle.setText("Pokemon");
            pokemonTitle.setBackgroundColor(Color.LTGRAY);
            header.addView(pokemonTitle);
            typeList.addView(header);

            for (AffinityRank r : ranks) {
                String type1 = r.getType1Name();
                String type2 = r.getType2Name();
                TableRow block = new TableRow(getActivity());

                block.setOrientation(LinearLayout.HORIZONTAL);
                block.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                TextView deviationValue = new TextView(getActivity());
                deviationValue.setText(String.valueOf(r.getDeviation()));
                block.addView(deviationValue);

                TextView typeValue = new TextView(getActivity());
                typeValue.setOnClickListener(new OnClickTypeText(this, r.getOneType()));
                if(type2.equals("エラー")){
                    typeValue.setText(type1);
                }else{
                    typeValue.setText(type1 + ", " + type2);
                }
                block.addView(typeValue);

                LinearLayout pokemons = new LinearLayout(getActivity());
                pokemons.setOrientation(LinearLayout.VERTICAL);
                List<PBAPokemon> list = r.getOneType();
                int count = 0;
                for(;count < list.size();){
                    LinearLayout pokemonBlock = new LinearLayout(getActivity());
                    pokemonBlock.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                    pokemonBlock.setGravity(Gravity.CENTER);
                    for(int i = 0 ; i < 10 ; i++){
                        Bitmap image = Util.createImage(list.get(count), 100f, getResources());
                        ImageView iv = new ImageView(getActivity());
                        iv.setImageBitmap(image);
                        pokemonBlock.addView(iv);
                        count++;
                        if(count > list.size() - 1){
                            break;
                        }
                    }
                    pokemons.addView(pokemonBlock);
                }
                block.addView(pokemons);
                typeList.addView(block);


            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootView;
    }

    private Map<String, List<PBAPokemon>> getStringListMap() throws IOException {
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
        return typeMap;
    }

    public void initView(View rootView){
        selectPokemon = (LinearLayout)rootView.findViewById(R.id.select_pokemon);
        typeList = (TableLayout)rootView.findViewById(R.id.type_list);

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

        Button back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    public Map<String, Integer> calcAffinity(PBAPokemon pokemon){
        Map<String, Integer> resultMap = new HashMap<>();
        for(Type.TypeCode temp : Type.TypeCode.values()){
            Integer result = (int) (Type.calcurateAffinity(temp, pokemon) * 100);
            if(result != null){
                resultMap.put(Type.convertTypeCodeToName(temp), result);
            }
        }
        return resultMap;
    }


    public void setTypeView(List<PBAPokemon> oneType){
        for(LinearLayout temp : targetPokemonAffinity){
            temp.removeAllViews();
        }

        Map<String, Integer> resultMap = calcAffinity(oneType.get(0));
        for(String typeName : resultMap.keySet()){
            Integer p = affinityCode.get(String.valueOf(resultMap.get(typeName)));
            if(p != null) {
                TextView temp = new TextView(getActivity());
                temp.setText(typeName);
                targetPokemonAffinity[p].addView(temp);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(selectPokemon != null){
            Bitmap image = Util.createImage(p, 150f, getResources());
            ImageView imageView = new ImageView(getActivity());
            imageView.setImageBitmap(image);
            selectPokemon.addView(imageView);
        }
    }


}
