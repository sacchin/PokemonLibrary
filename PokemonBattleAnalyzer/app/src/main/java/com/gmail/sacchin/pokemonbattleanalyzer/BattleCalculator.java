package com.gmail.sacchin.pokemonbattleanalyzer;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonlibrary.entity.Skill;

import java.util.Map;

/**
 * Created by sacchin on 2015/07/12.
 */
public class BattleCalculator {
    private static final float[] damageRevesion = {1.0f, 0.99f, 0.98f, 0.97f, 0.96f, 0.95f,
            0.94f, 0.93f, 0.92f, 0.91f, 0.90f, 0.89f, 0.88f, 0.87f, 0.86f, 85f};

    public static void calcDamageRate(IndividualPBAPokemon attackSide, Skill skill, IndividualPBAPokemon deffenceSide){
        Map<Float, Integer> maxDamage = deffenceSide.calcDamage(attackSide, skill);
//        int kakuitiCount = 0;
//        for (float revision : damageRevesion){
//            int remain = deffenceSide.getHPValue() - (int)(maxDamage * revision);
//            if(remain <= 0){
//                kakuitiCount++;
//            }
//        }
        //((attackLevel * 2 / 5 + 2) * skill.getPower() * attackValue) / (deffenceValue / 50 + 2);

    }

}
