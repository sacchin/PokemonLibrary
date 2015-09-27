package com.gmail.sacchin.pokemonbattleanalyzer.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.pgl.RankingPokemonTrend;
import com.gmail.sacchin.pokemonlibrary.entity.Characteristic;
import com.gmail.sacchin.pokemonlibrary.entity.Skill;

public class IndividualPBAPokemon extends PBAPokemon {

    public final static int UNKNOWN_EV = -1;

    private long id = 0;
    private Timestamp time = null;
    private String item = "";
    private String ability = null;
    private String skillNo1 = "";
    private String skillNo2 = "";
    private String skillNo3 = "";
    private String skillNo4 = "";
    private int hpEffortValue = UNKNOWN_EV;
    private int attackEffortValue = UNKNOWN_EV;
    private int deffenceEffortValue = UNKNOWN_EV;
    private int specialAttackEffortValue = UNKNOWN_EV;
    private int specialDeffenceEffortValue = UNKNOWN_EV;
    private int speedEffortValue = UNKNOWN_EV;

    private RankingPokemonTrend trend = null;

    public IndividualPBAPokemon(PBAPokemon p, long id, Timestamp time, String item, String ability,
                                String skillNo1, String skillNo2, String skillNo3, String skillNo4) {
        super(p.getNo(), p.getJname(), p.getEname(), p.getH(), p.getA(), p.getB(), p.getC(), p.getD(), p.getS(), p.getAbility1(),
                p.getAbility2(), p.getAbilityd(), 0, 0, 0, 0);
        setRowId(p.getRowId());
        this.id = id;
        this.time = time;
        this.item = item;
        this.ability = ability;
        this.skillNo1 = skillNo1;
        this.skillNo2 = skillNo2;
        this.skillNo3 = skillNo3;
        this.skillNo4 = skillNo4;
    }

    public IndividualPBAPokemon(String no, String name, String ename, int h, int a,
                                int b, int c, int d, int s, String ability1,
                                String ability2, String abilityd, long id, Timestamp time, String item, String ability,
                                String skillNo1, String skillNo2, String skillNo3, String skillNo4) {
        super(no, name, ename, h, a, b, c, d, s, ability1, ability2,
                abilityd, 0, 0, 0, 0);
        this.id = id;
        this.time = time;
        this.item = item;
        this.ability = ability;
        this.skillNo1 = skillNo1;
        this.skillNo2 = skillNo2;
        this.skillNo3 = skillNo3;
        this.skillNo4 = skillNo4;
    }

    private List<Entry<String, Integer>> valueSort(Map<String, Integer> statistics) {
        List<Entry<String, Integer>> entries = new ArrayList<Entry<String, Integer>>(statistics.entrySet());
        Collections.sort(entries, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return ((Integer) o2.getValue()).compareTo((Integer) o1.getValue());//�~��
            }
        });
        return entries;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public Timestamp getTime() {
        return time;
    }


    public void setTime(Timestamp time) {
        this.time = time;
    }


    public String getItem() {
        return item;
    }


    public void setItem(String item) {
        this.item = item;
    }


    public String getCharacteristic() {
        return ability;
    }


    public void setCharacteristic(String characteristic) {
        this.ability = characteristic;
    }


    public String getSkillNo1() {
        return skillNo1;
    }


    public void setSkillNo1(String skillNo1) {
        this.skillNo1 = skillNo1;
    }


    public String getSkillNo2() {
        return skillNo2;
    }


    public void setSkillNo2(String skillNo2) {
        this.skillNo2 = skillNo2;
    }


    public String getSkillNo3() {
        return skillNo3;
    }


    public void setSkillNo3(String skillNo3) {
        this.skillNo3 = skillNo3;
    }


    public String getSkillNo4() {
        return skillNo4;
    }


    public void setSkillNo4(String skillNo4) {
        this.skillNo4 = skillNo4;
    }

    public RankingPokemonTrend getTrend() {
        return trend;
    }

    public void setTrend(RankingPokemonTrend trend) {
        this.trend = trend;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public void setHpEffortValue(int hpEffortValue) {
        this.hpEffortValue = hpEffortValue;
    }

    public void setAttackEffortValue(int attackEffortValue) {
        this.attackEffortValue = attackEffortValue;
    }

    public void setDeffenceEffortValue(int deffenceEffortValue) {
        this.deffenceEffortValue = deffenceEffortValue;
    }

    public void setSpecialAttackEffortValue(int specialAttackEffortValue) {
        this.specialAttackEffortValue = specialAttackEffortValue;
    }

    public void setSpecialDeffenceEffortValue(int specialDeffenceEffortValue) {
        this.specialDeffenceEffortValue = specialDeffenceEffortValue;
    }

    public void setSpeedEffortValue(int speedEffortValue) {
        this.speedEffortValue = speedEffortValue;
    }

    public int getHPValue() {
        if(hpEffortValue == UNKNOWN_EV){
            return super.getHPValue(31, 252);
        }
        return super.getHPValue(31, hpEffortValue);
    }

    public int getAttackValue() {
        if(attackEffortValue == UNKNOWN_EV){
            return super.getAttackValue(31, 252);
        }
        return super.getAttackValue(31, attackEffortValue);
    }

    public int getDeffenceValue() {
        if(deffenceEffortValue == UNKNOWN_EV){
            return super.getDeffenceValue(31, 252);
        }
        return super.getDeffenceValue(31, deffenceEffortValue);
    }

    public int getSpecialAttackValue() {
        if(specialAttackEffortValue == UNKNOWN_EV){
            return super.getSpecialAttackValue(31, 252);
        }
        return super.getSpecialAttackValue(31, specialAttackEffortValue);
    }

    public int getSpecialDeffenceValue() {
        if(specialDeffenceEffortValue == UNKNOWN_EV){
            return super.getSpecialDeffenceValue(31, 252);
        }
        return super.getSpecialDeffenceValue(31, specialDeffenceEffortValue);
    }

    public int getSpeedValue() {
        if(speedEffortValue == UNKNOWN_EV){
            return super.getSpeedValue(31, 252);
        }
        return super.getSpeedValue(31, speedEffortValue);
    }

    public int getSpeedValue(int characteristicNo) {
        float[] rate = Characteristic.CHARACTERRISTIC_TABLE[characteristicNo];
        if(rate != null && 4 < rate.length){
            return (int)(super.getHPValue(31, speedEffortValue) * rate[4]);
        }else{
            return getSpeedValue();
        }
    }

    public Map<Float, Integer> calcDamage(IndividualPBAPokemon attackSide, Skill skill) {
        List<PokemonCharacteristic> dSideCharacteristics = trend.getCharacteristicList();
        List<PokemonCharacteristic> aSideCharacteristics = attackSide.getTrend().getCharacteristicList();

        Map<Float, Integer> resultMap = new HashMap<>();
        for (PokemonCharacteristic dc : dSideCharacteristics) {
            float[] dRevision = dc.getRevision();
            for (PokemonCharacteristic ac : aSideCharacteristics) {
                float[] aRevision = ac.getRevision();
                float rate = (float) (dc.getUsageRate() * ac.getUsageRate());
                int damage = 0;
                switch (skill.getCategory()) {
                    case 0:
                        damage = (int) (22 * skill.getPower() * attackSide.getAttackValue() * aRevision[0] / getDeffenceValue() * dRevision[1] / 50 + 2);
                        resultMap.put(rate, damage);
                        break;
                    case 1:
                        damage = (int) (22 * skill.getPower() * attackSide.getSpecialAttackValue() * aRevision[2] / getSpecialDeffenceValue() * dRevision[3] / 50 + 2);
                        resultMap.put(rate, damage);
                        break;
                    default:
                        //this is not attack
                        break;
                }
            }
        }
        return resultMap;
    }

    public String toString(){
        return "id:" + id + ", item:" + item + ", ability:" + ability +
                ", skill1" + skillNo1 + ", skill2" + skillNo2 + ", skill3" + skillNo3 + ", skill4" + skillNo4 +
                ", H:" + hpEffortValue + ", A:" + attackEffortValue + ", B:" + deffenceEffortValue +
                ", C:" + specialAttackEffortValue + ", D:" + specialDeffenceEffortValue + ", S:" + speedEffortValue;
    }
}
