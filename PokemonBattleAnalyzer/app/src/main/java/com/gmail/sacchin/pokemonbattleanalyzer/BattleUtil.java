package com.gmail.sacchin.pokemonbattleanalyzer;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PokemonCharacteristic;
import com.gmail.sacchin.pokemonlibrary.entity.Characteristic;

/**
 * Created by sacchin on 2015/08/11.
 */
public class BattleUtil {
    public static IndividualPBAPokemon[] getAttackOrder(String myCharacteristicName, IndividualPBAPokemon mine,
                                                        PokemonCharacteristic opPc, IndividualPBAPokemon opponent){
        IndividualPBAPokemon[] order = new IndividualPBAPokemon[2];

        int mysp = mine.getSpeedValue(Characteristic.convertCharacteristicNameToNo(myCharacteristicName));
        int opposp = opponent.getSpeedValue(Characteristic.convertCharacteristicNameToNo(opPc.getName()));
        if(opposp < mysp){
            order[0] = mine;
            order[1] = opponent;
        }else{
            order[0] = opponent;
            order[1] = mine;
        }
        return order;
    }}
