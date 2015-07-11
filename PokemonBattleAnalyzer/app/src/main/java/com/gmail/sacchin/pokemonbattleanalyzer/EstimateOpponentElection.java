package com.gmail.sacchin.pokemonbattleanalyzer;

import com.gmail.sacchin.pokemonbattleanalyzer.entity.IndividualPBAPokemon;
import com.gmail.sacchin.pokemonbattleanalyzer.entity.Party;

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
}
