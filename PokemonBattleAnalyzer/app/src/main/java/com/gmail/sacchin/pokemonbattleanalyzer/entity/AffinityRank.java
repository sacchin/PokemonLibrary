package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import android.util.Log;

import com.gmail.sacchin.pokemonlibrary.entity.Type;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/** point算出アルゴリズム
 * 1. 補完得点を計算する。補完得点は、技を受けた時の倍率が低い方を採用する
 *      例1：ほのお技に対して、ポケモンAが4倍弱点、ポケモンBが等倍の場合
 *          → 4 > 1 = 1
 *      例2：ほのお技に対して、ポケモンAが2倍弱点、ポケモンBが1/4倍の場合
 *          → 2 > 0.25 = 0.25
 *      例3：みず技に対して、ポケモンAが「よびみず」の場合0倍、その他の特性の場合1倍、ポケモンBが1/4倍の場合
 *          → 0 or 1 < 0.25 = 0
 * 2. すべてのタイプに対して、補完得点を計算し、それを合計する。
 * 3. 合計が低いほど補完として優秀であり、高いほど補完として優秀でない。
 */
public class AffinityRank {
    private PBAPokemon pokemon;
    private int point = 0;
    private int deviation = 0;

    public AffinityRank(int point, PBAPokemon pokemon){
        this.pokemon = pokemon;
        this.point = point;
    }

    public static void sort(List<AffinityRank> list){
        Collections.sort(list, new AffinityRankComparator());
    }

    private static class AffinityRankComparator implements Comparator<AffinityRank> {
        @Override
        public int compare(AffinityRank lhs, AffinityRank rhs) {
            return lhs.point - rhs.point;
        }
    }

    public PBAPokemon getPokemon() {
        return pokemon;
    }

    public String getType1Name() {
        return Type.convertTypeCodeToName(pokemon.getType1());
    }

    public String getType2Name() {
        return Type.convertTypeCodeToName(pokemon.getType2());
    }

    public String toString(){ return point + " - " + getType1Name() + "," + getType2Name(); }

    public int getDeviation() {
        return deviation;
    }

    public void setDeviation(int deviation) {
        this.deviation = deviation;
    }

    public int getPoint() {
        return point;
    }

    public static void calcDeviation(List<AffinityRank> list){
        if(list == null){
            return;
        }

        int average = 0;
        for(AffinityRank temp : list){
            average += temp.getPoint();
        }
        average /= list.size();

        int standardDeviation = 0;
        for(AffinityRank temp : list){
            standardDeviation += (temp.getPoint() - average) * (temp.getPoint() - average);
        }
        standardDeviation /= list.size();
        standardDeviation = (int) Math.sqrt(standardDeviation);

        for(AffinityRank temp : list){
            //pointが低いほど優秀なため、下記計算式を変えています。
            double value = (average - temp.getPoint()) / (double)standardDeviation;
            temp.setDeviation((int)(value * 10d + 50d));
        }
    }
}
