package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import com.gmail.sacchin.pokemonlibrary.entity.Type;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Created by sacchin on 2014/12/30.
 */
public class AffinityRank {
    Map<String, Integer> affinityMap = null;
    List<PBAPokemon> oneType = null;
    int point = 0;

    public AffinityRank(int point, List<PBAPokemon> oneType, Map<String, Integer> affinityMap){
        this.affinityMap = affinityMap;
       this.oneType = oneType;
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

    public List<PBAPokemon> getOneType() {
        return oneType;
    }

    public Map<String, Integer> getAffinityMap() {
        return affinityMap;
    }

    public String getType1Name() {
        return Type.convertTypeCodeToName(oneType.get(0).getType1());
    }

    public String getType2Name() {
        return Type.convertTypeCodeToName(oneType.get(0).getType2());
    }

    public String toString(){ return point + " - " + getType1Name() + "," + getType2Name(); }






    public int calcPoint(){return 0;}

// point算出アルゴリズム
// 1. 補完得点を計算すす。補完得点は、技を受けた時の倍率が低い方を採用する
//      例1：ほのお技に対して、ポケモンAが4倍弱点、ポケモンBが等倍の場合
//          → 4:1 = 1
//      例2：ほのお技に対して、ポケモンAが2倍弱点、ポケモンBが1/4倍の場合
//          → 2:0.25 = 0.25
// 2. すべてのタイプに対して、補完得点を計算し、それを合計する。
// 3. 合計が低いほど補完として優秀であり、高いほど補完として優秀でない。

}
