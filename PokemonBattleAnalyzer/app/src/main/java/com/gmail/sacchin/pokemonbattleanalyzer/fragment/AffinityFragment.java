package com.gmail.sacchin.pokemonbattleanalyzer.fragment;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
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
        affinityCode.put("400", 0);
        affinityCode.put("200", 1);
        affinityCode.put("50", 2);
        affinityCode.put("25", 3);
        affinityCode.put("0", 4);

        databaseHelper = new PartyDatabaseHelper(getActivity());

        String pokemonNo = getArguments().getString(ARG_SECTION_NUMBER);
        try {
            this.p = databaseHelper.selectPBAPokemon(pokemonNo);
            Map<Type.TypeCode, Map<String, Integer>> selectedPokemonAffinity = calcAffinity(p);

            for(Type.TypeCode type : selectedPokemonAffinity.keySet()){
                Map<String, Integer> ttt = selectedPokemonAffinity.get(type);
                for(String key : ttt.keySet()){
                    Integer p = affinityCode.get(String.valueOf(ttt.get(key)));
                    if(p != null) {
                        TextView temp = new TextView(getActivity());
                        if(key.equals("both")){
                            temp.setText(Type.convertTypeCodeToName(type));
                            mainPokemonAffinity[p].addView(temp);
                        }else{
                            String message = "特性が" + key + "の場合、" + Type.convertTypeCodeToName(type) + "は" + p + "倍";
                            temp.setText(message);
                            mainPokemonAffinity[5].addView(temp);
                        }
                    }
                }
            }
            List<PBAPokemon> list = databaseHelper.selectAllPBAPokemon();
            List<AffinityRank> ranks = new ArrayList<>();
            for(PBAPokemon pokemon : list){
                Map<Type.TypeCode, Map<String, Integer>> targetPokemonAffinity = calcAffinity(pokemon);
                int sum = 0;
                for(Type.TypeCode type : Type.TypeCode.values()){
                    int minimumScale = Integer.MAX_VALUE;
                    Map<String, Integer> selectedScale = selectedPokemonAffinity.get(type);
                    for(String ability : selectedScale.keySet()){
                        if(selectedScale.get(ability) < minimumScale){
                            minimumScale = selectedScale.get(ability);
                        }
                    }
                    Map<String, Integer> targetScale = targetPokemonAffinity.get(type);
                    for(String ability : targetScale.keySet()){
                        if(targetScale.get(ability) < minimumScale){
                            minimumScale = targetScale.get(ability);
                        }
                    }
                    sum += minimumScale;
                }
                ranks.add(new AffinityRank(sum, pokemon));
            }

            AffinityRank.sort(ranks);
            AffinityRank.calcDeviation(ranks);

            TableRow header = createTableRow(TableRow.VERTICAL);

            TextView deviationTitle = new TextView(getActivity());
            deviationTitle.setText("偏差値");
            deviationTitle.setBackgroundColor(Color.LTGRAY);
            header.addView(deviationTitle);

            TextView pokemonTitle = new TextView(getActivity());
            pokemonTitle.setText("Pokemon");
            pokemonTitle.setBackgroundColor(Color.LTGRAY);
            header.addView(pokemonTitle);
            typeList.addView(header);

            for (AffinityRank r : ranks) {
                TableRow lastBlock = (TableRow)typeList.getChildAt(typeList.getChildCount() - 1);
                TextView deviationValue = (TextView)lastBlock.getChildAt(0);
                if(deviationValue != null){
                    try {
                        int deviation = Integer.parseInt((String)deviationValue.getText());
                        if(r.getDeviation() == deviation){
                            LinearLayout pokemons = (LinearLayout)lastBlock.getChildAt(1);
                            //既存のリストに追加する
                            LinearLayout pokemonBlock = (LinearLayout)pokemons.getChildAt(pokemons.getChildCount() - 1);
                            //一行10匹まで
                            if(9 < pokemonBlock.getChildCount()){
                                pokemonBlock.addView(createImageView(r.getPokemon()));
                            }else{
                                pokemons = new LinearLayout(getActivity());
                                pokemons.setOrientation(LinearLayout.VERTICAL);

                                pokemonBlock = new LinearLayout(getActivity());
                                pokemonBlock.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                                pokemonBlock.setGravity(Gravity.CENTER);
                                pokemonBlock.addView(createImageView(r.getPokemon()));

                                pokemons.addView(pokemonBlock);
                                lastBlock.addView(pokemons);
                                typeList.addView(lastBlock);
                            }
                        }
                    }catch (NumberFormatException e){
                        //新規のリストに追加する
                        lastBlock = new TableRow(getActivity());
                        lastBlock.setOrientation(LinearLayout.HORIZONTAL);
                        lastBlock.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
                        deviationValue = new TextView(getActivity());
                        deviationValue.setText(String.valueOf(r.getDeviation()));
                        lastBlock.addView(deviationValue);
                        LinearLayout pokemons = new LinearLayout(getActivity());
                        pokemons.setOrientation(LinearLayout.VERTICAL);

                        LinearLayout pokemonBlock = new LinearLayout(getActivity());
                        pokemonBlock.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                        pokemonBlock.setGravity(Gravity.CENTER);
                        pokemonBlock.addView(createImageView(r.getPokemon()));
                        pokemons.addView(pokemonBlock);
                        lastBlock.addView(pokemons);
                        typeList.addView(lastBlock);
                    }
                }
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
                    oneType = new ArrayList<>();
                    typeMap.put(key1, oneType);
                }
            }
            oneType.add(temp);
        }
        return typeMap;
    }

    public TableRow createTableRow(int orientation){
        TableRow tableRow = new TableRow(getActivity());
        tableRow.setOrientation(orientation);
        tableRow.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        return tableRow;
    }

    public ImageView createImageView(PBAPokemon pokemon){
        Bitmap image = Util.createImage(pokemon, 100f, getResources());
        ImageView iv = new ImageView(getActivity());
        iv.setImageBitmap(image);
        return iv;
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
        mainPokemonAffinity[5] = (LinearLayout)rootView.findViewById(R.id.main_type_of_e);

        targetPokemonAffinity = new LinearLayout[5];
        targetPokemonAffinity[0] = (LinearLayout)rootView.findViewById(R.id.target_type_of_4);
        targetPokemonAffinity[1] = (LinearLayout)rootView.findViewById(R.id.target_type_of_2);
        targetPokemonAffinity[2] = (LinearLayout)rootView.findViewById(R.id.target_type_of_h);
        targetPokemonAffinity[3] = (LinearLayout)rootView.findViewById(R.id.target_type_of_q);
        targetPokemonAffinity[4] = (LinearLayout)rootView.findViewById(R.id.target_type_of_z);
        targetPokemonAffinity[5] = (LinearLayout)rootView.findViewById(R.id.target_type_of_e);

        Button back = (Button) rootView.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    public Map<Type.TypeCode, Map<String, Integer>> calcAffinity(PBAPokemon pokemon){
        Map<Type.TypeCode, Map<String, Integer>> resultMap = pokemon.calcAllTypeScale();
        return resultMap;
    }

    public void setTypeView(List<PBAPokemon> oneType){
        for(LinearLayout temp : targetPokemonAffinity){
            temp.removeAllViews();
        }

        Map<Type.TypeCode, Map<String, Integer>> resultMap = calcAffinity(oneType.get(0));
        for(Type.TypeCode type : resultMap.keySet()){
            Map<String, Integer> abilityScale = resultMap.get(type);

            for(String abilityName  : abilityScale.keySet()) {
                String typeName = Type.convertTypeCodeToName(type);
                if(!"both".equals(abilityName)) {
                    Integer p = affinityCode.get(String.valueOf(resultMap.get(typeName)));
                    if (p != null) {
                        typeName += "(" + abilityName + ")";
                    }
                }
                Integer p = affinityCode.get(String.valueOf(resultMap.get(abilityName)));
                    if(p != null) {
                        TextView temp = new TextView(getActivity());
                        temp.setText(typeName);
                        targetPokemonAffinity[p].addView(temp);
                }
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
