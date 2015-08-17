package com.gmail.sacchin.pokemonbattleanalyzer;

import android.util.Log;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.PokemonCharacteristic;
import com.gmail.sacchin.pokemonlibrary.entity.Characteristic;

import java.util.List;
import java.util.Map;

/**
 * Created by sacchin on 2015/07/11.
 */
public class EstimateOpponentElection {
    public static IndividualPBAPokemon[] estimate(Party mine, Party opponent){
        IndividualPBAPokemon[] estimated = new IndividualPBAPokemon[3];
        estimated[0] = opponent.getMember().get(0);
        estimated[1] = opponent.getMember().get(1);
        estimated[2] = opponent.getMember().get(2);
        return estimated;
    }

    public void calc(IndividualPBAPokemon mine, IndividualPBAPokemon opponent){
        if(opponent.getTrend() != null){
            List<PokemonCharacteristic> c = opponent.getTrend().getCharacteristicList();

            for(PokemonCharacteristic h : c){
                Log.v("estimate", h.getName() + ":" + h.getUsageRate() );
            }
        }
    }

    public IndividualPBAPokemon[] get(PokemonCharacteristic minePc, IndividualPBAPokemon mine,
                                      PokemonCharacteristic opPc, IndividualPBAPokemon opponent){
        mine.getSpeedValue(minePc);
        opponent.getSpeedValue(opPc);
        return null;
    }
}
