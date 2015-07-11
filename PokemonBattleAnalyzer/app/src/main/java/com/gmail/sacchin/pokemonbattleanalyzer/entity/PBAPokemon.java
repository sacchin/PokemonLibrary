package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import android.util.Log;

import com.gmail.sacchin.pokemonlibrary.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PBAPokemon extends Pokemon{
	private int resourceId = 0;
	private int rowId = 0;
	private int ranking = 0;

	public PBAPokemon(String no, String jname, String ename, int h, int a, int b, int c, int d, int s,
                      String ability1, String ability2, String abilityd,
                      int type1, int type2, float weight, int ranking) {
		super(no, jname, ename, h, a, b, c, d, s, ability1, ability2, abilityd, type1, type2, weight);
		this.ranking = ranking;
	}
	
	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}

	public int getRanking() {
		return ranking;
	}

	public int getRowId() {
		return rowId;
	}

	public void setRowId(int rowId) {
		this.rowId = rowId;
	}

    public List<String> getAbilities(){
        List<String> temp = new ArrayList<>();
        temp.add(getAbility1());
        if(!getAbility2().equals("-")){
            temp.add(getAbility2());
        }
        if(!getAbilityd().equals("-")){
            temp.add(getAbilityd());
        }
        return temp;
    }

    public Map<String, Integer> calcATypeScale(Type.TypeCode type){
        List<String> abilities = getAbilities();
        Map<String, Integer> scaleMap = new HashMap<>();
        int result;

        //タイプ相性に関係する特性がある場合、その値を格納する
        //ふしぎなまもりは特別
        for(String ability : abilities){
            if(ability.equals("ふしぎなまもり")){
                if(type == Type.TypeCode.FIRE || type == Type.TypeCode.GHOST || type == Type.TypeCode.FLYNG ||
                        type == Type.TypeCode.ROCK || type == Type.TypeCode.DARK){
                    scaleMap.put(ability, 200);
                }else{
                    scaleMap.put(ability, 0);
                }
            }else{
                float scaleByAbility = Ability.calcTypeScale(ability, type);
                result = (int) (scaleByAbility * Type.calcurateAffinity(type, this) * 100);
                scaleMap.put(ability, result);
            }
        }

        //すべての特性で倍率が同じ場合、１つにまとめる
        int judgeSameScale = scaleMap.values().iterator().next();
        boolean isSame = true;
        for(Integer scale : scaleMap.values()){
            if(judgeSameScale != scale){
                isSame = false;
            }
        }
        if(isSame){
            scaleMap.clear();
            scaleMap.put("both", judgeSameScale);
        }
        return scaleMap;
    }

    public Map<Type.TypeCode, Map<String, Integer>> calcAllTypeScale(){
        Map<Type.TypeCode, Map<String, Integer>> scaleMap = new HashMap<>();
        for(Type.TypeCode type : Type.TypeCode.values()){
            Map<String, Integer> temp = calcATypeScale(type);
            scaleMap.put(type, temp);
        }
        return scaleMap;
    }


    @Override
    public float getAttackRevision() {
        return 0;
    }

    @Override
    public float getDeffenceRevision() {
        return 0;
    }

    @Override
    public float getSpecialAttackRevision() {
        return 0;
    }

    @Override
    public float getSpecialDeffenceRevision() {
        return 0;
    }

    @Override
    public float getSpeedRevision() {
        return 0;
    }
}
