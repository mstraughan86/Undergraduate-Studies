package io.github.mstraughan86.dungeon2.Characters.Skills;

import io.github.mstraughan86.dungeon2.Characters.BattleCharacter;

import java.util.ArrayList;

public class Flee extends Skill {

    public Flee(BattleCharacter parent){
        super(parent, "Flee", 0, false);
    }

    public void doSkill
            (BattleCharacter currentPlayer, BattleCharacter targetPlayer, ArrayList playerArray, ArrayList enemyArray) {
        System.out.println("Fleeing!");
    }

}
